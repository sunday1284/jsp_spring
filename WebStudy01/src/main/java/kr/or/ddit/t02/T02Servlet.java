package kr.or.ddit.t02;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//확장자 매핑
@WebServlet("*.t02")
public class T02Servlet extends HttpServlet {	
	private ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();

		//정규식의 그룹화
		//(\w{2,4})
		
		
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String method = req.getMethod().toLowerCase();
		if("get".equals(method) || "post".equals(method)) {
			
			List<String> allLines =  readTemplate(req);
			
			StringBuffer content = exchangeData(allLines, req);
			
			
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(content);
			out.close();
		} else {
			resp.sendError(405, "t02 템플릿 엔진은 get/post 요청만 처리함.");
		}
	}
	private StringBuffer exchangeData(List<String> allLines, HttpServletRequest req) {
		
		String checkTarget = allLines.stream().collect(Collectors.joining("\n"));
		String regex = "#\\[(\\w+)\\]";
		Pattern pattern = Pattern.compile(regex);
		StringBuffer content = new StringBuffer();
		Matcher matcher = pattern.matcher(checkTarget);
		while(matcher.find()) {
			String name = matcher.group(1);
			System.out.println(name);
			Object data = req.getAttribute(name);
			matcher.appendReplacement(content, data.toString());
		}
		
		matcher.appendTail(content);
		return content;
	}
	private List<String> readTemplate(HttpServletRequest req) 
			throws IOException{
		String requestURI = req.getRequestURI(); //context path + resource path
		System.out.printf("request uri : %s\n", requestURI);
		String servletPath = req.getServletPath();
		System.out.printf("servlet path : %s\n", servletPath);
		String realPath = application.getRealPath(servletPath);
		Path t02Path = Paths.get(realPath);
		List<String> allLines = Files.readAllLines(t02Path);
		return allLines;
	}
}
