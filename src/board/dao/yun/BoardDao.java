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
	public BoardDao() {}
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
			if(field==null || field.equals("")) { //검색조건이 없는 경우
				sql="select * from " +
					"(" +
					" select aa.*, rownum rnum from " + 
					" (" +
					" select * from music order by write_num desc" +
					" )aa" +
					" ) where rnum>=? and rnum<=?";
			}else if(field.equals("TC")) { //검색조건이 있는 경우 (제목+내용)
				sql="select * from " +
					"(" +
					" select aa.*, rownum rnum from " +
					" (" +
					" select * from music " +
					" where p_title like '%"+ keyword+"%' or contents like '%" +keyword+
					"%' order by write_num desc " +
					" )aa" +
					" ) where rnum>=? and rnum<=?";
			}else { //검색조건이 있는 경우 (제목, 내용, 작성자)
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
				int genre=rs.getInt("genre_num");
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
			if(field!=null && field.equals("TC")) {
				sql += " where p_title like '%" + keyword + "%' or contents like '%"+ keyword+"%'";
			}
			else if(field!=null && !field.equals("")) {
				sql += " where " + field + " like '%" + keyword + "%'";
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
	public int insert(BoardVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();	
			String sql="insert into music values(write_num_seq.nextval,?,?,?,sysdate,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getP_title());
			pstmt.setString(3, vo.getContents());
			pstmt.setInt(4, vo.getViews());
			pstmt.setInt(5, vo.getGenre_num());
			return pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
	public BoardVo detail(int write_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from music where write_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, write_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String id=rs.getString("id");
				String p_title=rs.getString("p_title");
				String contents=rs.getString("contents");
				Date r_date=rs.getDate("r_date");
				int views=rs.getInt("views");
				views ++;
				sql="update music set views=? where write_num=?";
				pstmt2=con.prepareStatement(sql);
				pstmt2.setInt(1, views);
				pstmt2.setInt(2, write_num);
				pstmt2.executeUpdate();
				int genre_num=rs.getInt("genre_num");
				BoardVo vo=new BoardVo(write_num, id, p_title, contents, r_date, views, genre_num);
				return vo;
			}
			return null;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	
	public int update(BoardVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;

		try {
			con=JdbcUtil.getConn();
			String sql="update music set contents=?, p_title=? where write_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getContents());
			pstmt.setString(2, vo.getP_title());	
			pstmt.setInt(3, vo.getWrite_num());			
			return pstmt.executeUpdate();
		
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
	
	public BoardVo select(int write_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from music where write_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,write_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String id=rs.getString("id");
				String p_title=rs.getString("p_title");
				String contents=rs.getString("contents");
				Date r_date=rs.getDate("r_date");
				int views=rs.getInt("views");
				int genre_num=rs.getInt("genre_num");
				BoardVo vo=new BoardVo(write_num, id, p_title, contents, r_date, views, genre_num);
				return vo;
			}
			return null;
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	
	public int delete(int write_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		try {
			con=JdbcUtil.getConn();
			String sql1="delete from comments where write_num=?";
			pstmt1=con.prepareStatement(sql1);
			pstmt1.setInt(1,write_num);
			pstmt1.executeUpdate();
			String sql2="delete from report2 where write_num=?";
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setInt(1,write_num);
			pstmt2.executeUpdate();
			String sql3="delete from music where write_num=?";
			pstmt=con.prepareStatement(sql3);
			pstmt.setInt(1,write_num);
			return pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
	public int increWriteCount(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=JdbcUtil.getConn();
			String sql="update members set write_count=? where id=?";
			int n=getWriteCount(id);
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, n+1);
			pstmt.setString(2, id);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}
	}
	public int getWriteCount(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=JdbcUtil.getConn();
			String sql="select write_count from members where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("write_count");
			}
			return -1;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	public int increGrade(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=JdbcUtil.getConn();
			String sql="update members set grade=? where id=?";
			int n=getGrade(id);
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, n+1);
			pstmt.setString(2, id);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}
	}
	public int getGrade(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=JdbcUtil.getConn();
			String sql="select grade from members where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("grade");
			}
			return -1;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
}











