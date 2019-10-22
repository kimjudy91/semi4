package index.controller.joo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import message.dao.joo.MessageDao;
import report.dao.joo.ReportDao;

@WebServlet("/index")
public class IndexController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");	
		String spage=req.getParameter("page");
		int report2Count=ReportDao.getDao().newReport2Count();	
		req.getSession().setAttribute("report2Count", report2Count);
		String id=(String)req.getSession().getAttribute("id");
		if(id!=null) {
			int newrf=MessageDao.getDao().getRevFriCount(id);
			req.getSession().setAttribute("newrf",newrf);
			ArrayList<String> mlist=MessageDao.getDao().getMsgList(id);
			int countMsgs=MessageDao.getDao().newMsgs(id, mlist);
			req.setAttribute("countMsgs", countMsgs);
			req.getSession().setAttribute("id", id);
			}
		if(spage==null||spage.equals("")) {
			
			req.setAttribute("page", "/main/main.jsp");
			req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
		}else {
		
			req.setAttribute("page", spage);
			req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
		}			
	}
}
