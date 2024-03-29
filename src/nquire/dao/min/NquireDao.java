package nquire.dao.min;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import members.vo.min.MembersVo;
import nquire.vo.min.NquireVo;

public class NquireDao {
	
	private static NquireDao dao=new NquireDao();
	private NquireDao() {}
	public static NquireDao getDao() {
		return dao;
	}
	
	// nquire테이블에서 글번호 select
	public NquireVo selectNquire(int nquire_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=JdbcUtil.getConn();
			String sql="select * from nquire where nquire_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, nquire_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String id=rs.getString("id");
				String title=rs.getString("title");
				String contents=rs.getString("contents");
				Date r_date=rs.getDate("r_date");
				String comments=rs.getString("comments");
				NquireVo vo=new NquireVo(nquire_num, id, title, contents, r_date, comments);
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
	
	// members테이블에서 회원아이디 select
	public MembersVo select(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=JdbcUtil.getConn();
			String sql="select * from members where id=?";		
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String pwd=rs.getString("pwd");
				String name=rs.getString("name");
				String email=rs.getString("email");
				String address=rs.getString("address");
				String phone=rs.getString("phone");
				int write_count=rs.getInt("write_count");
				int reply_count=rs.getInt("reply_count");
				int grade=rs.getInt("grade");
				int warning=rs.getInt("warning");
				int genre_num=rs.getInt("genre_num");
				int jumin=rs.getInt("jumin");
				MembersVo vo=new MembersVo(id, pwd, name, email, address, phone, write_count, reply_count, grade, warning, genre_num, jumin);
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
	
	// 문의게시판 글insert
	public int insert(NquireVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=JdbcUtil.getConn();
			String sql="insert into nquire values(nquire_num_seq.nextval,?,?,?,sysdate,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContents());
			pstmt.setString(4, vo.getComments());
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}	
	}
	
	// 문의게시판 list (페이징)
	public ArrayList<NquireVo> list(String id,int startRow, int endRow) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=JdbcUtil.getConn();
			
			String sql="";
			if(id==null) {
			sql="select * from (" +
								" select aa.*, rownum rnum from" +
								" (" +
								"     select * from nquire" +
								"     order by nquire_num desc" +
								" )aa" +
								") where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			}else {
				sql="select * from (" +
						" select aa.*, rownum rnum from" +
						" (" +
						"     select * from nquire where id=?" +
						"     order by nquire_num desc" +
						" )aa" +
						") where rnum>=? and rnum<=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			}
			
			rs=pstmt.executeQuery();
			ArrayList<NquireVo> list=new ArrayList<NquireVo>();
			while(rs.next()) {
				int nquire_num=rs.getInt("nquire_num");
				id=rs.getString("id");
				String title=rs.getString("title");
				String contents=rs.getString("contents");
				Date r_date=rs.getDate("r_date");		
				String comments=rs.getString("comments");
				NquireVo vo=new NquireVo(nquire_num, id, title, contents, r_date, comments);
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
	
	// 문의게시판 글 상세보기
	public NquireVo detail(int nquire_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=JdbcUtil.getConn();
			String sql="select * from nquire where nquire_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, nquire_num);
			rs=pstmt.executeQuery();
		
			if(rs.next()) {
				nquire_num=rs.getInt("nquire_num");
				String id=rs.getString("id");
				String title=rs.getString("title");
				String contents=rs.getString("contents");
				Date r_date=rs.getDate("r_date");		
				String comments=rs.getString("comments");
				NquireVo vo=new NquireVo(nquire_num, id, title, contents, r_date, comments);
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
	
	// 문의게시판 답변
	public int updateComments(int nquire_num, String comments) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=JdbcUtil.getConn();
			String sql="update nquire set comments=? where nquire_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, comments);
			pstmt.setInt(2, nquire_num);
			return pstmt.executeUpdate();
					
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
	
	// 페이지 count
	public int getCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=JdbcUtil.getConn();
			String sql="select NVL(count(nquire_num),0) from nquire";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int nquire_num=rs.getInt(1);
				return nquire_num;
			}else {
				return 0;
			}
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
}
