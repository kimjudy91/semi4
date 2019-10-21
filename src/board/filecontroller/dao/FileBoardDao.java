package board.filecontroller.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.tomcat.util.http.fileupload.UploadContext;

import board.filecontroller.vo.FileBoardVo;
import board.filecontroller.vo.FileUpLoadVo;
import board.vo.yun.BoardVo;
import jdbc.JdbcUtil;



public class FileBoardDao {
private static FileBoardDao instance=new FileBoardDao();
public FileBoardDao() {}
public static FileBoardDao getinstance() {
	return instance;
}

public ArrayList<FileBoardVo> list(int startRow, int endRow, String field, String keyword){
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
				" select * from music_file order by write_num desc" +
				" )aa" +
				" ) where rnum>=? and rnum<=?";
		}else if(field.equals("TC")) { //검색조건이 있는 경우 (제목+내용)
			sql="select * from " +
				"(" +
				" select aa.*, rownum rnum from " +
				" (" +
				" select * from music_file " +
				" where p_title like '%"+ keyword+"%' or contents like '%" +keyword+
				"%' order by write_num desc " +
				" )aa" +
				" ) where rnum>=? and rnum<=?";
		}else { //검색조건이 있는 경우 (제목, 내용, 작성자)
			sql="select * from " +
				"(" +
				" select aa.* , rownum rnum from " +
				" (" +
				" select * from music_file " +
				" where " + field + " like '%" + keyword + "%'" +
				" order by write_num desc " +
				" )aa" +
				" ) where rnum>=? and rnum<=?" ;
		}
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, startRow);
		pstmt.setInt(2, endRow);
		rs=pstmt.executeQuery();
		ArrayList<FileBoardVo> list=new ArrayList<FileBoardVo>();
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
			FileBoardVo vo=new FileBoardVo(write_num, id, f_num, p_title, contents, r_date, views, likes, genre_num);
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



public int insert(FileUpLoadVo vo1,FileBoardVo vo) {
	Connection con=null;
	PreparedStatement pstmt=null;//파일게시판에 글 저장소에 저장할거
	PreparedStatement pstmt1=null;//업로드 게시판에 파일 저장할거
	
	
	try {
		con=JdbcUtil.getConn();	
		String sql1="insert into upload values(f_num_seq.nextval,?,?,?)";
		pstmt1=con.prepareStatement(sql1);
		pstmt1.setString(1, vo1.getOrgfilename());
		pstmt1.setString(2, vo1.getSavefilename());
		pstmt1.setInt(3, vo1.getFilesize());
		pstmt1.executeUpdate();
		
		
		String sql="insert into music_file values(write_num_seq.nextval,?,f_num_seq.currval,?,?,sysdate,?,?,?)";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getP_title());
		pstmt.setString(3, vo.getContents());
		pstmt.setInt(4, vo.getViews());
		pstmt.setInt(5, vo.getLikes());
		pstmt.setInt(6, vo.getGenre_num());
		return pstmt.executeUpdate();
	}catch(SQLException e) {
		System.out.println(e.getMessage());
		return -1;
	}finally {
		JdbcUtil.close(con, pstmt, null);
	}
}

public int update2(FileUpLoadVo vo3) {
	Connection con=null;
	
	PreparedStatement pstmt1=null;//업로드 게시판에 파일 저장할거
	
	
	try {
		con=JdbcUtil.getConn();	
		String sql1="update upload set Orgfilename = ?, Savefilename = ?, Filesize = ?  where  f_num = ?";
		
		pstmt1=con.prepareStatement(sql1);
		pstmt1.setString(1, vo3.getOrgfilename());
		pstmt1.setString(2, vo3.getSavefilename());
		pstmt1.setInt(3, vo3.getFilesize());
		pstmt1.setInt(4,vo3.getF_num());
		return pstmt1.executeUpdate();
		
	
		
	}catch(SQLException e) {
		System.out.println(e.getMessage());
		return -1;
	}finally {
		JdbcUtil.close(con, pstmt1, null);
	}
}

public int update3(int likess,int write_num) {
	Connection con=null;
	
	PreparedStatement pstmt=null;// 게시판에 좋아요
	
	try {
		con=JdbcUtil.getConn();	
		int likes=likess+1;
		String sql="update music_file set  likes= ?  where  write_num = ?";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, likes);
		pstmt.setInt(2, write_num);

		return pstmt.executeUpdate();
		
	
		
	}catch(SQLException e) {
		System.out.println(e.getMessage());
		return -1;
	}finally {
		JdbcUtil.close(con, pstmt, null);
	}
}
	
