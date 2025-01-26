package hw05.mbti.controller;

import java.io.IOException;
import java.util.List;

import hw05.mbti.service.MbtiService;
import hw05.mbti.service.MbtiServiceImpl;
import hw05.mbti.vo.MbtiVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mbti/list")
public class MbtiListServlet extends HttpServlet{
	private MbtiService service = new MbtiServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 인코딩값 셋팅
		req.setCharacterEncoding("utf-8");
		
		//2. 전체 보여주기니까 파라미터값 셋팅 x 
		
		//3. 서비스에서 다오에서 작업한 코드를 셋팅함
		List<MbtiVO> mbtilist = service.MbtiList();
		
		//4. 해당 리스트 값을 Scope에 저장함
		req.setAttribute("mbtilist", mbtilist);
		
		//5. 해당 경로 지정 -> forward로 보냄
		String path = "/WEB-INF/views/MbtiList.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
		
	}
}
