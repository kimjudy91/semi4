package recommended.controller.yun;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_f_dao.yun.Recommended_RB_Dao;
import board_f_vo.yun.Recommended_RB_Vo;


@WebServlet("/recommended_RB")
public class recommendedListController_RB extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Recommended_RB_Vo> list=Recommended_RB_Dao.getDao().list();
		req.setAttribute("recommended_RB", list);
	

		req.setAttribute("page", "/recommended/recommended_RB.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
	}
}