package nquireController.min;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import members.dao.min.MembersDao;
import members.vo.min.MembersVo;
import nquire.dao.min.NquireDao;
import nquire.vo.min.NquireVo;

@WebServlet("/nquire/insert")
public class NquireInsertController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=(String)req.getSession().getAttribute("id");
		MembersVo vo=MembersDao.getDao().search(id);
		req.setAttribute("vo", vo);
		req.setAttribute("page","/nquire.min/nquire.insert.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		String title=req.getParameter("title");
		String contents=req.getParameter("contents");
		String comments=req.getParameter("comments");
		NquireVo vo=new NquireVo(0, id, title, contents, null, comments);
		int n=NquireDao.getDao().insert(vo);
		if(n>0) {
			req.setAttribute("code", "success");
			
		}else {
			req.setAttribute("code", "fail");
			
		}
		
		req.setAttribute("page","/nquire.min/nquireOk.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
	}
}
