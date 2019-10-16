package board.controller.yun;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.yun.BoardCommentsDao;
import board.dao.yun.BoardDao;
import board.vo.yun.BoardCommentsVo;
import board.vo.yun.BoardVo;

@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int write_num=Integer.parseInt(req.getParameter("write_num"));
		BoardDao dao=new BoardDao();
		BoardVo vo=dao.detail(write_num);
		ArrayList<BoardCommentsVo> commList=BoardCommentsDao.getCommentsDao().getCommList(write_num);
		req.setAttribute("commList", commList);
		req.setAttribute("vo", vo);	
		req.setAttribute("page","/board/detail.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
	}
}
