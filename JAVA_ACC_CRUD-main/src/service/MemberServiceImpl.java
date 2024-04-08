package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import domain.dao.MemberDao;
import domain.dao.MemberDaoImpl;
import domain.dao.SessionDao;
import domain.dao.SessionDaoImpl;
import domain.dto.MemberDto;
import domain.dto.SessionDto;

public class MemberServiceImpl implements MemberService {

    private List<String> SessionUUIDList;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private MemberDao memberDao;
    private SessionDao sessionDao;
    private String encrypt;
    private List<SessionDto> sessionUserList;

    private static MemberService instance;

    public static MemberService getInstance() throws Exception {
        if (instance == null)
            instance = new MemberServiceImpl();
        return instance;
    }

    private MemberServiceImpl() throws Exception {
        System.out.println("MemberServiceImpl's MemberServiceImpl()");
        bCryptPasswordEncoder = new BCryptPasswordEncoder();

        memberDao = MemberDaoImpl.getInstance();
        sessionDao = SessionDaoImpl.getInstance();
        SessionUUIDList = new ArrayList<>();
        // 접속중인 sessionid를 session테이블에서 list로 저장
        List<SessionDto> tmpList = sessionDao.selectAll();
        for (SessionDto dto : tmpList) {
            SessionUUIDList.add(dto.getUuid());
        }
    }

    public List<MemberDto> getAllUsers() throws SQLException {
        return memberDao.selectAll();
    }

    @Override
    public boolean signUp(MemberDto dto) throws Exception {
        encrypt = bCryptPasswordEncoder.encode(dto.getPassword());
        dto.setPassword(encrypt);

        return memberDao.insert(dto);
    }

    @Override
    public Map<String, Object> signIn(MemberDto dto) throws Exception {

        Map<String, Object> result = new HashMap<>();
        String uuid = UUID.randomUUID().toString();
        String username = dto.getUsername();
        String password = dto.getPassword();
        sessionUserList = sessionDao.selectAll();
        List<String> allUsersName = new ArrayList<>();
        for (SessionDto user : sessionUserList) {
            allUsersName.add(user.getUsername());
        }

        // 1 SessionList에 동일한 세션정보가 있는지 확인
        // if sessionDB에 사용자 이름이 있다면? 로그인한 상태인거임
        for (String userList : allUsersName) {
            if (username.equals(userList)) {
                result.put("response", false);
                result.put("msg", "이미 해당 계정은 로그인한 상태입니다.");
                return result;
            }
        }

        // 2 로그인 상태가 아니라면 user테이블로부터 동일한 이름의 user정보를 가져오기
        MemberDto savedUser = getMember(username);
        if (savedUser == null) {
            result.put("response", false);
            result.put("msg", "동일 계정이 존재하지 않습니다.");
            return result;
        }

        // 3 pw일치여부 확인
        if (!bCryptPasswordEncoder.matches(password, savedUser.getPassword())) {
            result.put("response", false);
            result.put("msg", "Password가 일치하지 않습니다.");
            return result;
        }

        // 4 PW일치한다면 session테이블에 세션정보 저장
        SessionDto sessionDto = new SessionDto();
        sessionDto.setUsername(savedUser.getUsername());
        sessionDto.setUuid(uuid);
        boolean isSessionSaved = sessionDao.insert(sessionDto);
        if (!isSessionSaved) {
            result.put("response", false);
            result.put("msg", "로그인 처리중 오류가 발생하였습니다.Session생성 실패..");
            return result;
        }

        // 5 PW일치한다면 sessionList에 sessionId값 저장
        String id = sessionDao.select(sessionDto.getUsername()).getUuid();
        result.put("response", true);
        result.put("msg", "로그인 성공!");
        result.put("sessionId", id);
        // SessionUUIDList.add(id);
        return result;
    }

    @Override
    public Map<String, Object> signOut(MemberDto dto) throws Exception {

        Map<String, Object> result = new HashMap<>();
        String username = dto.getUsername();
        String password = dto.getPassword();
        MemberDto member = memberDao.select(username);

        sessionUserList = sessionDao.selectAll();
        List<String> allUsersName = new ArrayList<>();
        for (SessionDto user : sessionUserList) {
            allUsersName.add(user.getUsername());
        }

        // 1 멤버 테이블에 존재하는 지 확인 && pw 일치 확인
        for (String userList : allUsersName) {
            if (username.equals(userList) && bCryptPasswordEncoder.matches(password, member.getPassword())) {
                // 2 세션에 존재하는 지 확인
                for (String signOutUser : allUsersName) {
                    if (username.equals(signOutUser)) {
                        sessionDao.delete(username);
                        result.put("response", true);
                        result.put("msg", "로그아웃 성공했습니다.");
                    }
                }
            }
        }
        return result;
    }

    @Override
    public MemberDto getProfile(String username) throws Exception {
        return memberDao.select(username);
    }

    @Override
    public List<Integer> getSessionIdList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSessionIdList'");
    }

    @Override
    public boolean updateProfile(MemberDto dto) throws Exception {
        String encryptPassword = bCryptPasswordEncoder.encode(dto.getPassword());
        return memberDao.update(dto.getUsername(), encryptPassword , dto.getEmail(), dto.getPhone());
    }

    @Override
    public boolean deleteProfile(MemberDto deleteDto) throws Exception {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String username = deleteDto.getUsername();
        String password = deleteDto.getPassword();
        boolean temp = bCryptPasswordEncoder.matches(password, encrypt);
        return memberDao.delete(username);
    }

    @Override
    public MemberDto getMember(String username) throws Exception {
        return memberDao.select(username);
    }

}