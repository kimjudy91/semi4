package board_f_dao.yun;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import board_f_vo.yun.RecentlyMusicVo;
import jdbc.JdbcUtil;

public class RecentlyMusicDao {
	private static RecentlyMusicDao dao=new RecentlyMusicDao();
	private RecentlyMusicDao() {}
	public static RecentlyMusicDao getDao() {
		return dao;
	}
	public ArrayList<RecentlyMusicVo> list(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from " +
					   "(" +
					   "select write_num, p_title, id, r_date " +
					   "from music " +
					   "order by r_date desc " +
					   ") " +
					   "where rownum<6";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<RecentlyMusicVo> list=new ArrayList<RecentlyMusicVo>();
			while(rs.next()) {
				int write_num=rs.getInt("write_num");
				String id=rs.getString("id");
				String p_title=rs.getString("p_title");
				Date r_date=rs.getDate("r_date");
				RecentlyMusicVo vo=new RecentlyMusicVo(write_num, id, p_title, r_date);
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
