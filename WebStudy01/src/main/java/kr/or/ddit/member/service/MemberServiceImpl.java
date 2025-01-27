package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.auth.exception.AuthenticateException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	private MemberDAO dao = new MemberDAOImpl();
	
	@Override
	public boolean createMember(MemberVO member) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MemberVO> readMemberList() {
		return dao.selectMemberList();
	}

	@Override
	public MemberVO readMember(String memId) throws UserNotFoundException {
		MemberVO member = dao.selectMember(memId);
		if(member ==null) {
			throw new UserNotFoundException(memId);
		}
		return member;
	}

	@Override
	public boolean modifyMember(MemberVO member) throws AuthenticateException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeMember(MemberVO inputData) throws AuthenticateException {
		// TODO Auto-generated method stub
		return false;
	}

}
