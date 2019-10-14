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

}