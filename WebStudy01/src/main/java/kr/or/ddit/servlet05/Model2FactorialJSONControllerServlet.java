package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/05/model2-fatorial.json")
public class Model2FactorialJSONControllerServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String operandStr = req.getParameter("operand");
		
		if(operandStr==null || operandStr.trim().isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
			return;
		}
		//정규식 검증
		if(!operandStr.matches("[0-9]{1,3}")) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "피연산자의 형식이 잘못됐음");
			return;
		}
		
//		formatting : 특정 타입의 데이터를 일정한 패턴의 문자열로 변환하는 작업 
//		int number = 3;
//		String text = String.format("%d",number);
//		parsing : 일정한 패턴의 문자열을 특정 타입의 데이터로 변환하는 작업
		
		//p연산자
		int operand = Integer.parseInt(operandStr);;
		long result = factorial(operand);
		//information 전달
		req.setAttribute("operand", operand);
		req.setAttribute("result", result);
		
		String path = "/jsonView";
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
