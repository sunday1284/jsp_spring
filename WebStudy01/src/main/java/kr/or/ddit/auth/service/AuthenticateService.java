package kr.or.ddit.auth.service;

import kr.or.ddit.member.vo.MemberVO;

/**
 * 아이디와 비밀번호 기반의 인증 로직을 가진 Business logic layer
 * 
 */
public interface AuthenticateService {
	
	/**
	 * 아이디와 비밀번호로 신원 확인 절차를 거친 후 인증 여부를 결정함.
	 * @param username
	 * @param password
	 * @return
	 */
	public MemberVO authenticate(String username, String password);
}
