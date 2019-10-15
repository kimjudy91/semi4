package nquire.dao.min;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import nquire.vo.min.NquireVo;

public class NquireDao {
	
	private static NquireDao dao=new NquireDao();
	private NquireDao() {}
	public static NquireDao getDao() {
		return dao;
	}
	
	public String select(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=JdbcUtil.getConn();
			String sql="select id from members id=?";		
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			return null;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}	
	}
	
	public int insert(NquireVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=JdbcUtil.getConn();
			String sql="insert into nquire values(nquire_num_seq.nextval,?,?,?,sysdate,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContents());
			pstmt.setString(4, vo.getComments());
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
		
		
		
	}
}
