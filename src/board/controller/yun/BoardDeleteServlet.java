package board.controller.yun;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.yun.BoardDao;
import board.vo.yun.BoardVo;
@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int write_num=Integer.parseInt(req.getParameter("write_num"));
		BoardDao dao=new BoardDao();
		int n=dao.delete(write_num);
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/board/community");
		}else {
			req.setAttribute("top", "header.jsp");
			req.setAttribute("content", "/board/community.jsp");
			req.getRequestDispatcher("/board/main.jsp").forward(req, resp);
		}
	}

}
