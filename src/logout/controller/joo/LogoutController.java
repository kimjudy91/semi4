package logout.controller.joo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import loginIds.dao.joo.LoginIdsDao;
@WebServlet("/logout")
public class LogoutController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=(String)req.getSession().getAttribute("id");
		LoginIdsDao.getDao().deleteLogins(id);
		req.getSession().invalidate();
		req.setAttribute("page", "/main/main.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
	}
}
