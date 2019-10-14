package joinController.min;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import members.dao.min.MembersDao;
import members.vo.min.MembersVo;

@WebServlet("/join/insert")
public class JoinServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath() + "/join.min/join.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		String pwd=req.getParameter("pwd");
		String name=req.getParameter("name");	
		String email=req.getParameter("email");
		String address=req.getParameter("address");
		String phone=req.getParameter("phone");
		int janre=Integer.parseInt(req.getParameter("janre"));
		int jumin=Integer.parseInt(req.getParameter("jumin"));
		MembersVo vo=new MembersVo(id, pwd, name, email, address, phone, 0, 0, 1, 0, janre, jumin);
		int n=MembersDao.getDao().insert(vo);
		if(janre==1) {
			req.setAttribute("rock", janre);
		}else if(janre==2) {
			req.setAttribute("folk",janre);
		}else if(janre==3) {
			req.setAttribute("RB", janre);
		}
		if(n>0) {
			req.setAttribute("code", "success");
		}else {
			req.setAttribute("code", "fail");
		}
		req.getRequestDispatcher("/join.min/joinOk.jsp").forward(req, resp);
		
	}
}
