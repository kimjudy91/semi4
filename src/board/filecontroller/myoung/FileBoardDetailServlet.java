package board.filecontroller.myoung;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.controller.myoung.GenreDao;
import board.dao.yun.BoardDao;
import board.filecontroller.dao.FileBoardDao;
import board.filecontroller.vo.FileBoardVo;
import board.filecontroller.vo.FileUpLoadVo;
import board.vo.myoung.GenreVo;
import board.vo.yun.BoardVo;

@WebServlet("/fileboard/detail")
public class FileBoardDetailServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int write_num=Integer.parseInt(req.getParameter("write_num"));
		FileBoardDao dao=new FileBoardDao();
		FileBoardVo vo=dao.detail(write_num);
	
				
		//장르 테이블이랑 이어서 장르를 쓰게 하는것
		GenreDao dao1=new GenreDao();
		String genre_name=dao1.GenreSelect(vo.getGenre_num());
		
		req.setAttribute("genre_name", genre_name);
		req.setAttribute("vo", vo);
		req.setAttribute("page","/fileboard/detail.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
	}
}
