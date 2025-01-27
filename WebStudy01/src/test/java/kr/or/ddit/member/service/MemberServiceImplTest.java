package kr.or.ddit.member.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.member.vo.MemberVO;

class MemberServiceImplTest {
	MemberService service = new MemberServiceImpl();
	@Test
	void testCreateMember() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

	@Test
	void testRemoveMember() {
		fail("Not yet implemented");
	}

}
