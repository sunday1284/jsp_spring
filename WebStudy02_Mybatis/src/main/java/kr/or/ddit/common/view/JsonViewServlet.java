package kr.or.ddit.common.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/jsonView")
//json 마샬링 작업 ->공통 
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
