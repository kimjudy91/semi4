package updateMembers.dao.joo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.JdbcUtil;

public class UpdateDao {
	private static UpdateDao dao=new UpdateDao();
	private UpdateDao() {}
	public static UpdateDao getDao() {
		return dao;
	}
	public int update(String id,String email,String phone,String address,String pwd) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=JdbcUtil.getConn();
			String sql="update members set email=?,phone=?,address=?,pwd=? where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, phone);
			pstmt.setString(3, address);
			pstmt.setString(4, pwd);
			pstmt.setString(5,id);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
}
