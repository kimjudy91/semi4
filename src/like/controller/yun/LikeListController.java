package like.controller.yun;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board_f_dao.yun.BoardFileDao;
import board_f_dao.yun.MainBoardFileDao;
import board_f_vo.yun.BoardFilevo;
import board_f_vo.yun.MainBoardFileVo;

@WebServlet("/likes")
public class LikeListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<MainBoardFileVo> list=MainBoardFileDao.getDao().list();
		req.setAttribute("likeslist", list);
		req.getRequestDispatcher("/likes/likes.jsp").forward(req, resp);
	
		
	}
}
