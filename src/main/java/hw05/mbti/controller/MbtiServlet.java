package hw05.mbti.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import hw05.mbti.service.MbtiService;
import hw05.mbti.service.MbtiServiceImpl;
import hw05.mbti.vo.MbtiVO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//선택한 mbti 하나의 유형에 대한 상세 정보 제공
@WebServlet("/mbti/detail")
public class MbtiServlet extends HttpServlet{
	//1. 셋팅한 서비스 가져옴
	private MbtiService service = new MbtiServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//2. 인코딩 작업
		req.setCharacterEncoding("utf-8");
		
		//3. 파라미터 설정
		String mbtitype = req.getParameter("what");
		//4. 필수 파라미터 검증
		if(StringUtils.isBlank(mbtitype)) {
			resp.sendError(400, "필수 파라미터 누락");
			return;
		}
		//5. 서비스 호출 
		MbtiVO mbtiInfo = service.getMbti(mbtitype);
		
		//6. 셋팅 값을 스코프에 저장
		req.setAttribute("mbtiInfo", mbtiInfo);
		
		//7. forward로 해당 jsp로 이동
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/getMbtil.jsp");
		dispatcher.forward(req, resp);
	}
}
