package report2.dao.min;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;

public class Report2Dao{
	
	public int select(int rnum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=JdbcUtil.getConn();
			String sql="select * from music where id=? and write_num=?";
			pstmt=con.prepareStatement(sql);
			if(rs.next()) {
				String id=rs.getString("id");
				int write_num=rs.getInt("write_num);
					
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}finally{
			JdbcUtil.close(con, pstmt, rs);
		}
	}
}