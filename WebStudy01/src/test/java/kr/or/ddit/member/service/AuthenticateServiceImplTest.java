package kr.or.ddit.member.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.auth.exception.AuthenticateException;
import kr.or.ddit.auth.service.AuthenticateService;
import kr.or.ddit.auth.service.AuthenticateServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

class AuthenticateServiceImplTest {
	
	AuthenticateService service = new AuthenticateServiceImpl();
	
	@Test
	void testAuthenticate() {
		assertThrows(AuthenticateException.class, ()->service.authenticate("b001", "1004"));
		MemberVO result = service.authenticate("a004", "java");
		assertNotNull(result);
	}

}
