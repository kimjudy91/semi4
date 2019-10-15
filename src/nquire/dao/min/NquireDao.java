package nquire.dao.min;

import java.sql.Connection;
import java.sql.Date;
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
	
	public NquireVo select(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=JdbcUtil.getConn();
			String sql="select * from members where id=?";		
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int nquire_num=rs.getInt("nquire_num");
				String title=rs.getString("title");
				String contents=rs.getString("contents");
				Date r_date=rs.getDate("r_date");
				String comments=rs.getString("comments");
				NquireVo vo=new NquireVo(nquire_num, id, title, contents, r_date, comments);
				return vo;			
			}
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
