package report.controller.joo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.yun.BoardDao;
import board.vo.yun.BoardVo;
import report.dao.joo.ReportDao;
@WebServlet("/report2")
public class ReportController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int write_num=Integer.parseInt(req.getParameter("write_num"));
		BoardVo vo=BoardDao.getinstance().select(write_num);
		req.setAttribute("vo", vo);
		req.setAttribute("page","/report/report.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int write_num=Integer.parseInt(req.getParameter("write_num"));
		String report_content=req.getParameter("report_content");
		String id=(String)req.getSession().getAttribute("id");
		int n=ReportDao.getDao().insertReport2(id, write_num, report_content);
		if(n>0) {
			req.setAttribute("code", "success");
		}else {
			req.setAttribute("code", "fail");
		}
		req.setAttribute("page","/report/result.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);

	}
}
