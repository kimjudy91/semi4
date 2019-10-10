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
		String spageNum=req.getParameter("pageNum");
		String field=req.getParameter("field");
		String keyword=req.getParameter("keyword");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int endRow=pageNum*10;
		int startRow=endRow-9;
		BoardDao dao=BoardDao.getinstance();
		ArrayList<BoardVo> list=dao.list(startRow, endRow, field, keyword);
		int pageCount=(int)Math.ceil(dao.getCount(field,keyword)/10.0);
		int startPageNum=((pageNum-1)/10*10)+1;
		int endPageNum=startPageNum+9;
		if(endPageNum>pageCount) {
			endPageNum=pageCount;
		}
		
		req.setAttribute("list", list);
		req.setAttribute("top","/board/header.jsp");
		req.setAttribute("content","/board/community.jsp");
		req.getRequestDispatcher("/board/main").forward(req, resp);
	}
}
