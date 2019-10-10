package findId.dao.joo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import login.dao.joo.LoginDao;

public class FindIdDao {
	private static FindIdDao dao=new FindIdDao();
	private FindIdDao() {}
	public static FindIdDao getDao() {
		return dao;
	}
	public String findId(String phone,String email,String pwd) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=JdbcUtil.getConn();
			String sql="select id from members where phone=? and email=? and pwd=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, phone);
			pstmt.setString(2, email);
			pstmt.setString(3, pwd);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String id=rs.getString("id");
				return id;
			}
			return null;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
}
