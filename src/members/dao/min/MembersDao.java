package members.dao.min;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;

import members.vo.min.MembersVo;

public class MembersDao {
	private static MembersDao dao=new MembersDao();
	private MembersDao() {}
	public static MembersDao getDao() {
		return dao;
	}
	
	public int insert(MembersVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=JdbcUtil.getConn();
			String sql="insert into members values(?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress());
			pstmt.setString(6, vo.getPhone());
			pstmt.setInt(7, vo.getWrite_count());
			pstmt.setInt(8, vo.getReply_count());
			pstmt.setInt(9, vo.getGrade());
			pstmt.setInt(10, vo.getWarning());
			pstmt.setInt(11, vo.getGenre_num());
			pstmt.setInt(12, vo.getJumin());
			return pstmt.executeUpdate();			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
	public MembersVo search(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
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
			se.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	
	//회원관리 모든목록 list
	public ArrayList<MembersVo> list(int startRow, int endRow, String field, String keyword){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="";
			if(field==null || field.equals("")) {
				sql="select * from " +
						"(" +
						" select aa.*, rownum rnum from " + 
						" (" +
						" select * from members order by id desc" +
						" )aa" +
						" ) where rnum>=? and rnum<=?";
			}else if(field.equals("grade")){
				if(keyword.equals("브론즈")) {
					keyword="1";
				}else if(keyword.equals("실버")) {
					keyword="2";
				}else if(keyword.equals("골드")) {
					keyword="3";	
				}
				sql="select * from " +
						"(" +
						" select aa.* , rownum rnum from " +
						" (" +
						" select * from members " +
						" where " + field + " like '%" + keyword + "%'" +
						" order by id desc " +
						" )aa" +
						" ) where rnum>=? and rnum<=?" ;
	
			}else {
				sql="select * from " +
						"(" +
						" select aa.* , rownum rnum from " +
						" (" +
						" select * from members " +
						" where " + field + " like '%" + keyword + "%'" +
						" order by id desc " +
						" )aa" +
						" ) where rnum>=? and rnum<=?" ;
	
			}
					
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<MembersVo> list=new ArrayList<MembersVo>();
			while(rs.next()) {
				String id=rs.getString("id");
				String pwd=rs.getString("pwd");
				String name=rs.getString("name");
				String email=rs.getString("email");
				String address=rs.getString("address");
				String phone=rs.getString("phone");
				int write_count =rs.getInt("write_count");
				int reply_count =rs.getInt("reply_count");
				int grade=rs.getInt("grade");
				int warning=rs.getInt("warning");
				int genre_num=rs.getInt("genre_num");
				int jumin=rs.getInt("jumin");
				MembersVo vo=new MembersVo(id, pwd, name, email, address, phone, write_count, reply_count, grade, warning, genre_num, jumin);
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
	public int getCount(String field, String keyword) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select NVL(count(id),0) from members";
			if(field!=null && !field.equals("")) {
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
	public MembersVo detail(String id) {
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
				int write_count =rs.getInt("write_count");
				int reply_count =rs.getInt("reply_count");
				int grade=rs.getInt("grade");
				int warning=rs.getInt("warning");
				int genre_num=rs.getInt("genre_num");
				int jumin=rs.getInt("jumin");
				MembersVo vo1=new MembersVo(id, pwd, name, email, address, phone, write_count, reply_count, grade, warning, genre_num, jumin);
				return vo1;
			}
			return null;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	
	

	
	public int IncreaseCount(String id) {
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
	
	public int increaseWarning(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="update members set warning=warning+1 where id=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			return pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
	public int downWarning(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="update members set warning=warning-1 where id=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			return pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
}































