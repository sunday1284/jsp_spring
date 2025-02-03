package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;


@WebServlet("/member/memberDetail.do")
public class MemberDetailControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1.character encoding 설정 
		req.setCharacterEncoding("utf-8");
//		2. 필수 파라미터 확보 
		String memId = req.getParameter("who");
//		3. 파라미터 검증 
		if(StringUtils.isBlank(memId)) {
//			누락 : 400 에러 전송 
			resp.sendError(400,"필수 파라미터 누락");
			return;
		}
//		4. model 확보(logic 사용 - readMember) 
		MemberVO member = service.readMember(memId);
//		5. scope 에 저장 : member (attribute name)
		req.setAttribute("member", member);
//		6. view layer 선택(/WEB-INF/views/member/memberDetail.jsp)
		String path = "/WEB-INF/views/member/memberDetail.jsp";
//		7. dispatch forward로 이동.
		req.getRequestDispatcher(path).forward(req, resp);
		
	}
}
