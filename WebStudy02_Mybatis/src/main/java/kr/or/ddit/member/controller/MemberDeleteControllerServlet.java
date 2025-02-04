package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.auth.exception.AuthenticateException;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/member/memberDelete.do")
public class MemberDeleteControllerServlet extends HttpServlet{
	//의존 관계 형성 -> service
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//1. authMember확보
		HttpSession session = req.getSession();
		MemberVO authMember =  (MemberVO) session.getAttribute("authMember");
		MemberVO inputData = new MemberVO();
		inputData.setMemId(authMember.getMemId());
		inputData.setMemPass(req.getParameter("password"));
		String logicalName = null;
		//2. 성공시 웰컴 , 실패시 mypage로 이동
		try {
			service.removeMember(inputData);
			session.invalidate(); //탈퇴하면 세션 정보를 다 삭제해야함 -> 로그아웃
			logicalName = "redirect:/";	
		}catch(AuthenticateException e){
			session.setAttribute("message", "비밀번호 오류");
			logicalName = "redirect:/mypage";
		}
		
	
		if(logicalName.startsWith("redirect:")) {
			String location = logicalName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		}else {
			String path = "/" + logicalName + ".tiles";
			req.getRequestDispatcher(path).forward(req, resp);
		}
	}
}
