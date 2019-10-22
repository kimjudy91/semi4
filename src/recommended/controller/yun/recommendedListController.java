package recommended.controller.yun;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_f_dao.yun.RecommendedDao;
import board_f_vo.yun.RecommendedVo;

@WebServlet("/recommended")
public class recommendedListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<RecommendedVo> list=RecommendedDao.getDao().list();
		req.setAttribute("recommended", list);


		req.setAttribute("page", "/recommended/recommended.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
	}
}
