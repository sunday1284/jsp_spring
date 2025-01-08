package kr.or.ddit.servlet02;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
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
public class ServerTimeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		raw data -> information -> content
		resp.setContentType("text/html;charset=UTF-8");
		Date rawData = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
		String information = formatter.format(rawData);
		StringBuffer content = new StringBuffer();
		content.append(" <html>       ");
		content.append(" <body>       ");
		content.append(
			String.format(" <h4>%s</h4>  ", information)	
		);
		content.append(" </body>      ");
		content.append(" </html>      ");
		resp.getWriter().print(content);
	}
}












