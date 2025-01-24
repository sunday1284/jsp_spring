package kr.or.ddit.member.service;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.member.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService {

	private MemberDAO dao = new MemberDAOImpl();
	
	
	@Override
	public boolean authenticate(String username, String password) {
		MemberVO member = dao.selectMember(username);
		if(member !=null) {
			String savedPass = member.getMemPass();	
			return savedPass.equals(password);
		}else {
			return false;
		}
	}

}
