package hw06.miles;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.miles")
public class MilesDispatherServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
//		/member/memberList.miles
		//정규식 만들기 -> \w(w안에 ex)->/member/memberlist)
		Pattern regexp = Pattern.compile("/([\\w/]+)\\.miles");
		Matcher matcher = regexp.matcher(servletPath);
		String logicalName = null;
		if(matcher.find()) {
			logicalName = matcher.group(1);
		}
		System.out.printf("servlet path : %s\n", servletPath);
		String Content = "/WEB-INF/views/"+logicalName+".jsp";
		req.setAttribute("content", Content);
		String path="/WEB-INF/views/layouts/templateDashBoard.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}