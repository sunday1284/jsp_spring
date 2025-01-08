package kr.or.ddit.servlet02;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * client 의 get 요청을 처리함.
 * 1. resource path : /02/now.do
 * 2. 응답 컨텐츠의 형태
 * 	  <h4>현재 서버의 시각</h4>
 * 
 * MIME (Multipurpose Internet Mail Extension)
 *  : 두 피어사이의 전송 컨텐츠의 종류를 표현하는 텍스트.
 *   main_type/sub_type;charset=encoding
 *   ex) text/css, text/html, image/gif, video/mpeg
 * 
 */
@WebServlet("/02/now.json")
public class ServerTimeJSONServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		raw data -> information -> content
		String dummyName = "now.json";
		String mime = getServletContext().getMimeType(dummyName);
		resp.setContentType(mime);
		Date rawData = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
		String information = formatter.format(rawData);
		StringBuffer content = new StringBuffer();
		
		String jsonPattern = "{\"time\":\"%s\"}";
		
		content.append(
			String.format(jsonPattern, information)	
		);
		resp.getWriter().print(content);
	}
}












