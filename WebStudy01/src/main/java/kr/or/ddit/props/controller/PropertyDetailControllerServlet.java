package kr.or.ddit.props.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.props.service.PropertyService;
import kr.or.ddit.props.service.PropertyServiceImpl;
import kr.or.ddit.props.vo.PropertyVO;

@WebServlet("/prop/detail")
public class PropertyDetailControllerServlet extends HttpServlet{
	private PropertyService service = new PropertyServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//1.파라미터 값 설정
		String propertyName = req.getParameter("what");
		
		// 2.파라미터 검증
		if(StringUtils.isBlank(propertyName)) {
			resp.sendError(400,"필수 파라미터 누락");
			return;
		}
		
		//3.모델 확보
		PropertyVO property = service.readProperty(propertyName);
		if(property == null) {
			resp.sendError(404,"그런 프로퍼티 값 없음");
			return;
		}
		
		
		//4.모델 값 설정
		req.setAttribute("property", property);
		//5.뷰로 선택 후  이동
		String path = "/WEB-INF/views/props/propDetail.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
		
		
	}
}
