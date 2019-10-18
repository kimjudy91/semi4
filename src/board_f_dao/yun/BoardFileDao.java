package board_f_dao.yun;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import board_f_vo.yun.BoardFilevo;
import jdbc.JdbcUtil;

public class BoardFileDao {
	private static BoardFileDao dao=new BoardFileDao();
	private BoardFileDao() {}
	public static BoardFileDao getDao() {
		return dao;
	}
	public ArrayList<BoardFilevo> list(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from " +
					   "(" +
					   "select write_num, p_title, id, likes " +
					   "from music_file " +
					   "order by likes desc" +
					   ") " +
					   "where rownum<=11";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<BoardFilevo> list=new ArrayList<BoardFilevo>();
			while(rs.next()) {
				int write_num=rs.getInt("write_num");
				String id=rs.getString("id");
				int f_num=rs.getInt("f_num");
				String p_title=rs.getString("p_title");
				String contents=rs.getString("contents");
				Date r_date=rs.getDate("r_date");
				int views=rs.getInt("views");
				int likes=rs.getInt("likes");
				int genre_num=rs.getInt("genre_num");
				BoardFilevo vo=new BoardFilevo(write_num, id, f_num, p_title, contents, r_date, views, likes, genre_num);
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
