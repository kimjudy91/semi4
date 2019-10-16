package board.controller.yun;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.yun.BoardDao;
import board.vo.yun.BoardVo;

@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int write_num=Integer.parseInt(req.getParameter("write_num"));
		BoardDao dao=new BoardDao();
		BoardVo vo=dao.select(write_num);
		req.setAttribute("vo", vo);
		req.setAttribute("page","/board/update.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int write_num=Integer.parseInt(req.getParameter("write_num"));
		String id=req.getParameter("id");
		String p_title=req.getParameter("p_title");
		String contents=req.getParameter("contents");
		int views=Integer.parseInt(req.getParameter("views"));
		BoardVo vo=new BoardVo(write_num, id, p_title, contents, null, views , 1);
		BoardDao dao=new BoardDao();
		int n=dao.update(vo);
		if(n>0) {
			req.setAttribute("code", "success");
		}else {
			req.setAttribute("code", "fail");
		}
		req.setAttribute("page","/board/result.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
	}
}
