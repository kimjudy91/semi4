package board_f_dao.yun;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board_f_vo.yun.Recommended_folk_Vo;
import jdbc.JdbcUtil;

public class Recommended_folk_Dao {
	private static Recommended_folk_Dao dao=new Recommended_folk_Dao();
	private Recommended_folk_Dao() {}
	public static Recommended_folk_Dao getDao() {
		return dao;
	}
	public ArrayList<Recommended_folk_Vo> list(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from " +
					   "(" +
					   "select write_num,genre_num,likes " +
					   "from music_file " +
					   "where genre_num=2 " +
					   "order by likes desc" +
					   ") " +
					   "where rownum<4";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<Recommended_folk_Vo> list=new ArrayList<Recommended_folk_Vo>();
			while(rs.next()) {
				int write_num=rs.getInt("write_num");
				int genre_num=rs.getInt("genre_num");
				int likes=rs.getInt("likes");
				Recommended_folk_Vo vo=new Recommended_folk_Vo(write_num, genre_num, likes);
				list.add(vo);
			}
				return list;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
}