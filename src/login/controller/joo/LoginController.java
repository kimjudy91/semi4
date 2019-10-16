package login.controller.joo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.dao.joo.LoginDao;
import report.dao.joo.ReportDao;
@WebServlet("/logins")
	public class LoginController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath()+"/login/login.jsp");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		String pwd=req.getParameter("pwd");
		int n=LoginDao.getDao().login(id,pwd);
		if(n>0) {
			int report2Count=ReportDao.getDao().newReport2Count();	
			req.setAttribute("report2Count", report2Count);
			req.getSession().setAttribute("id", id);
			req.setAttribute("page", "/main/main.jsp");
			req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
		}else {
			
			req.setAttribute("errMsg", "fail");
			req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
		}
	}
}
