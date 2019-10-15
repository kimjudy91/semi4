package nquireController.min;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javafx.scene.chart.PieChart.Data;
import nquire.dao.min.NquireDao;
import nquire.vo.min.NquireVo;

@WebServlet("/nquire/detail")
public class NquireDetailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		NquireDao dao=NquireDao.getDao();
		NquireVo vo=dao.detail(id);
		req.setAttribute("vo",vo);
		req.setAttribute("top", "header.jsp");
		req.getRequestDispatcher("/nquire.min/nquire.datail.jsp").forward(req, resp);
	}
}
