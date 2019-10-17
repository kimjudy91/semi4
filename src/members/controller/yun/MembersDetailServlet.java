package members.controller.yun;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import members.dao.min.MembersDao;
import members.vo.min.MembersVo;
@WebServlet("/myPage")
public class MembersDetailServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		MembersDao dao=MembersDao.getDao();
		MembersVo vo=dao.detail(id);
		req.setAttribute("vo", vo);
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
	}
}
