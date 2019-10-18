package board.filecontroller.myoung;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.apache.tomcat.util.http.fileupload.UploadContext;

import board.filecontroller.dao.FileBoardDao;
import board.filecontroller.vo.FileUpLoadVo;


@WebServlet("/fileboard.download")
public class FileBoardDownLoad extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int f_num=Integer.parseInt(req.getParameter("f_num"));
		FileBoardDao dao=new FileBoardDao();
		
		//다운로드할 파일에 대한 정보 얻어오기
		FileUpLoadVo vo=dao.getinfo(f_num);
		
		
		//1. 다운로드창으로 응답하기
		//다운로드할 파일명이 한글인 경우 깨지지 않도록 인코딩하기
		String filename=URLEncoder.encode(vo.getOrgfilename(), "utf-8");
		filename=filename.replaceAll("\\+","%20");//%20 공백문자? 뭔소리야
		//응답콘텐츠타입 설정(다운로드창으로 응답) 일반 변환하고자 하는 정규 표현식?
		resp.setContentType("application/octet-stream");//웹브라우저 해석할수있다? 다운로드 창으로 열겟다/8비트로 나열되는 데이터다
		//다운로드할 파일크기 지정
		resp.setContentLengthLong(vo.getFilesize());
		//다운로드창에 보여질 파일명 지정
		resp.setHeader("Content-Disposition", "attachment;filename="+filename);
		
		//2. 파일을 다운로드하기
		String path=getServletContext().getRealPath("/upload");
		FileInputStream fis=new FileInputStream(path+"\\"+vo.getSavefilename());//서버에 잇는 파일을 읽어보는것 실제 저장된곳을 찾아서 가져오는것
		OutputStream os=resp.getOutputStream();
		BufferedInputStream bis=new BufferedInputStream(fis); //버퍼크기를 조정할수잇다
		BufferedOutputStream bos=new BufferedOutputStream(os);
		byte[] b=new byte[1024];
		int n=0;
		while((n=bis.read(b))!=-1) {
			bos.write(b, 0, n);
		}
		bos.close();
		bis.close();
		
		
		
	}
}
