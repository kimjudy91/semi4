package board.filecontroller.myoung;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.HTTP;

import board.filecontroller.dao.FileBoardDao;
import board.filecontroller.vo.FileBoardVo;

@WebServlet("/fileboard/likes")
public class FileBoardLikesServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int write_num=Integer.parseInt(req.getParameter("write_num"));
		
	
		FileBoardDao dao=new FileBoardDao();
	
		int likes=Integer.parseInt(req.getParameter("likes"));
			
			
		int n=dao.update3(likes,write_num);
		
		if (n>0) {
			req.setAttribute("code", "success");
		}else {
			req.setAttribute("code", "fail");
		}
		FileBoardVo vo=dao.detail(write_num);
		req.setAttribute("vo", vo);
		req.setAttribute("page","/fileboard/detail.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
		
	}
}