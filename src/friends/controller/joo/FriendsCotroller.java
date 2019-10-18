package friends.controller.joo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import message.dao.joo.MessageDao;
import message.vo.joo.FriendsVo;
@WebServlet("/friends")
public class FriendsCotroller extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<FriendsVo> list=MessageDao.getDao().friednsList();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/message/friends.jsp").forward(req, resp);
	}
}
