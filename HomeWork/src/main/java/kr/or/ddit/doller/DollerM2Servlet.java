package kr.or.ddit.doller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/currency/exchange")
public class DollerM2Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/05/dollerForm.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String amountStr = req.getParameter("amount");

		// 정규식 검증
		if (amountStr == null || amountStr.trim().isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
			return;
		}
		if (!amountStr.matches("[0-9]{1,3}")) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "피연산자의 형식이 잘못됐음");
			return;
		}
		
		int amount = Integer.parseInt(amountStr);
		long result = doller(amount);
		//정보 전달
		req.setAttribute("amount", amount);
		req.setAttribute("result", result);
		
		String accept = req.getHeader("accept");
		String path = null;
		
		if(accept.contains("json")) {
			path = "/jsonhome";
		}else {
			path = "/WEB-INF/view/05/dollerView.jsp";
		}
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	/*리팩토링 작업*/
	private long doller(int amount) {
		if(amount <= 0) {
			throw new IllegalArgumentException("달러 계산은 양의 정수만 처리 가능");
		}else {
			long result = amount * 1470L; 
			return result;
			
		}
			
		
	}
}
