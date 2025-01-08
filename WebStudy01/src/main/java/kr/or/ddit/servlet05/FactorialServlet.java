package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/05/factorial.do")
public class FactorialServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int operand = 10;
		long result = factorial(operand);
		StringBuffer content = new StringBuffer();
		
		content.append("<html>                ");
		content.append("<body>                ");
		content.append(
				String.format("<h4> %d! = %d </h4>", operand, result)
		);
		content.append("</body>               ");
		content.append("</html>               ");
		
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println(content);
		out.close();
	}
	
	/* 리팩토링 작업 */
	private long factorial(int operand){
		if(operand <= 0){
			// 예외를 직접 만듦
			throw new IllegalArgumentException("팩토리얼 연산은 양의 정수만 처리 가능.");
		}
		if(operand == 1){
			return 1;
		}else{
			return operand * factorial(operand - 1);		
		}
	}
}
