package nquireController.min;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nquire.dao.min.NquireDao;
import nquire.vo.min.NquireVo;

@WebServlet("/nquire/list")
public class NquireListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		NquireDao dao=NquireDao.getDao();
		ArrayList<NquireVo> list=dao.list();
				
		req.setAttribute("list", list);
		req.setAttribute("page","/nquire.min/nquire.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
	}
}
