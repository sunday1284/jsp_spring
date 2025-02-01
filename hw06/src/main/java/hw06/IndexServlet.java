package hw06;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/index.do")
public class IndexServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//마일즈 셋팅 -> miles로 셋팅하면 dashboard 스타일 적용 가능
		String logicalName ="index";
		String path = "/"+logicalName+".miles";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
