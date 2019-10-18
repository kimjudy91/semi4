package message.controller.joo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import message.dao.joo.MessageDao;
import message.vo.joo.MessageVo;
@WebServlet("/message/detail")
public class MessageDetailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid=(String)req.getSession().getAttribute("id");
		String rid=req.getParameter("rid");
		ArrayList<MessageVo> list=MessageDao.getDao().msgDetailList(sid, rid);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/message/messageDetail.jsp").forward(req, resp);
	}
}
