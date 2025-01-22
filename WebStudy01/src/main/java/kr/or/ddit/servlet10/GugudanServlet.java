package kr.or.ddit.servlet10;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/gugudan", loadOnStartup = 2)
public class GugudanServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		// 1.minDan, maxDan 파라미터값 설정
		String min = req.getParameter("minDan");
		String max = req.getParameter("maxDan");
		// 3.누락된 경우, 기본값(2, 9)
		int minDan = 2;
		int maxDan = 9;
		// 2.검증(숫자 파라미터)
		if (StringUtils.isNumeric(min)) {
		// 4.검증 통과시, 해당 파라미터를 파싱하고, 구구단의 범위 설정.
			minDan = Integer.parseInt(min);
		}
		if (StringUtils.isNumeric(max)) {
			maxDan = Integer.parseInt(max);
		}
		// 5.scoper 를 통해 구구단의 범위 전달
		
		// 6. 뷰 레이어 선택
		req.setAttribute("section4", "/WEB-INF/views/10/gugudan.jsp");
		String path = "/WEB-INF/views/files/layout.jsp";
		// 7. 뷰 이동
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
