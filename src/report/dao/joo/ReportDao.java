package report.dao.joo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import report2.vo.min.Report2Vo;

public class ReportDao {
	private static ReportDao dao=new ReportDao();
	private ReportDao() {}
	public static ReportDao getDao() {
		return dao;
	}
	public int insertReport2(String id,int write_num,String report_content) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=JdbcUtil.getConn();
			String sql="insert into report2 values(rnum_seq.nextval,?,?,?,null)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, write_num);
			pstmt.setString(3, report_content);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
	public int newReport2Count() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=JdbcUtil.getConn();
			String sql="select NVL(comments,'a')  from report2";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			int newReport2Count=0;
			while(rs.next()) {
				if(rs.getString(1).equals("a")) {
					newReport2Count++;
				}
			}
			return newReport2Count;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	public ArrayList<Report2Vo> listReport2(int startRow, int endRow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=JdbcUtil.getConn();
			String
			sql="select * from" + 		
					"    (" + 
					"        select aa.*,rownum rrnum from" + 
					"        (" + 
					"            select * from report2 order by rnum desc" + 
					"        )aa" + 
					")where rrnum>=? and  rrnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,startRow);
			pstmt.setInt(2,endRow);
			rs=pstmt.executeQuery();
			ArrayList<Report2Vo> listReport2=new ArrayList<Report2Vo>();
			while(rs.next()) {
				int rnum=rs.getInt("rnum");
				String id=rs.getString("id");
				int write_num=rs.getInt("write_num");
				String report_content=rs.getString("report_content");
				String comments=rs.getString("comments");
				Report2Vo vo=new Report2Vo(rnum, id, write_num, report_content, comments);
				listReport2.add(vo);
			}
			return listReport2;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	public Report2Vo searchReport2(int rnum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=JdbcUtil.getConn();
			String sql="select * from report2 where rnum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, rnum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String id=rs.getString("id");
				int write_num=rs.getInt("write_num");
				String report_content=rs.getString("report_content");
				Report2Vo vo=new Report2Vo(rnum, id, write_num, report_content, null);
				return vo;
			}
			return null;
		}catch(SQLException  se) {
			se.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	public int updateComments(int rnum,String comments) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=JdbcUtil.getConn();
			String sql="update report2 set comments=? where rnum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, comments);
			pstmt.setInt(2, rnum);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
	public ArrayList<Report2Vo> searchListReport2(String id){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=JdbcUtil.getConn();
			String sql="select * from report2 where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			ArrayList<Report2Vo> listReport2=new ArrayList<Report2Vo>();
			while(rs.next()) {
				int rnum=rs.getInt("rnum");
				int write_num=rs.getInt("write_num");
				String report_content=rs.getString("report_content");
				String comments=rs.getString("comments");
				Report2Vo vo=new Report2Vo(rnum, id, write_num, report_content, comments);
				listReport2.add(vo);
			}
			return listReport2;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	public int getCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select NVL(count(*),0) from report2";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int cnt=rs.getInt(1);
				return cnt;
			}
			return 0;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
}






































