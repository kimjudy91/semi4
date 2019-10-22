package board.filecontroller.myoung;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.glass.ui.Application;

import board.dao.yun.BoardDao;
import board.filecontroller.dao.FileBoardDao;
import board.filecontroller.vo.FileBoardVo;
import board.filecontroller.vo.FileUpLoadVo;
import board.vo.yun.BoardVo;
import members.dao.min.MembersDao;
import members.vo.min.MembersVo;
@WebServlet("/fileboard/insert")
public class FileBoardInsertServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("page", "/fileboard/insert.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//서블릿에서 사용하는 파일첨부 프로그램 우리가 배운
		//application 이거랑 동일하다 이거 사용하면 위에 HttpServletRequest 이거 못쓰고
		//아래에 잇는 MultipartRequest mr 이걸로 사용해야한다
		String path=getServletContext().getRealPath("/upload");
		MultipartRequest mr =new MultipartRequest(
				req,
				path,
				1024*1024*5,
				"utf-8",
				new DefaultFileRenamePolicy()
		);
		
		String  orgfilename=mr.getOriginalFileName("file");//전송된 파일명
		String  savefilename=mr.getFilesystemName("file");//저장된 파일명
		File file=mr.getFile("file");//업로드된 파일에 대한 정보를 갖는 File객체
		int filesize=(int) file.length();//업로드된 파일크기
		
		
	
		String id=mr.getParameter("id");
		String p_title=mr.getParameter("p_title");
		String contents=mr.getParameter("contents");
		
		
		//여기는 장르 구분하는곳
		String trans=mr.getParameter("trans");
		int genre_num=0;
		if (trans.equals("rnb")) {
			 genre_num=1;
		}else if(trans.equals("pop")) {
			 genre_num=2;
		}else if(trans.equals("oldsong")) {
			 genre_num=3;
		}
		
		
		
		
		
		//이거는 그냥 글내용이랑 아이디 저장
		FileBoardVo vo=new FileBoardVo(0, id, 0, p_title, contents, null, 0, 0, genre_num);
		//DB에 전송된 파일정보 저장하기
		FileUpLoadVo vo1=new FileUpLoadVo(0, orgfilename, savefilename, filesize);
		FileBoardDao dao1=FileBoardDao.getinstance();
		int n=dao1.insert(vo1,vo);
		if(n>0) {
			req.setAttribute("code", "success");
		}else {
			req.setAttribute("code", "fail");
		}
		req.setAttribute("page", "/fileboard/result.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
	}
}
