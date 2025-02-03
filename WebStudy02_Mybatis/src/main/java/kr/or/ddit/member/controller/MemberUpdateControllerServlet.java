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

import kr.or.ddit.auth.exception.AuthenticateException;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;


@WebServlet("/member/memberUpdate.do")
public class MemberUpdateControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. 인증 객체 확보 
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		// 이미 실패한 요청에 대한 정보를 가진 객체 
		MemberVO member =  (MemberVO) session.getAttribute("member");
		Map<String, String> errors =  (Map)session.getAttribute("errors");
		if(member == null) {
			// 수정을 위한 요청이 처음 발생한 경우.
			//2. 현재 사용자의 기본 정보 조회
			member = service.readMember(authMember.getMemId());
			//3. scope를 이용해 2번에서 조회한 모델을 전달
			req.setAttribute("member", member);	
		}else {
			// 실패한 요청으로 디라이렉션한 경우.
			req.setAttribute("member", member);
			req.setAttribute("errors", errors);
			session.removeAttribute("member");
			session.removeAttribute("errors");
		}
//		4. logicalName 결정 
		String logicalName = "member/memberEdit";
//		5. view layer로 이동 
		
		if(logicalName.startsWith("redirect:")) {
			String location = logicalName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		}else {
			String path = "/" + logicalName + ".tiles";
			req.getRequestDispatcher(path).forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//memberinsert랑 비슷함 
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		//인증 실패 ,수정 성공, 수정 실패 
		//1. 인코딩 값 설정
		req.setCharacterEncoding("utf-8");
		MemberVO member = new MemberVO();
		member.setMemId(authMember.getMemId());
		//2. 파라미터 설정 -> beanutils로 다 가져옴
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		//3. 서비스에서 값 받아옴 -> logicalName 설정
		//에러를 해쉬맵으로 셋팅함
		Map<String, String> errors = new HashMap<>();
		validate(member, errors);
		//에러가 비어 있으면 valid , 비어있지 않으면 !valid
		boolean valid = errors.isEmpty();
		
		//4. 수정 성공 실패 여부 확인
		session.setAttribute("member", member);
		session.setAttribute("errors", errors);
		
		String logicalName = null;
		if(valid) {
			try {
				//수정 성공 
				service.modifyMember(member);
				//완료 후 삭제
				session.removeAttribute("member");
				session.removeAttribute("errors");
				
				// 기존 인증 객체 변경 
				session.setAttribute("authMember", service.readMember(member.getMemId()));
				logicalName = "redirect:/mypage";
				
			} catch(AuthenticateException e) {
				session.setAttribute("message", "비밀번호 오류");
				logicalName = "redirect:/member/memberUpdate.do";
			}
		} else {
			//수정 실패시 다시 원래 폼 화면
			logicalName = "redirect:/member/memberUpdate.do";
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
//		if (StringUtils.isBlank(member.getMemId())) {
//			errors.put("memId", "회원아이디 누락");
//		}
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
