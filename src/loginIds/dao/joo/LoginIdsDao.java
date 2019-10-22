package loginIds.dao.joo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.dao.yun.BoardDao;
import jdbc.JdbcUtil;
import loginIds.vo.joo.LoginIdVO;

public class LoginIdsDao{
	private static LoginIdsDao dao=new LoginIdsDao();
	public LoginIdsDao() {}
	public static LoginIdsDao getDao() {
		return dao;
	}
	public int insertLogins(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=JdbcUtil.getConn();
			String sql="insert into loginids values(login_num_seq.nextval,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally{
			JdbcUtil.close(con, pstmt, null);
		}
	}
	public int deleteLogins(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=JdbcUtil.getConn();
			String sql="delete from loginids where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally{
			JdbcUtil.close(con, pstmt, null);
		}
	}
	public ArrayList<LoginIdVO> list(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=JdbcUtil.getConn();
			String sql="select * from loginids";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<LoginIdVO> list=new ArrayList<LoginIdVO>();
			while(rs.next()) {
				int login_num=rs.getInt("login_num");
				String ids=rs.getString("ids");
				LoginIdVO vo=new LoginIdVO(login_num, ids);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
				
	}
}



























