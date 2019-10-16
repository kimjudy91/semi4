package ad.controller.joo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ad.dao.joo.AdDao;
import ad.vo.joo.AdVo;
@WebServlet("/ad/list")
public class AdListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<AdVo> list=AdDao.getDao().list();
		req.setAttribute("adList", list);
		req.setAttribute("page","/ad/list.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
	}
}
