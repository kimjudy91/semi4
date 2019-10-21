package myPage.controller.joo;

import java.io.IOException;
import java.util.ArrayList;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import members.dao.min.MembersDao;
import members.vo.min.MembersVo;
import report.dao.joo.ReportDao;
import report2.vo.min.Report2Vo;
@WebServlet("/myPage")
public class MyPageController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=(String)req.getSession().getAttribute("id");
		if(req.getParameter("id")!=null) {
			id=req.getParameter("id");
		}
		MembersVo vo=MembersDao.getDao().search(id);
		ArrayList<Report2Vo> rlist=ReportDao.getDao().searchListReport2(id);
		req.setAttribute("rlist", rlist);
		req.setAttribute("vo", vo);
		req.setAttribute("page","/myPage/myPage.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
		
	}
}
