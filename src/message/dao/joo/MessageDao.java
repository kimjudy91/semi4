package message.dao.joo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import message.vo.joo.FriendsVo;
import message.vo.joo.MessageVo;

public class MessageDao {
	private static MessageDao dao=new MessageDao();
	public MessageDao() {}
	public static MessageDao getDao() {
		return dao;
	}
	public ArrayList<MessageVo> msgDetailList(String ssid,String rrid){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=JdbcUtil.getConn();
			String sql="select * from message where (sid=? and rid=?) or (sid=? and rid=?)  order by message_num asc";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, ssid);
			pstmt.setString(2, rrid);
			pstmt.setString(3, rrid);
			pstmt.setString(4, ssid);
			rs=pstmt.executeQuery();
			setChecking(rrid, ssid);
			ArrayList<MessageVo> list=new ArrayList<MessageVo>();
			while(rs.next()) {
				int message_num=rs.getInt("message_num");
				String contents=rs.getString("contents");
				int checking=rs.getInt("checking");
				String sid=rs.getString("sid");
				String rid=rs.getString("rid");
				MessageVo vo=new MessageVo(message_num, sid, rid, contents, checking);
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
	public ArrayList<String> getMsgList(String sid){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=JdbcUtil.getConn();
			String sql="select rid from message where sid=? UNION select sid from message where rid= ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, sid);
			pstmt.setString(2, sid);
			rs=pstmt.executeQuery();
			ArrayList<String> list=new ArrayList<String> ();
			while(rs.next()) {
				String ssid=rs.getString(1);
				list.add(ssid);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	public ArrayList<FriendsVo> friednsList(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=JdbcUtil.getConn();
			String sql="select * from friends ";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<FriendsVo> list=new ArrayList<FriendsVo>();
			while(rs.next()) {
				int friends_num=rs.getInt("friends_num");
				String sid=rs.getString("sid");
				String rid=rs.getString("rid");
				int commit=rs.getInt("commit");
				FriendsVo vo=new FriendsVo(friends_num, sid, rid, commit);
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
	public int insertfri(String sid,String rid) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=JdbcUtil.getConn();
			String sql="insert into friends values(friends_num_seq.nextval,?,?,0)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, sid);
			pstmt.setString(2, rid);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
	public int acceptfir(String sid,String rid) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=JdbcUtil.getConn();
			String sql="update friends set commit=1 where sid=? and rid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, sid);
			pstmt.setString(2, rid);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
	public int checkfri(String sid,String rid) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=JdbcUtil.getConn();
			String sql="select * from friends where rid=? and sid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, sid);
			pstmt.setString(2, rid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return 1;
			}
			return -1;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	public int getRevFriCount(String rid) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=JdbcUtil.getConn();
			String sql="select count(*) from friends where rid=? and commit=0";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, rid);
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
	public int insertMsg(String sid,String rid,String contents) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			if(contents.equals("")||contents==null) {
				return -1;
			}
			con=JdbcUtil.getConn();
			String sql="insert into message values(message_num_seq.nextval,?,?,?,0)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, sid);
			pstmt.setString(2, rid);
			pstmt.setString(3, contents);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
	public int newMsgs(String sid,ArrayList<String> list) {
		int count=0;
		for (int i = 0; i < list.size(); i++) {
			count+=newMsg(sid, list.get(i));
		}
		return count;
	}
	public int newMsg(String rid,String sid) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=JdbcUtil.getConn();
			String sql="select count(*) from message where sid=? and rid=? and checking=0";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, sid);
			pstmt.setString(2, rid);
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
	public int setChecking(String sid,String rid) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=JdbcUtil.getConn();
			String sql="update message set checking=1 where sid=? and rid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, sid);
			pstmt.setString(2, rid);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
}

























































