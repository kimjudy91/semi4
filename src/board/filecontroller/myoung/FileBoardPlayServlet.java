package board.filecontroller.myoung;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.HTTP;

import board.controller.myoung.GenreDao;
import board.filecontroller.dao.FileBoardDao;
import board.filecontroller.vo.FileBoardVo;
import board.filecontroller.vo.FileUpLoadVo;

@WebServlet("/fileboard/play")
public class FileBoardPlayServlet  extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int write_num=Integer.parseInt(req.getParameter("write_num"));
		FileBoardDao dao1=new FileBoardDao();
		FileBoardVo vo=dao1.detail(write_num);
		//장르 테이블이랑 이어서 장르를 쓰게 하는것
		GenreDao dao2=new GenreDao();
		String genre_name=dao2.GenreSelect(vo.getGenre_num());
		
		
		//f_num을 받아서
		int f_num=Integer.parseInt(req.getParameter("f_num"));
		FileBoardDao dao=new FileBoardDao();
		
		//다운로드할 파일에 대한 정보 얻어오기
		FileUpLoadVo vo1=dao.getinfo(f_num);
		
		String name=vo1.getSavefilename();
		
		//req.getContextPath() 프로젝트 주소명 
	
		//이게 통합주소
		String music="//C:/Users/JHTA/Downloads/"+ name;		
		req.setAttribute("genre_name", genre_name);
		req.setAttribute("vo", vo);
		req.setAttribute("music", music);
		req.setAttribute("page","/fileboard/detail.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
	
	}
	

}
