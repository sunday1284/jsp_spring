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
			if(member.isMemDelete()) {
				//이미 탈퇴한 회원
				throw new AuthenticateException("이미 탈퇴한 회원임.");
			}else {
				// 아직 활동중인 회원 
				String savedPass = member.getMemPass();	
				if(savedPass.equals(password)) {
					return member;
				}else {
					throw new AuthenticateException("비밀번호 오류");
				}
				
			}
		}else {
			throw new AuthenticateException("존재하지 않는 회원.");
		}
	}

}
