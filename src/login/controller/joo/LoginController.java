package login.controller.joo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.dao.joo.LoginDao;
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
		System.out.println(id);
		System.out.println(pwd);
		int n=LoginDao.getDao().login(id,pwd);
		System.out.println(n);
		if(n>0) {
			req.getSession().setAttribute("id", id);
			resp.sendRedirect(req.getContextPath()+"/index/index.jsp");
		}else {
			
			req.setAttribute("errMsg", "fail");
			req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
		}
	}
}
