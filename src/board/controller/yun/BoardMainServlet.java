package board.controller.yun;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/board/main")
public class BoardMainServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String content=(String)req.getAttribute("content");
		if(content==null) {
			content="/board/main.jsp";
		}
		req.setAttribute("content", content);
		getServletContext().setAttribute("cp", req.getContextPath());
		req.getRequestDispatcher("/board/index.jsp").forward(req, resp);
	}
}