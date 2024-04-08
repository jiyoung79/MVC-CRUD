package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.mysql.cj.protocol.ExportControlled;

import domain.dto.MemberDto;

public interface MemberService {
    //회원가입
	boolean signUp(MemberDto dto) throws Exception;

	//로그인
	Map<String, Object> signIn(MemberDto dto) throws Exception;

	//로그아웃
	Map<String, Object> signOut(MemberDto dto) throws Exception;

	//유저정보 가져오기
	MemberDto getProfile(String username) throws Exception;

	//현재 접속중인 세션Id list 리턴
	List<Integer> getSessionIdList();

	// 전체 유저 조회
	public List<MemberDto> getAllUsers() throws SQLException;

	// 회원 정보 수정
	boolean updateProfile(MemberDto updateDto) throws Exception;
	
	// 회원 정보 삭제
	boolean deleteProfile(MemberDto deleteDto) throws Exception;

	// 유저정보 가져오기
	public MemberDto getMember(String username) throws Exception;
}
