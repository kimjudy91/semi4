package login.controller.joo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.dao.joo.LoginDao;
import members.dao.min.MembersDao;
import message.dao.joo.MessageDao;
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
			int grade=MembersDao.getDao().getGrade(id);
			req.setAttribute("grade", grade);
			int warning=MembersDao.getDao().getWarning(id);
			req.setAttribute("warning", warning);
			int report2Count=ReportDao.getDao().newReport2Count();	
			req.getSession().setAttribute("report2Count", report2Count);
			int newrf=MessageDao.getDao().getRevFriCount(id);
			req.getSession().setAttribute("newrf",newrf);
			req.getSession().setAttribute("id", id);
			ArrayList<String> mlist=MessageDao.getDao().getMsgList(id);
			int countMsgs=MessageDao.getDao().newMsgs(id, mlist);
			req.setAttribute("countMsgs", countMsgs);
			req.setAttribute("page", "/main/main.jsp");
			req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
		}else {
			
			req.setAttribute("errMsg", "fail");
			req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
		}
	}
}
