package board.controller.yun;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import board.dao.yun.BoardCommentsDao;
import board.vo.yun.BoardCommentsVo;

@WebServlet("/board/comments")
public class BoardCommentsServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd=req.getParameter("cmd");
		if(cmd!=null && cmd.equals("insert")) {
			insert(req,resp);
		}
	}
	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int write_num=Integer.parseInt(req.getParameter("write_num"));
		String id=req.getParameter("id");
		String comments_contents=req.getParameter("comments_contents");
		BoardCommentsDao dao=BoardCommentsDao.getCommentsDao();
		BoardCommentsVo vo=new BoardCommentsVo(0, write_num, id, comments_contents, null, 0,0,0);
		ArrayList<BoardCommentsVo> commList=BoardCommentsDao.getCommentsDao().getCommList(write_num);
		req.setAttribute("commList", commList);
		req.setAttribute("wrtie_num", write_num);
		int n=dao.insert(vo);
		if(n>0) {
			req.getRequestDispatcher("/board/detail").forward(req, resp);
		}else {
			req.getRequestDispatcher("/board/detail").forward(req, resp);
		}
	}
}












