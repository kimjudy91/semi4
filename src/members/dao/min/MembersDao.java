package members.dao.min;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import members.vo.min.MembersVo;

public class MembersDao {
	
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
			pstmt.setInt(4, vo.getJumin());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getAddress());
			pstmt.setString(7, vo.getPhone());
			pstmt.setInt(8, vo.getWrite_count());
			pstmt.setInt(9, vo.getReply_count());
			pstmt.setInt(10, vo.getGrade());
			pstmt.setInt(11, vo.getWarning());
			pstmt.setInt(12, vo.getGenre_num());
			return pstmt.executeUpdate();			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}

}