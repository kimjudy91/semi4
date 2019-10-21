package board.filecontroller.myoung;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.yun.BoardDao;
import board.filecontroller.dao.FileBoardDao;
import board.vo.yun.BoardVo;
@WebServlet("/fileboard/delete")
public class FileBoardDeleteServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int write_num=Integer.parseInt(req.getParameter("write_num"));
		int f_num=Integer.parseInt(req.getParameter("f_num"));
		FileBoardDao dao=new FileBoardDao();
		int n=dao.delete(write_num,f_num);
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/fileboard/community");
		}else {
			req.setAttribute("page", "/fileboard/community.jsp");
			req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
		}
	}
		
	

}
