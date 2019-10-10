package board.dao.yun;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.vo.yun.BoardVo;
import jdbc.JdbcUtil;

public class BoardDao {
	private static BoardDao instance=new BoardDao();
	private BoardDao() {}
	public static BoardDao getinstance() {
		return instance;
	}
	
	public ArrayList<BoardVo> list(int startRow, int endRow, String field, String keyword){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="";
			if(field==null || field.equals("")) { //검색조건이 없는 경우 (field가 없는경우)
				sql="select * from " +
					"(" +
					" select aa.*, rownum rnum from " + 
					" (" +
					" select * from music order by write_num desc" +
					" )aa" +
					" ) where rnum>=? and rnum<=?";
			}else { //검색조건이 있는 경우
				sql="select * from " +
					"(" +
					" select aa.* , rownum rnum from " +
					" (" +
					" select * from music " +
					" where " + field + " like '%" + keyword + "%'" +
					" order by write_num desc " +
					" )aa" +
					" ) where rnum>=? and rnum<=?" ;
			}
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<BoardVo> list=new ArrayList<BoardVo>();
			while(rs.next()) {
				int write_num=rs.getInt("write_num");
				String id=rs.getString("id");
				String p_title=rs.getString("p_title");
				String contents=rs.getString("contents");
				Date r_date=rs.getDate("r_date");
				int views=rs.getInt("views");
				String genre=rs.getString("genre");
				BoardVo vo=new BoardVo(write_num, id, p_title, contents, r_date, views, genre);
				list.add(vo);
			}
				return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	public int getCount(String field, String keyword) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select NVL(count(write_num),0) from music";
			if(field!=null && !field.equals("")) {
				sql += " where " + field + " like '&" + keyword + "&'";
			}
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int num=rs.getInt(1);
				return num;
			}else {
				return 0;
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
}
