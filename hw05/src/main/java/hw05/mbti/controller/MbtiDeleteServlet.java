package hw05.mbti.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import hw05.mbti.service.MbtiService;
import hw05.mbti.service.MbtiServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//기 mbti 유형 삭제 
@WebServlet("/mbti/delete")
public class MbtiDeleteServlet extends HttpServlet{
	private MbtiService service = new MbtiServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String what = req.getParameter("what");
		
		if(StringUtils.isBlank(what)) {
			resp.sendError(400);
			return;
		}
		
		int cnt = service.deleteMbti(what);
		
		String path =req.getContextPath()+"/mbti/list";
		// 삭제 성공 여부 
		if(cnt > 0) {
			resp.sendRedirect(path);
		}else {
			resp.sendError(400,"삭제 실패~");
		}
		
	}
}
