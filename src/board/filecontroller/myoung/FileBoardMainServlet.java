package board.filecontroller.myoung;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fileboad/main")
public class FileBoardMainServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String content=(String)req.getAttribute("content");
	if(content==null) {
		content="/fileboard/main.jsp";
	}
	req.setAttribute("content", content);
	getServletContext().setAttribute("cp", req.getContextPath());
	req.getRequestDispatcher("/fileboard/index.jsp").forward(req, resp);
	}
	
}
