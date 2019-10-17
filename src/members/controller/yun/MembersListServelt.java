package members.controller.yun;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.vo.yun.BoardVo;
import members.dao.min.MembersDao;
import members.vo.min.MembersVo;
@WebServlet("/members_management")
public class MembersListServelt extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		String spageNum=req.getParameter("pageNum");
		String field=req.getParameter("field");
		String keyword=req.getParameter("keyword");
		String cmd=req.getParameter("cmd");
		MembersDao mdao=MembersDao.getDao();
		if(cmd!=null &&cmd.equals("increase")) {
			mdao.increaseWarning(id);
			mdao.setGrade();
		}else if(cmd!=null &&cmd.equals("down")) {
			mdao.downWarning(id);
			mdao.setGrade();
		}
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum*5)-4;
		int endRow=pageNum*5;
		MembersDao dao=MembersDao.getDao();
		ArrayList<MembersVo> list=dao.list(startRow, endRow, field, keyword);
		int pageCount=(int)Math.ceil(dao.getCount(field,keyword)/5.0);
		int startPageNum=((pageNum-1)/4*4)+1;
		int endPageNum=startPageNum+3;
		if(endPageNum>pageCount) {
			endPageNum=pageCount;
		}
		



		req.setAttribute("list", list);
		req.setAttribute("page","/members_management/members_management.jsp");
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPageNum", startPageNum);
		req.setAttribute("endPageNum", endPageNum);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("field", field);
		req.setAttribute("keyword", keyword);
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
	}
}
