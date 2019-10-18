package board_f_dao.yun;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import board_f_vo.yun.MainBoardFileVo;
import board_f_vo.yun.RecentlyVo;
import jdbc.JdbcUtil;

public class RecentlyDao {
	private static RecentlyDao dao=new RecentlyDao();
	private RecentlyDao() {}
	public static RecentlyDao getDao() {
		return dao;
	}
	public ArrayList<RecentlyVo> list(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from " +
					   "(" +
					   "select write_num, p_title, id, r_date " +
					   "from music_file " +
					   "order by r_date desc" +
					   ") " +
					   "where rownum<6";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<RecentlyVo> list=new ArrayList<RecentlyVo>();
			while(rs.next()) {
				int write_num=rs.getInt("write_num");
				String id=rs.getString("id");
				String p_title=rs.getString("p_title");
				Date r_date=rs.getDate("r_date");
				RecentlyVo vo=new RecentlyVo(write_num, id, p_title, r_date);
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
