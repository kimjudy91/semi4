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
import report2.vo.min.Report2Vo;
@WebServlet("/report/comments")
public class ReportCommentsController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int write_num=Integer.parseInt(req.getParameter("write_num"));
		int rnum=Integer.parseInt(req.getParameter("rnum"));
		Report2Vo vo=ReportDao.getDao().searchReport2(rnum);
		BoardVo bvo=BoardDao.getinstance().select(write_num);
		req.setAttribute("vo", vo);
		req.setAttribute("bvo", bvo);
		req.setAttribute("page","/report/reportComments.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int rnum=Integer.parseInt(req.getParameter("rnum"));
		String comments=req.getParameter("comments");
		ReportDao.getDao().updateComments(rnum, comments);
		resp.sendRedirect(req.getContextPath()+"/index");
	}
}
