package friends.controller.joo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import message.dao.joo.MessageDao;
@WebServlet("/acceptfri")
public class FriendsAcceptController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid=req.getParameter("sid");
		String rid=req.getParameter("rid");
		
		int n=MessageDao.getDao().acceptfir(sid, rid);
		if(n>0) {
			req.setAttribute("cmd", "친구수락에 성공했습니다.");
			req.getRequestDispatcher("/friends").forward(req, resp);
		}else {
			req.setAttribute("cmd", "친구수락에 실패했습니다.");
			req.getRequestDispatcher("/friends").forward(req, resp);
		}
		
	}
}
