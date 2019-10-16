package report.controller.joo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.yun.BoardDao;
import board.vo.yun.BoardVo;
import report.dao.joo.ReportDao;
import report2.vo.min.Report2Vo;
@WebServlet("/reportList")
public class ReportListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Report2Vo> listReport2=ReportDao.getDao().listReport2();
		req.setAttribute("listReport2", listReport2);
		req.setAttribute("page","/report/reportlist.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);

	}
}
