package kr.or.ddit.servlet04;

import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/04/mime")
public class FindMimeControllerServlet extends HttpServlet{
	private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String filename = req.getParameter("filename");
		String mime = application.getMimeType(filename);
		req.setAttribute("mime", mime);
		String path = "/WEB-INF/views/04/mime.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
















