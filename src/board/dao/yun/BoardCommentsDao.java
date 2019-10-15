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
	private static BoardCommentsDao commentsDao=new BoardCommentsDao();
	public static BoardCommentsDao getCommentsDao() {
		return commentsDao;
	}
	private BoardCommentsDao() {}
	public int delete(int comments_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql= "delete from comments where comments_num=?";
		try {
			con=JdbcUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,comments_num);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt,null);
		}	
	}
	
	public int insert(BoardCommentsVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="insert into comments values(comments_num_seq.nextval,?,?,?,sysdate,?,?,?)";
		try {
			con=JdbcUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getWrite_num());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getComments_contents());
			pstmt.setInt(4, vo.getRef());
			pstmt.setInt(5, vo.getLev());
			pstmt.setInt(6, vo.getStep());
			return pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
	public ArrayList<BoardCommentsVo> list(int write_num){
		String sql="select * from comments where write_num=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, write_num);
			rs=pstmt.executeQuery();
			ArrayList<BoardCommentsVo> commList=new ArrayList<BoardCommentsVo>();
			while(rs.next()) {
				BoardCommentsVo vo=new BoardCommentsVo(
						rs.getInt("comments_num"),
						write_num,
						rs.getString("id"),
						rs.getString("comments_contents"),
						rs.getDate("comments_date"),
						rs.getInt("ref"),
						rs.getInt("lev"),
						rs.getInt("step"));
				commList.add(vo);
			}
			return commList;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
}
	
	
	
/*	
	//댓글에 댓글//
	//가장 큰 글번호 얻어오기
	public int getMaxNum() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
		con=JdbcUtil.getConn();
		String sql="select NVL(max(comments_num),0) as maxnum from comments";
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			int comments_num=rs.getInt("maxnum");
			return comments_num;
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

	public int commentsinsert(BoardCommentsVo vo) {
			Connection con=null;
			PreparedStatement pstmt=null; 
			PreparedStatement pstmt1=null; 
		try {
			con=JdbcUtil.getConn();
			int boardNum=getMaxNum() +1 ; 
			int comments_num=vo.getComments_num(); 
			int ref=vo.getRef();
			int lev=vo.getLev();
			int step=vo.getStep();
			if(comments_num==0) { 
				ref=boardNum;
			}else { 
				String sql1="update comments set step=step+1 where ref=? and step>?";
				pstmt1=con.prepareStatement(sql1);
				pstmt1.setInt(1, ref);
				pstmt1.setInt(2, step);
				pstmt1.executeUpdate();
				lev=lev+1;
				step=step+1;
			}
			String sql="insert into comments values(?,?,?,?,,sysdate,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			pstmt.setInt(2, vo.getWrite_num());
			pstmt.setString(3, vo.getId());
			pstmt.setString(4, vo.getComments_contents());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, lev);
			pstmt.setInt(7, step);
			return pstmt.executeUpdate();
		
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con, pstmt1, null);
			
		}
	}
	

	public ArrayList<BoardCommentsVo> list(int startRow, int endRow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from " +
					"(" +
					" select aa.*,rownum rnum from" +
					" (" +
					"	select * from comments" +
					"   order by ref desc, step asc" +
					" )aa" +
					" ) where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<BoardCommentsVo> list=new ArrayList<BoardCommentsVo>();
			while(rs.next()) {
				int comments_num=rs.getInt("comments_num");
				int write_num=rs.getInt("write_num");
				String id=rs.getString("id");
				String comments_contents=rs.getString("comments_contents");
				Date comments_date=rs.getDate("comments_date");
				int ref=rs.getInt("ref");
				int lev=rs.getInt("lev");
				int step=rs.getInt("step");
				BoardCommentsVo vo=new BoardCommentsVo(comments_num, write_num, id, comments_contents, comments_date, ref, lev, step);
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
	
	//전체글의 갯수 구하기
	public int getCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
		con=JdbcUtil.getConn();
		String sql="select NVL(count(comments_num),0) from comments";
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		if(rs.next()) {
				int comments_num=rs.getInt(1);
				return comments_num;
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
	public BoardCommentsVo detail(int comments_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
		con=JdbcUtil.getConn();
		String sql="select * from comments where comments_num=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, comments_num);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			int write_num=rs.getInt("write_num");
			String id=rs.getString("id");
			String comments_contents=rs.getString("comments_contents");
			Date comments_date=rs.getDate("comments_date");
			int ref=rs.getInt("ref");
			int lev=rs.getInt("lev");
			int step=rs.getInt("step");
			BoardCommentsVo vo=new BoardCommentsVo(comments_num, write_num, id, comments_contents, comments_date, ref, lev, step);		
			return vo;
			}
			return null;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
}
*/
