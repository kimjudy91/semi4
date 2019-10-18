package board.filecontroller.myoung;

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
import board.filecontroller.dao.FileBoardCommentsDao;
import board.filecontroller.vo.FileBoardCommentsVo;
import board.vo.yun.BoardCommentsVo;
import members.dao.min.MembersDao;
import members.vo.min.MembersVo;

@WebServlet("/fileboard/comments")
public class FileBoardCommentsServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd=req.getParameter("cmd");
		if(cmd!=null && cmd.equals("insert")) {
			insert(req,resp);
		}else if(cmd!=null && cmd.equals("insertCom")) {
			insertComm(req,resp);
		}
	}
	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int write_num=Integer.parseInt(req.getParameter("write_num"));
		String id=req.getParameter("id");
		String comments_contents=req.getParameter("comments_contents");
		FileBoardCommentsDao dao=FileBoardCommentsDao.getCommentsDao();
		FileBoardCommentsVo vo=new FileBoardCommentsVo(0, write_num, id, comments_contents, null, 0,0,0);
		ArrayList<FileBoardCommentsVo> commList=FileBoardCommentsDao.getCommentsDao().getCommList(write_num);
		req.setAttribute("commList", commList);
		req.setAttribute("wrtie_num", write_num);
		int n=dao.insert(vo);
		if(n>0) {
			req.getRequestDispatcher("/fileboard/detail").forward(req, resp);
		}else {
			req.getRequestDispatcher("/fileboard/detail").forward(req, resp);
		}
	}
	
	
	protected void insertComm(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		int write_num=Integer.parseInt(req.getParameter("write_num"));
		String id=req.getParameter("id");
		String comments_contents=req.getParameter("comments_contents");
		int ref=Integer.parseInt(req.getParameter("ref"));
		int lev=(Integer.parseInt(req.getParameter("lev"))+1);
		int step=(Integer.parseInt(req.getParameter("step"))+1);
		FileBoardCommentsDao dao= FileBoardCommentsDao.getCommentsDao();
		FileBoardCommentsVo vo=new FileBoardCommentsVo(0, write_num, id, comments_contents, null, ref, lev, step);	
		int n=dao.insertComm(vo);
		int n1=dao.increseStep(ref, step);
		ArrayList<FileBoardCommentsVo> commList=FileBoardCommentsDao.getCommentsDao().getCommList(write_num);
		req.setAttribute("commList", commList);
		req.setAttribute("wrtie_num", write_num);
		if(n>0) {
			req.getRequestDispatcher("/fileboard/detail").forward(req, resp);
		}else {
			req.getRequestDispatcher("/fileboard/detail").forward(req, resp);
		}
	}
}












