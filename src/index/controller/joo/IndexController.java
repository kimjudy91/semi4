package index.controller.joo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class IndexController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String spage=req.getParameter("page");
			if(spage!=null) {
				req.setAttribute("page", spage);
				req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
			}else {
				resp.sendRedirect(req.getContextPath()+"/index/index.jsp");			
			}			
	}
}
