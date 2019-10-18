package nquireController.min;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nquire.dao.min.NquireDao;
import nquire.vo.min.NquireVo;

@WebServlet("/nquire/list")
public class NquireListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		String spageNum=req.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*11+1;
		int endRow=startRow+9;
		NquireDao dao=NquireDao.getDao();
		ArrayList<NquireVo> list=dao.list(id,startRow, endRow);
		int pageCount=(int)Math.ceil(dao.getCount()/10.0);
		int startPageNum=((pageNum-1)/10*10)+1;
		int endPageNum=startPageNum+9;
		if(endPageNum>pageCount) {
			endPageNum=pageCount;
		}
		req.setAttribute("id", id);
		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPageNum", startPageNum);
		req.setAttribute("endPageNum", endPageNum);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("page","/nquire.min/nquire.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
	}
}
