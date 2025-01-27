package kr.or.ddit.exception;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import kr.or.ddit.auth.exception.AuthenticateException;
import kr.or.ddit.member.vo.MemberVO;

class ExceptionTest {
	
	private void method1() throws IOException {
		if(1==1) {
			throw new IOException("강제 발생 예외-IOException");
		}
		System.out.println("메소드 1번 실행");
	}
	private int method2() throws NullPointerException{
		if(1==1) {
			throw new NullPointerException("강제 발생 예외-NullPointerException");
		}
		System.out.println("메소드 2번 실행");
		return 30;
	}
	
	private MemberVO method3(String id, String password) {
		if(1==1) {
			throw new AuthenticateException("인증 실패");
		}else {
			return new MemberVO();
		}
	}
	
	@Test
	void testMethod3() {
		try {
			method3("", "");
			System.out.println("로그인 성공");
		}catch (AuthenticateException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Disabled
	@Test
	void test(){
		try {
			method1();
		} catch (IOException e) {
//			e.printStackTrace();
			System.err.println(e.getMessage());
			throw new RuntimeException(e);
		}
//		try {
//			method2();
//		}catch(NullPointerException e) {
//			System.err.println(e.getMessage());
//			throw e;
//		}
	}

}
