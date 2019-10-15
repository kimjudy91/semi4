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
		if(cmd!=null && cmd.equals("list")) {
			list(req,resp);
		}else if(cmd!=null && cmd.equals("insert")) {
			insert(req,resp);
		}else if(cmd!=null && cmd.equals("delete")) {
			delete(req,resp);
		}
	}
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int comments_num=Integer.parseInt(req.getParameter("comments_num"));
		BoardCommentsDao dao=BoardCommentsDao.getCommentsDao();
		int n=dao.delete(comments_num);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		JSONObject json=new JSONObject();
		if(n>0) {
			json.put("code", "success");
		}else {
			json.put("code", "fail");
		}
		pw.print(json);
	}
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int write_num=Integer.parseInt(req.getParameter("write_num"));
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		BoardCommentsDao dao=BoardCommentsDao.getCommentsDao();
		ArrayList<BoardCommentsVo> list=dao.list(write_num);
		JSONArray arr=new JSONArray();
		arr.put(list);
		pw.print(arr);
	
	}
	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int write_num=Integer.parseInt(req.getParameter("write_num"));
		String id=req.getParameter("id");
		String comments_contents=req.getParameter("comments_contents");
	
		BoardCommentsVo vo=new BoardCommentsVo(0, write_num, id, comments_contents, null, 0,0,0);
		BoardCommentsDao dao=BoardCommentsDao.getCommentsDao();
		int n=dao.insert(vo);
		resp.setContentType("text/plain);charset=utf-8");
		PrintWriter pw=resp.getWriter();
		JSONObject json=new JSONObject();
		if(n>0) {
			json.put("code", "success");
		}else {
			json.put("code", "fail");
		}
		pw.print(json);
		}
	}












