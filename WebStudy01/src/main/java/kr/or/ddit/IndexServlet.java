package kr.or.ddit;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/index.do")
public class IndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String path = "/WEB-INF/views/index.jsp";
		String logicalName ="index";
		String path = "/"+logicalName+".miles";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
