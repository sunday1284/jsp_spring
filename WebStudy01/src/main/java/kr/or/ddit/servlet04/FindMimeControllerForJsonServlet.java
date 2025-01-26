package kr.or.ddit.servlet04;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/04/mime/forJson")
public class FindMimeControllerForJsonServlet extends HttpServlet{
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
		
		Map<String, Object> requestScope = new HashMap<>();
		
		Enumeration<String> en = req.getAttributeNames();
		while (en.hasMoreElements()) {
			String attributeName = (String) en.nextElement();
			Object attributeValue = req.getAttribute(attributeName);
			requestScope.put(attributeName, attributeValue);
		}
		
//		1. content type 결정
		String contentType = getServletContext().getMimeType("dmy.json");
		resp.setContentType(contentType);
		
//		2. marshalling + serialization : serialization
		PrintWriter out = resp.getWriter();
		new ObjectMapper().writeValue(out, requestScope);
		out.close();
//		String path = "/WEB-INF/views/04/mime.jsp";
//		req.getRequestDispatcher(path).forward(req, resp);
	}
}
















