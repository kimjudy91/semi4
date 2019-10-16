package nquireController.min;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import members.vo.min.MembersVo;
import nquire.dao.min.NquireDao;
import nquire.vo.min.NquireVo;

@WebServlet("/nquire/comm")
public class NquireCommentsController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int nquire_num=Integer.parseInt(req.getParameter("nquire_num"));
		String id=req.getParameter("id");
		NquireVo von=NquireDao.getDao().selectNquire(nquire_num);
		MembersVo vom=NquireDao.getDao().select(id);
		req.setAttribute("von", von);
		req.setAttribute("vom", vom);
		req.getRequestDispatcher("/nquire.min/nquire.detail.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		req.setCharacterEncoding("utf-8");
		int nquire_num=Integer.parseInt(req.getParameter("nquire_num"));
		System.out.println(nquire_num+"/////");
		String comments=req.getParameter("comments");
		System.out.println(comments+"/////zzzzzz");
		NquireDao.getDao().updateComments(nquire_num, comments);
		resp.sendRedirect(req.getContextPath()+"/index");
	}
}
