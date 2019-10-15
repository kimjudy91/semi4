package nquireController.min;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nquire.dao.min.NquireDao;
import nquire.vo.min.NquireVo;

@WebServlet("/board/nquire")
public class NquireController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		NquireDao dao=NquireDao.getDao();
		NquireVo vo=dao.select(id);	
		req.setAttribute("vo", vo);	
		req.setAttribute("top", "header.jsp");
		req.setAttribute("content", "/board/nquire.insert.jsp");
		req.getRequestDispatcher("/nquire.min/nquire.insert.jsp").forward(req, resp);

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
		req.setAttribute("top", "header.jsp");
		req.setAttribute("content", "/board/nquireOk.jsp");
		req.getRequestDispatcher("/board/nquireOk.jsp").forward(req, resp);
		
		
		
	}
}
