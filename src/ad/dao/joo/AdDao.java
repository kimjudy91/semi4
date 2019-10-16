package ad.dao.joo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ad.vo.joo.AdVo;
import jdbc.JdbcUtil;

public class AdDao {
	private static AdDao dao=new AdDao();
	private AdDao() {}
	public static AdDao getDao() {
		return dao;
	}
	public ArrayList<AdVo> list(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=JdbcUtil.getConn();
			String sql="select * from advertise";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<AdVo> list=new ArrayList<AdVo>();
			while(rs.next()) {
				int ad_num=rs.getInt("ad_num");
				int ad_money=rs.getInt("ad_money");
				String ad_com=rs.getString("ad_com");
				String ad_image=rs.getString("ad_image");
				Date ad_start_date1=rs.getDate("ad_start_date");
				Date ad_end_date1=rs.getDate("ad_end_date");
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
				String ad_start_date = transFormat.format(ad_start_date1);
				String ad_end_date = transFormat.format(ad_end_date1);
				AdVo vo=new AdVo(ad_num, ad_money, ad_com, ad_image, ad_start_date, ad_end_date);
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
	public int insert(AdVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=JdbcUtil.getConn();
			String sql="insert into advertise values(ad_num_seq.nextval,?,?,?,TO_DATE(?,'YYYYMMDDHH24'),TO_DATE(?,'YYYYMMDDHH24'))";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getAd_money());
			pstmt.setString(2, vo.getAd_com());
			pstmt.setString(3, vo.getAd_image());
			pstmt.setString(4, vo.getAd_start_date());
			pstmt.setString(5, vo.getAd_end_date());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
}































