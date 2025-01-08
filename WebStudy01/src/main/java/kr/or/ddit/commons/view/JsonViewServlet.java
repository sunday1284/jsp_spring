package kr.or.ddit.commons.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/jsonView")
public class JsonViewServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> requestScope = new HashMap<>();
		Enumeration<String> en = req.getAttributeNames();
		while (en.hasMoreElements()) {
			String attributeName = (String) en.nextElement();
			Object attributeValue = req.getAttribute(attributeName);
			requestScope.put(attributeName, attributeValue);
		}
		resp.setContentType("application/json; charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		new ObjectMapper().writeValue(out, requestScope);
		out.close();
	}
}
