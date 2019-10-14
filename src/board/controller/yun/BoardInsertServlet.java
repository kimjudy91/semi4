package board.controller.yun;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.yun.BoardDao;
import board.vo.yun.BoardVo;
@WebServlet("/board/insert")
public class BoardInsertServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("top", "header.jsp");
		req.setAttribute("content", "/board/insert.jsp");
		req.getRequestDispatcher("/board/insert.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		String p_title=req.getParameter("p_title");
		String contents=req.getParameter("contents");
		BoardVo vo=new BoardVo(0, id, p_title, contents, null, 0, 1);
		BoardDao dao=BoardDao.getinstance();
		int n=dao.insert(vo);
		if(n>0) {
			req.setAttribute("code", "success");
			dao.increWriteCount(id);
			int write_count=dao.getWriteCount(id);
			if(write_count==10) {
				dao.increGrade(id);
			}else if(write_count==5) {
				dao.increGrade(id);
			}
		}else {
			req.setAttribute("code", "fail");
		}
		req.setAttribute("top", "header.jsp");
		req.setAttribute("content", "/board/result.jsp");
		req.getRequestDispatcher("/board/main.jsp").forward(req, resp);
	}
}
