package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.member.service.AuthenticateServiceImpl;

//redirect는 파라미터를 넘기지 않고 넘어가게 하는법
@WebServlet("/Login/LoginProcess")
public class LogingProcessControllerServlet extends HttpServlet {
	private AuthenticateService service = new AuthenticateServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
//		1. 파라미터 수신
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		
		HttpSession session = req.getSession();
		if(session.isNew()) {
			resp.sendError(400);
			return;
		}
		String logical = null;
		
//		2. 검증(필수 파라미터)
		if (StringUtils.isBlank(memId) || StringUtils.isBlank(memPass)) {
//			1) 누락된 경우, loginForm으로 이동 (redirect)
//			   메시지(message) 전달(session scope로 ... flash attribute : set->get->remove)
			session.setAttribute("message", "아이디나 비밀번호 누락");
			logical = "/login/loginForm.jsp";
		} else {
			boolean login = service.authenticate(memId, memPass);

//			2) 인증 성공 여부 판단 
			if (login) {
//				a) 성공 : 웰컴페이지로 이동(redirect)
				session.setAttribute("authMember", memId);
				logical = "/";
			} else {
//				b) 실패 : loginForm으로 이동 (redirect)
//				메시지(message) 전달(session scope로 ... flash attribute)
				session.setAttribute("message", "아이디나 비밀번호 확인!");
				logical = "/login/loginForm.jsp";
				
			} //인증 여부 판단 end

		} // 파라미터 누락 여부 판단 end
		String location = req.getContextPath() + logical;
		resp.sendRedirect(location);
	}
}
