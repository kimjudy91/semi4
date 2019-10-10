package findId.controller.joo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import findId.dao.joo.FindIdDao;

@WebServlet("/findId")
public class FindIdController extends HttpServlet {
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath()+"/findId/findId.jsp");
	}
	 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String phone=req.getParameter("phone");
		String email=req.getParameter("email");
		String id=FindIdDao.getDao().findId(phone, email);
		if(id!=null) {
			req.setAttribute("id", id);
			req.getRequestDispatcher("findId/result.jsp").forward(req, resp);
		}else {
			req.setAttribute("errMsg", "fail");
			req.getRequestDispatcher("/findId/findId.jsp").forward(req, resp);
		}
	}
}
