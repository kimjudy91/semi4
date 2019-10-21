package board.filecontroller.myoung;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.dao.yun.BoardDao;
import board.filecontroller.dao.FileBoardDao;
import board.filecontroller.vo.FileBoardVo;
import board.filecontroller.vo.FileUpLoadVo;
import board.vo.yun.BoardVo;

@WebServlet("/fileboard/update")
public class FileBoardUpdateServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int write_num=Integer.parseInt(req.getParameter("write_num"));
		FileBoardDao dao=new FileBoardDao();
		FileBoardVo vo=dao.select(write_num);
		
	
		
		req.setAttribute("vo", vo);
		req.setAttribute("page","/fileboard/update.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String path=getServletContext().getRealPath("/upload");
		MultipartRequest mr =new MultipartRequest(
				req,
				path,
				1024*1024*5,
				"utf-8",
				new DefaultFileRenamePolicy()
		);
	

		//기존 파일삭제
		int f_num=Integer.parseInt(mr.getParameter("f_num"));
		
		FileBoardDao dao1=new FileBoardDao();
		
		FileUpLoadVo vo1=dao1.getinfo(f_num);

		File f=new File(path +"\\" + vo1.getSavefilename());
		f.delete();	
		
		
		
		String  orgfilename=mr.getOriginalFileName("file");//전송된 파일명
	String  savefilename=mr.getFilesystemName("file");//저장된 파일명
		File file=mr.getFile("file");//업로드된 파일에 대한 정보를 갖는 File객체
		int filesize=(int) file.length();//업로드된 파일크기
		System.out.println("filesize"+filesize);
		
		
		int write_num=Integer.parseInt(mr.getParameter("write_num"));
		String id=mr.getParameter("id");
		String p_title=mr.getParameter("p_title");
		String contents=mr.getParameter("contents");
		
		FileBoardVo vo2=new FileBoardVo(write_num, id, 0, p_title, contents, null, 0, 0, 0);
		//DB에 전송된 파일정보 저장하기
		
		
		FileBoardDao dao2=FileBoardDao.getinstance();
		int n=dao2.update(vo2);
		
		FileUpLoadVo vo3=new FileUpLoadVo(f_num, orgfilename, savefilename, filesize);
		dao1.update2(vo3);
		
		if(n>0) {
			req.setAttribute("code", "success");
		}else {
			req.setAttribute("code", "fail");
		}
		req.setAttribute("page", "/fileboard/result.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
	}
}
