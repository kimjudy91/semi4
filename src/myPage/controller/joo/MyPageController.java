package myPage.controller.joo;

import java.io.IOException;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.ast.MemberValuePair;

import members.dao.min.MembersDao;
import members.vo.min.MembersVo;
@WebServlet("/myPage")
public class MyPageController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=(String)req.getSession().getAttribute("id");
		MembersVo vo=MembersDao.getDao().search(id);
		req.setAttribute("vo", vo);
		req.getRequestDispatcher("/myPage/myPage.jsp").forward(req, resp);
	}
}
