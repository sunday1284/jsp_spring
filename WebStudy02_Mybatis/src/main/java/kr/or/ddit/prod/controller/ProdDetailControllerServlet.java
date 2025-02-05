package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.vo.ProdVO;

@WebServlet("/prod/prodDetail.do")
public class ProdDetailControllerServlet extends HttpServlet{
	//1. 서비스 호출
	private ProdService service = new ProdServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//2. characterSet 인코딩 
		req.setCharacterEncoding("utf-8");
		
		//3. 필수 파라미터 설정
		String prodId = req.getParameter("what");
		
		
		//4. 필수 파라미터 검증
		if(StringUtils.isBlank(prodId)) {
			resp.sendError(400, "필수 파라미터 누락");
			return;
		}
		
		//5. 다오에서 설정한 작업을 서비스에 담아줌
		ProdVO prod = service.readProd(prodId);
		
		
		//6. scope에 작업한 내용을 담아줌
		req.setAttribute("prod", prod);
		//7. logicalName 설정 
		String logicalName = "prod/prodDetail";
		//8.타일즈로 이동 
		if (logicalName.startsWith("redirect:")) {
			String location = logicalName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		} else {
			String path = "/" + logicalName + ".tiles";
			req.getRequestDispatcher(path).forward(req, resp);
		}
		
		
	}
	
}
