package domain.dao;

import java.util.List;

import domain.dto.SessionDto;

public interface SessionDao {
    
	//SESSION용
	boolean insert(SessionDto sessionDto) throws Exception;

	SessionDto select(int sessiondId) throws Exception;

	SessionDto select(String username) throws Exception;

	boolean delete(String username) throws Exception;

	//SELECTALL
	List<SessionDto> selectAll() throws Exception;
}
