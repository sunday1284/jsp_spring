package kr.or.ddit.servlet06;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/currency")
public class CurrencyExchangeControllerServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/WEB-INF/views/currency/formUI.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	private static final long DOLLAR = 1470;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. 요청 분석과 검증
		String str = req.getParameter("amount");
		String accept = req.getHeader("accept");
//		str == null || str.trim().isEmpty()
		if(StringUtils.isBlank(str)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
			return;
		}
		//숫자인지 확인..
		if(!StringUtils.isNumeric(str)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "원화는 숫자 입력 필요함.");
			return;
		}
		
		long amout = Long.parseLong(str);	
//		2. Model 생성 
		long result = amout * DOLLAR;
//		3. scope에 model 저장
		req.setAttribute("result", result);
//		4. view 선택
		String path = null;
		//json에서 content-type이 대소문자를 무시할때 
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			path = "/jsonView";
		}else if(StringUtils.containsIgnoreCase(accept, "xml")){
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,"xml 컨텐츠는 서비스 불가");
			return;
		}else {
			path = "/WEB-INF/views/currency/resultView.jsp";	
		}
		
//		5. view로 이동
		req.getRequestDispatcher(path).forward(req, resp);
		
	}
}
