package kr.or.ddit.member.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.auth.service.AuthenticateService;
import kr.or.ddit.auth.service.AuthenticateServiceImpl;

class AuthenticateServiceImplTest {
	
	AuthenticateService service = new AuthenticateServiceImpl();
	
	@Test
	void testAuthenticate() {
//		boolean result = service.authenticate("b001", "1004");
//		assertEquals(true, result);
	}

}
