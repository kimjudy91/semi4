package board_f_dao.yun;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board_f_vo.yun.Recommended_RB_Vo;
import jdbc.JdbcUtil;

public class Recommended_RB_Dao {
	private static Recommended_RB_Dao dao=new Recommended_RB_Dao();
	private Recommended_RB_Dao() {}
	public static Recommended_RB_Dao getDao() {
		return dao;
	}
	public ArrayList<Recommended_RB_Vo> list(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from " +
					   "(" +
					   "select write_num,genre_num,likes " +
					   "from music_file " +
					   "where genre_num=3 " +
					   "order by likes desc" +
					   ") " +
					   "where rownum<4";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<Recommended_RB_Vo> list=new ArrayList<Recommended_RB_Vo>();
			while(rs.next()) {
				int write_num=rs.getInt("write_num");
				int genre_num=rs.getInt("genre_num");
				int likes=rs.getInt("likes");
				Recommended_RB_Vo vo=new Recommended_RB_Vo(write_num, genre_num, likes);
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