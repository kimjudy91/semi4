package updateMembers.controller.joo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import updateMembers.dao.joo.UpdateDao;
@WebServlet("/updateMembers")
public class UpdateMembersController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");	
		String id=(String)req.getSession().getAttribute("id");
		String email=req.getParameter("email");
		String address=req.getParameter("address");
		String phone=req.getParameter("phone");
		int n=UpdateDao.getDao().update(id, email, phone, address);
		if(n>0) {
			req.setAttribute("code", "success");
			req.getRequestDispatcher("/myPage/result.jsp").forward(req, resp);
		}else {
			req.setAttribute("code", "fail");
			req.getRequestDispatcher("/myPage/result.jsp").forward(req, resp);
		}
	}
}
