package board_f_dao.yun;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import board_f_vo.yun.MainBoardFileVo;
import jdbc.JdbcUtil;

public class MainBoardFileDao {
	private static MainBoardFileDao dao=new MainBoardFileDao();
	private MainBoardFileDao() {}
	public static MainBoardFileDao getDao() {
		return dao;
	}
	public ArrayList<MainBoardFileVo> list(){
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
			ArrayList<MainBoardFileVo> list=new ArrayList<MainBoardFileVo>();
			while(rs.next()) {
				int write_num=rs.getInt("write_num");
				String id=rs.getString("id");
				String p_title=rs.getString("p_title");
				int likes=rs.getInt("likes");
				MainBoardFileVo vo=new MainBoardFileVo(write_num, id, p_title, likes);
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
