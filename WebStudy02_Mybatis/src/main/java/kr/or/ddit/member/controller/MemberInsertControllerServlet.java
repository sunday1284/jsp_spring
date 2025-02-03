package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/member/memberInsert.do")
public class MemberInsertControllerServlet extends HttpServlet {
	private MemberService service = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setAttribute("member", session.getAttribute("member")); 
		req.setAttribute("errors", session.getAttribute("errors")); 
		//세션 삭제 -> flash attribute 방식
		session.removeAttribute("member");
		session.removeAttribute("errors");
		
		// 가입 form UI 제공
		String logicalName = "member/memberForm";

		if (logicalName.startsWith("redirect:")) {
			String location = logicalName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		} else {
			String path = "/" + logicalName + ".tiles";
			req.getRequestDispatcher(path).forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		// 전송된 파라미터들로 가입 처리
//		1. character set 설정(body 디코딩 설정)
		req.setCharacterEncoding("utf-8");
//		2. 17개의 파라미터 수신 --> MemberVO에 바인드(setter) 
		MemberVO member = new MemberVO();
		session.setAttribute("member", member);
//		member.setMemId(req.getParameter("memId"));
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}

		String logicalName = null;
//		3. 요청 검증 
		Map<String, String> errors = new HashMap<>();
		session.setAttribute("errors", errors);
		validate(member, errors);
		boolean valid = errors.isEmpty();
		if (valid) {
//		2) 검증 통과 
//			a) 등록(createMember) 처리
			service.createMember(member);
//			b) 등록 성공 : 웰컴 페이지로 이동(redirect)
			logicalName = "redirect:/";
			session.removeAttribute("member");
			session.removeAttribute("errors");
		} else {
//		1) 검증 실패
//			: 가입 양식으로 다시 이동(기존 입력 데이터 검증 결과 메시지를 전달).
//				dispatch -> redirect
			logicalName = "redirect:/member/memberInsert.do";
		}

		if (logicalName.startsWith("redirect:")) {
			String location = logicalName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		} else {
			String path = "/" + logicalName + ".tiles";
			req.getRequestDispatcher(path).forward(req, resp);
		}

	}

	// Call by Reference 구조
	private void validate(MemberVO member, Map<String, String> errors) {
		if (StringUtils.isBlank(member.getMemId())) {
			errors.put("memId", "회원아이디 누락");
		}
		if (StringUtils.isBlank(member.getMemPass())) {
			errors.put("memPass", "비밀번호 누락");
		}
		if (StringUtils.isBlank(member.getMemName())) {
			errors.put("memName", "회원명 누락");
		}
		if (StringUtils.isBlank(member.getMemZip())) {
			errors.put("memZip", "우편번호 누락");
		}
		if (StringUtils.isBlank(member.getMemAdd1())) {
			errors.put("memAdd1", "주소1 누락");
		}
		if (StringUtils.isBlank(member.getMemAdd2())) {
			errors.put("memAdd2", "주소2 누락");
		}
		if (StringUtils.isBlank(member.getMemHp())) {
			errors.put("memHp", "휴대폰 누락");
		}

	}
}
