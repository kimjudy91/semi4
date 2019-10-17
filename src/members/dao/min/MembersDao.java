package members.dao.min;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;

import members.vo.min.MembersVo;

public class MembersDao {
	private static MembersDao dao=new MembersDao();
	private MembersDao() {}
	public static MembersDao getDao() {
		return dao;
	}
	
	public int insert(MembersVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=JdbcUtil.getConn();
			String sql="insert into members values(?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress());
			pstmt.setString(6, vo.getPhone());
			pstmt.setInt(7, vo.getWrite_count());
			pstmt.setInt(8, vo.getReply_count());
			pstmt.setInt(9, vo.getGrade());
			pstmt.setInt(10, vo.getWarning());
			pstmt.setInt(11, vo.getGenre_num());
			pstmt.setInt(12, vo.getJumin());
			return pstmt.executeUpdate();			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
	public MembersVo search(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
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
			se.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	
}































