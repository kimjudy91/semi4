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
@WebServlet("/insertFri")
public class FriInsertController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid=req.getParameter("sid");
		String rid=req.getParameter("rid");
		if(!sid.equals(rid)) {
			int n=MessageDao.getDao().checkfri(sid, rid);
			if(n<0) {
				int n1=MessageDao.getDao().insertfri(sid, rid);
				if(n1>0) {
					req.setAttribute("cmd", "친구신청을 보냈습니다.");
					req.getRequestDispatcher("/friends").forward(req, resp);
				}else {
					req.setAttribute("cmd", "친구신청에 실패했습니다.");
					req.getRequestDispatcher("/friends").forward(req, resp);
				}
			}else {
				req.setAttribute("cmd", "이미 등록된 친구입니다.");
				req.getRequestDispatcher("/friends").forward(req, resp);
			}
		}else {
			req.setAttribute("cmd", "자기자신을 추가할수 없습니다 .");
			req.getRequestDispatcher("/friends").forward(req, resp);
		}
	}
}
