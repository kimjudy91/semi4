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
import members.dao.min.MembersDao;
import members.vo.min.MembersVo;
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
		String trans=req.getParameter("trans");
		int genre_num=0;
		
		if (trans.equals("rnb")) {
			 genre_num=1;
		}else if(trans.equals("pop")) {
			 genre_num=2;
		}else if(trans.equals("oldsong")) {
			 genre_num=3;
		}

	
		BoardVo vo1=new BoardVo(0, id, p_title, contents, null, 0, genre_num);
		BoardDao dao1=BoardDao.getinstance();
		int n=dao1.insert(vo1);
		if(n>0) {
			req.setAttribute("code", "success");
			
	
			
		}else {
			req.setAttribute("code", "fail");
		}
		req.setAttribute("top", "header.jsp");
		req.setAttribute("content", "/board/result.jsp");
		req.getRequestDispatcher("/board/main.jsp").forward(req, resp);
	}
}
