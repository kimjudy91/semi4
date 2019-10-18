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
@WebServlet("/messageList")
public class MessageListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<MessageVo> msgList=MessageDao.getDao().msgList((String)req.getSession().getAttribute("id"));
		req.setAttribute("msgList", msgList);
		req.getRequestDispatcher("/message/messageList.jsp").forward(req, resp);
	}
}
