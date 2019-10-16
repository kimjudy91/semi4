package ad.controller.joo;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ad.dao.joo.AdDao;
import ad.vo.joo.AdVo;
@WebServlet("/ad/insert")
public class AdInsertController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("page","/ad/insert.jsp");
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int ad_money=Integer.parseInt(req.getParameter("ad_money"));
		String ad_com=req.getParameter("ad_com");
		String ad_image=req.getParameter("ad_image");
		String ad_start_date=req.getParameter("ad_start_date");
		String ad_end_date=req.getParameter("ad_end_date");
		AdVo vo=new AdVo(0, ad_money, ad_com, ad_image, ad_start_date, ad_end_date);
		AdDao.getDao().insert(vo);
		ArrayList<AdVo> list=AdDao.getDao().list();
		req.setAttribute("adList", list);
		req.setAttribute("page","/ad/list.jsp");		
		req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
	}
}
