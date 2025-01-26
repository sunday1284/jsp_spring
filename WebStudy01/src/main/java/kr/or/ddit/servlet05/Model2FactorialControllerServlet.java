package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Model2 : request 와 response를 별개의 객체로 처리하는 설계 방식 
 * request 처리 (controller layer)
 *  1. request 분석 (line, header, body) + 검증 
 *  2. information(Model) 생성 
 *  3. scope를 선택하고 model 을 저장 (setAttribute...) 
 *  4. view layer 선택 (request accept header를 기준으로 선택) : accept헤더에 따른 컨텐츠 협상 과정 
 *  5. 선택된 view layer로 이동 
 * response 생성 (view layer) : Model을 컨텐츠화해서 응답 전송 
 * 
 */
@WebServlet("/05/model2-factorial.do")
public class Model2FactorialControllerServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/05/factorialForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String operandStr = req.getParameter("operand");
		
		//정규식 검증
		if(operandStr==null || operandStr.trim().isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
			return;
		}
		if(!operandStr.matches("[0-9]{1,3}")) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "피연산자의 형식이 잘못됐음");
			return;
		}
		
//		formatting : 특정 타입의 데이터를 일정한 패턴의 문자열로 변환하는 작업 
//		int number = 3;
//		String text = String.format("%d",number);
//		parsing : 일정한 패턴의 문자열을 특정 타입의 데이터로 변환하는 작업
		
		//p연산자
		int operand = Integer.parseInt(operandStr);
		long result = factorial(operand);
		//information 전달
		req.setAttribute("operand", operand);
		req.setAttribute("result", result);
		
		//accept라는 헤더를 꺼냄
		String accept = req.getHeader("accept");
		// view layer 이동
		String path = null;
		
		//accept 헤더에 json이라는 문자가 포함되어 있을 때
		if(accept.contains("json")) {
			path = "/jsonView";
		}else {
			path = "/WEB-INF/views/05/factorialView.jsp";	
		}
		
		req.getRequestDispatcher(path).forward(req, resp);
	}

	/* 리팩토링 작업 */
	private long factorial(int operand) {
		if (operand <= 0) {
			throw new IllegalArgumentException("factorial 연산은 양의 정수만 처리 가능.");
		} else {
			if (operand == 1) {
				return 1;
			} else {
				return operand * factorial(operand - 1);
			}
		}
	}

	
}
