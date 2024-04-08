package domain.dao;

import java.util.ArrayList;
import java.util.List;

import domain.CommonDao;
import domain.dto.SessionDto;

public class SessionDaoImpl extends CommonDao implements SessionDao {

    private static SessionDao instance ;
	public static SessionDao getInstance() throws Exception {
		if(instance==null)
			instance=new SessionDaoImpl();
		return instance;
	}
	
	private SessionDaoImpl() throws Exception{
		System.out.println("[DAO] SessionDaoImpl's INIT ");

	}

    @Override
    public boolean insert(SessionDto sessionDto) throws Exception {
        pstmt =  conn.prepareStatement("insert into session values(?,?)");
		pstmt.setString(1, sessionDto.getUuid());
        pstmt.setString(2, sessionDto.getUsername());
        int result = pstmt.executeUpdate();
        freeConnection(pstmt);
		return result>0;
    }

    @Override
    public SessionDto select(int sessiondId) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SessionDto select(String username) throws Exception {
        pstmt = conn.prepareStatement("select * from session where username=?");
		pstmt.setString(1,username);
		
		rs=pstmt.executeQuery();
		SessionDto dto=null;
		if(rs!=null) {
			rs.next();
			dto=new SessionDto();
			dto.setUsername(rs.getString("username"));
        }
		freeConnection(pstmt,rs);
		return dto;
    }

    @Override
    public boolean delete(String username) throws Exception {
		pstmt = conn.prepareStatement("delete from session where username=?");
		pstmt.setString(1, username);
		System.out.println(username);
		int result = pstmt.executeUpdate();
		freeConnection(pstmt);
        return result>0;
    }

    @Override
    public List<SessionDto> selectAll() throws Exception {
        pstmt = conn.prepareStatement("select * from session");
		rs =  pstmt.executeQuery();
		List<SessionDto> list = new ArrayList<>();
		SessionDto dto = null;
		if(rs!=null)
		{
			while(rs.next()) {
				dto = new SessionDto();
                dto.setUuid(rs.getString("uuid"));
                dto.setUsername(rs.getString("username"));
				list.add(dto);
			}
		}	
		freeConnection(pstmt,rs);
		return list;
	}
}
