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
		String pwd=req.getParameter("pwd");
		int n=UpdateDao.getDao().update(id, email, phone, address,pwd);
		if(n>0) {
			req.setAttribute("code", "success");
		}else {
			req.setAttribute("code", "fail");		
		}
		req.setAttribute("page","/myPage/result.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);

	}
}
