package board.controller.yun;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.yun.BoardDao;
import board.vo.yun.BoardVo;
@WebServlet("/board/community")
public class BoardListServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		BoardDao dao=BoardDao.getinstance();
		ArrayList<BoardVo> list=dao.list();
		req.setAttribute("list", list);
		req.setAttribute("top","/board/header.jsp");
		req.setAttribute("content","/board/community.jsp");
		req.getRequestDispatcher("/board/main").forward(req, resp);
	}
}
