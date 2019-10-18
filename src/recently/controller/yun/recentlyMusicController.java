package recently.controller.yun;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_f_dao.yun.RecentlyDao;
import board_f_dao.yun.RecentlyMusicDao;
import board_f_vo.yun.RecentlyMusicVo;
import board_f_vo.yun.RecentlyVo;

@WebServlet("/recently_music")
public class recentlyMusicController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<RecentlyMusicVo> list=RecentlyMusicDao.getDao().list();
		req.setAttribute("recently_music", list);
		req.getRequestDispatcher("/recently/recently_music.jsp").forward(req, resp);

	}
}