public FileBoardVo detail(int write_num) {
	Connection con=null;
	PreparedStatement pstmt=null;
	PreparedStatement pstmt2=null;
	ResultSet rs=null;
	try {
		con=JdbcUtil.getConn();
		String sql="select * from music_file where write_num=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, write_num);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			String id=rs.getString("id");
			String p_title=rs.getString("p_title");
			int f_num=rs.getInt("f_num");
			String contents=rs.getString("contents");
			Date r_date=rs.getDate("r_date");
			int views=rs.getInt("views");
			int genre_num=rs.getInt("genre_num");
			int likes=rs.getInt("likes");
			views ++;
			sql="update music_file set views=? where write_num=?";
			pstmt2=con.prepareStatement(sql);
			pstmt2.setInt(1, views);
			pstmt2.setInt(2, write_num);
			pstmt2.executeUpdate();
			FileBoardVo vo=new FileBoardVo(write_num, id, f_num, p_title, contents, r_date, views, likes, genre_num);
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



public FileUpLoadVo detail1(int f_num) {
			Connection con=null;
		PreparedStatement pstmt=null;
			ResultSet rs=null;
				try {
			con=JdbcUtil.getConn();
			String sql1="select * from upload where f_num=?";
			pstmt=con.prepareStatement(sql1);
					pstmt.setInt(1, f_num);
		rs=pstmt.executeQuery();

			if(rs.next()) {
	String orgfilename=rs.getString("orgfilename");
	String savefilename=rs.getString("savefilename");
	int filesize=rs.getInt("filesize");
	FileUpLoadVo vo1=new FileUpLoadVo(f_num, orgfilename, savefilename, filesize);
	return vo1;
					}
					
		return null;
			}catch(SQLException e) {
						System.out.println(e.getMessage());
								return null;
				}finally {
						JdbcUtil.close(con,pstmt,rs);
					}
		}

	
	
	public int getCount(String field, String keyword) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select NVL(count(write_num),0) from music_file";
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
	
	public int update(FileBoardVo vo2) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			
			String sql="update music_file  set id=?,p_title=?,contents=? where write_num=? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo2.getId());
			pstmt.setString(2, vo2.getP_title());
			pstmt.setString(3, vo2.getContents());
			pstmt.setInt(4, vo2.getWrite_num());
			return pstmt.executeUpdate();
	
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
	
	public FileBoardVo select(int write_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from music_file where write_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,write_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String id=rs.getString("id");
				int f_num=rs.getInt("f_num");
				String p_title=rs.getString("p_title");
				String contents=rs.getString("contents");
				Date r_date=rs.getDate("r_date");
				int views=rs.getInt("views");
				int genre_num=rs.getInt("genre_num");
				FileBoardVo vo=new FileBoardVo(write_num, id, f_num, p_title, contents, r_date, views, 0, genre_num);
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
	
	
	public ArrayList<FileUpLoadVo> list(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from upload";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<FileUpLoadVo> list=new ArrayList<FileUpLoadVo>();
			while(rs.next()) {
				int f_num=rs.getInt("f_num");
				String orgfilename=rs.getString("orgfilename");					
				String savefilename=rs.getString("savefilename");
				int filesize=rs.getInt("filesize");
				FileUpLoadVo vo=
					new FileUpLoadVo(f_num, orgfilename, savefilename, filesize);
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

	public int delete(int write_num,int f_num) {
		Connection con=null;
		PreparedStatement pstmt=null; //본문 지우기
		PreparedStatement pstmt1=null;//파일지우기
		PreparedStatement pstmt2=null;//댓글지우기
		try {
			con=JdbcUtil.getConn();
			
			String sql2="delete from comment_file where write_num=?";
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setInt(1, write_num);
			pstmt2.executeUpdate();
			
			
			String sql="delete from music_file where write_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,write_num);
			 pstmt.executeUpdate();
			 
			String sql1="delete from upload where f_num=?";
			pstmt1=con.prepareStatement(sql1);
			pstmt1.setInt(1, f_num);	
			return pstmt1.executeUpdate();
			
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
			JdbcUtil.close(pstmt1);
		}
	}
	
	
	public FileUpLoadVo getinfo(int f_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from upload where f_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,f_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String orgfilename=rs.getString("orgfilename");					
				String savefilename=rs.getString("savefilename");
				int filesize=rs.getInt("filesize");
				FileUpLoadVo vo=
					new FileUpLoadVo(0, orgfilename, savefilename, filesize);
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
	
}









	

