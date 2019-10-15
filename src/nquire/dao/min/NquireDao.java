package nquire.dao.min;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import members.vo.min.MembersVo;
import nquire.vo.min.NquireVo;

public class NquireDao {
	
	private static NquireDao dao=new NquireDao();
	private NquireDao() {}
	public static NquireDao getDao() {
		return dao;
	}
	
	public MembersVo select(String id) {
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
				String pwd=rs.getString("pwd");
				String name=rs.getString("name");
				String email=rs.getString("email");
				String address=rs.getString("address");
				String phone=rs.getString("phone");
				int write_count=rs.getInt("write_count");
				int reply_count=rs.getInt("reply_count");
				int grade=rs.getInt("grade");
				int warning=rs.getInt("warning");
				int genre_num=rs.getInt("genre_num");
				int jumin=rs.getInt("jumin");
				MembersVo vo=new MembersVo(id, pwd, name, email, address, phone, write_count, reply_count, grade, warning, genre_num, jumin);
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
