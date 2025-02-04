package kr.or.ddit.member.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.auth.exception.AuthenticateException;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.member.vo.MemberVO;

class MemberServiceImplTest {
	MemberService service = new MemberServiceImpl();
	@Test
	void testCreateMember() {
		MemberVO dummy = service.readMember("a002");
		dummy.setMemId("a003");
		assertTrue(service.createMember(dummy));
	}

	@Test
	void testReadMemberList() {
		List<MemberVO> memberList = service.readMemberList();
		assertTrue(memberList.size()>0);
	}

	@Test
	void testReadMember() {
		assertDoesNotThrow(()->service.readMember("b001"));
		
		assertThrows(UserNotFoundException.class,
					()-> service.readMember("asdasf"));		
	}

	@Test
	void testModifyMember() {
		MemberVO member = service.readMember("a001");
		member.setMemAdd1("서울");
		assertDoesNotThrow(() -> {
			assertTrue(service.modifyMember(member));
		});
		member.setMemPass("asdqas");
		assertThrows(AuthenticateException.class, () -> service.modifyMember(member));
	}

	@Test
	void testRemoveMember() {
		MemberVO inputData = new MemberVO();
		inputData.setMemId("j001");
		inputData.setMemPass("asdqfasd");
		assertThrows(AuthenticateException.class, ()-> service.removeMember(inputData));
		inputData.setMemPass("6262");
		assertTrue(service.removeMember(inputData)); 
	}

}
