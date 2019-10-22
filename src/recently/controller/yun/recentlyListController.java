package recently.controller.yun;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_f_dao.yun.RecentlyDao;
import board_f_vo.yun.RecentlyVo;

@WebServlet("/recently")
public class recentlyListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<RecentlyVo> list=RecentlyDao.getDao().list();
		req.setAttribute("recently", list);
		

		req.setAttribute("page", "/recently/recently.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
	}
}