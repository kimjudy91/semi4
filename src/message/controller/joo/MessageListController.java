package message.controller.joo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import message.dao.joo.MessageDao;
import message.vo.joo.MessageVo;
import newmessage.vo.joo.NewMessageVo;
@WebServlet("/messageList")
public class MessageListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<String> msgList=MessageDao.getDao().getMsgList((String)req.getSession().getAttribute("id"));
		ArrayList<NewMessageVo> newMsg=new ArrayList<NewMessageVo>();
		for (int i = 0; i <msgList.size(); i++) {
			int count=MessageDao.getDao().newMsg((String)req.getSession().getAttribute("id"), msgList.get(i));
			NewMessageVo vo=new NewMessageVo(msgList.get(i),count );
			newMsg.add(vo);
		}
		req.setAttribute("newMsg", newMsg);
		req.getRequestDispatcher("/message/messageList.jsp").forward(req, resp);
	}
}
