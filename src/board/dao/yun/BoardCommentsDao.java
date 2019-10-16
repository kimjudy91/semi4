package board.dao.yun;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.vo.yun.BoardCommentsVo;
import jdbc.JdbcUtil;


public class BoardCommentsDao {
	private static BoardCommentsDao dao=new BoardCommentsDao();
	public static BoardCommentsDao getCommentsDao() {
		return dao;
	}
	public ArrayList<BoardCommentsVo> getCommList(int write_num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=JdbcUtil.getConn();
			String sql="select * from comments where write_num =? order by lev asc , step desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, write_num);
			rs=pstmt.executeQuery();
			ArrayList<BoardCommentsVo> list=new ArrayList<BoardCommentsVo>();
			while(rs.next()) {
				String id=rs.getString("id");
				String comments_contents=rs.getString("comments_contents");
				Date comments_date=rs.getDate("comments_date");
				int comments_num=rs.getInt("comments_num");
				int ref=rs.getInt("ref");
				int lev=rs.getInt("lev");
				int step=rs.getInt("step");
				BoardCommentsVo vo=new BoardCommentsVo(comments_num, write_num, id, comments_contents, comments_date, ref, lev, step);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	public int maxRef (int write_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=JdbcUtil.getConn();
			String sql="select max(ref) from comments where write_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, write_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return -1;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	public int insert(BoardCommentsVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=JdbcUtil.getConn();
			String sql="insert into comments values(comments_num_seq.nextval,?,?,?,sysdate,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getWrite_num());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3,vo.getComments_contents());
			pstmt.setInt(4, vo.getRef());
			pstmt.setInt(5, vo.getLev());
			pstmt.setInt(6, vo.getStep());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
}


































