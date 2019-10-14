package board.controller.myoung;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import board.vo.myoung.GenreVo;
import jdbc.JdbcUtil;

public class GenreDao {

	public String GenreSelect(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
		con=JdbcUtil.getConn();
		String sql="select genre_name from genre where genre_num=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, num);
		rs=pstmt.executeQuery();
		if (rs.next()) {
			String genre_name=rs.getString("genre_name");
			return genre_name;
		}
		return null;
			
		} catch (SQLException se) {
		System.out.println(se.getMessage());
		return null;
		}finally{
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	
	
	
}
