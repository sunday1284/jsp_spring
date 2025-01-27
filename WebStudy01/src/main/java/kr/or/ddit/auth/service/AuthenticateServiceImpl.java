package kr.or.ddit.auth.service;

import kr.or.ddit.auth.exception.AuthenticateException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.member.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService {

	private MemberDAO dao = new MemberDAOImpl();
	
	
	@Override
	public MemberVO authenticate(String username, String password) {
		MemberVO member = dao.selectMember(username);
		if(member!=null) {
			String savedPass = member.getMemPass();	
			if(savedPass.equals(password)) {
				return member;
			}else {
				throw new AuthenticateException("비밀번호 오류");
			}
		}else {
			throw new AuthenticateException("존재하지 않는 회원.");
		}
	}

}
