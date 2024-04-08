package domain.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.CommonDao;
import domain.dto.MemberDto;

public class MemberDaoImpl extends CommonDao implements MemberDao {

    private static MemberDao instance;

    public static MemberDao getInstance() throws Exception {
        if (instance == null)
            instance = new MemberDaoImpl();
        return instance;
    }

    private MemberDaoImpl() throws Exception {
        System.out.println("[DAO] MemberDaoImpl's INIT " + conn);

    }

    @Override
    public boolean insert(MemberDto dto) throws Exception {
        pstmt = conn.prepareStatement("insert into member values(?,?,?,?)");
        pstmt.setString(1, dto.getUsername());
        pstmt.setString(2, dto.getPassword());
        pstmt.setString(3, dto.getEmail());
        pstmt.setInt(4, dto.getPhone());
        int result = pstmt.executeUpdate();
        freeConnection(pstmt);
        return result > 0;
    }

    @Override
    public List<MemberDto> selectAll() throws SQLException {
        pstmt = conn.prepareStatement("select * from member");
        rs = pstmt.executeQuery();
        // MemberDto dto = null;
        // null이 아닌 ArrayList의 인스턴스로 초기화 -> NullPointerException 방지
        List<MemberDto> list = new ArrayList<>(); // List 초기화
        if (rs != null) {
            while (rs.next()) {
                MemberDto dto = new MemberDto(); // 객체를 반복적으로 생성
                dto.setUsername(rs.getString("username"));
                dto.setPassword(rs.getString("password"));
                dto.setEmail(rs.getString("email"));
                dto.setPhone(rs.getInt("phone"));
                list.add(dto); // 리스트에 회원정보 추가
            }
        }
        freeConnection(pstmt, rs);
        // System.out.println(list);
        return list;
    }

    @Override
    public MemberDto select(String username) throws Exception {
        pstmt = conn.prepareStatement("select * from member where username=?");
        pstmt.setString(1, username);
        rs = pstmt.executeQuery();
        MemberDto dto = null;

        if (rs != null) {
            if (rs.next()) {
                dto = new MemberDto();
                dto.setUsername(username);
                dto.setPassword(rs.getString("password"));
                dto.setEmail(rs.getString("email"));
                dto.setPhone(rs.getInt("phone"));
            }
        }
        freeConnection(pstmt, rs);
        return dto;
    }

    @Override
    public boolean update(String username, String password, String email, Integer phone) throws Exception {
        pstmt = conn.prepareStatement("update member set password=?, email=?, phone=? where username=?");
        pstmt.setString(1, password);
        pstmt.setString(2, email);
        pstmt.setInt(3, phone);
        pstmt.setString(4, username);
        int result = pstmt.executeUpdate();
        freeConnection(pstmt);
        return result > 0;
    }

    @Override
    public boolean delete(String username) throws Exception {
        pstmt = conn.prepareStatement("delete from member where username=?");
        pstmt.setString(1, username);
        int result = pstmt.executeUpdate();
        freeConnection(pstmt);
        return result > 0;
    }

}
