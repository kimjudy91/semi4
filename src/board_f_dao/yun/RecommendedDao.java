package board_f_dao.yun;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;

import board_f_vo.yun.RecommendedVo;
import jdbc.JdbcUtil;

public class RecommendedDao extends HttpServlet{
			private static RecommendedDao dao=new RecommendedDao();
			private RecommendedDao() {}
			public static RecommendedDao getDao() {
				return dao;
			}
			public ArrayList<RecommendedVo> list(){
				Connection con=null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				try {
					con=JdbcUtil.getConn();
					String sql="select * from " +
							   "(" +
							   "select write_num,genre_num,likes " +
							   "from music_file " +
							   "where genre_num=1 " +
							   "order by likes desc" +
							   ") " +
							   "where rownum<4";
					pstmt=con.prepareStatement(sql);
					rs=pstmt.executeQuery();
					ArrayList<RecommendedVo> list=new ArrayList<RecommendedVo>();
					while(rs.next()) {
						int write_num=rs.getInt("write_num");
						int genre_num=rs.getInt("genre_num");
						int likes=rs.getInt("likes");
						RecommendedVo vo=new RecommendedVo(write_num, genre_num, likes);
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
	}



	