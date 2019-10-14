package findPwd.dao.joo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import jdbc.JdbcUtil;
import login.dao.joo.LoginDao;

public class FindPwdDao {
	private static FindPwdDao dao=new FindPwdDao();
	private FindPwdDao() {}
	public static FindPwdDao getDao() {
		return dao;
	}
	public String getTemPwd() {
		 Random rnd=new Random();
			String pwd="";
			for (int i = 0; i < 4; i++) {
				String n=String.valueOf(rnd.nextInt(10));
				char[] a=Character.toChars(rnd.nextInt(26)+65);
				pwd+=n+a[0];
			}
			return pwd;
	}
	public int updateTemPwd(String id,String temPwd) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=JdbcUtil.getConn();
			String sql="update members set pwd=? where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, temPwd);
			pstmt.setString(2, id);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;	
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
	public int searchPwd(String id,String email) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=JdbcUtil.getConn();
			String sql="select * from members where id=? and email=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return 1;
			}else {
				return -1;
			}
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;	
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
}



























