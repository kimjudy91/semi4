package recommended.controller.yun;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_f_dao.yun.Recommended_folk_Dao;
import board_f_vo.yun.Recommended_folk_Vo;

@WebServlet("/recommended_folk")
public class recommendedListController_Folk extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Recommended_folk_Vo> list=Recommended_folk_Dao.getDao().list();
		req.setAttribute("recommended_folk", list);
	
		req.getRequestDispatcher("/recommended/recommended_folk.jsp").forward(req, resp);
	}
}