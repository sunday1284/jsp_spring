package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.member.vo.MemberVO;

class MemberDAOImplTest {
	MemberDAO dao = new MemberDAOImpl();

	@Test
	void testInsertMember() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectMemberList() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectMember() {
		assertDoesNotThrow(()->{
			MemberVO member = dao.selectMember("b001");
			assertNotNull(member);
			String id = "admin' or ' 1'=' 1";
			member = dao.selectMember(id);
			assertNull(member);
		});
	}

	@Test
	void testUpdateMember() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteMember() {
		fail("Not yet implemented");
	}

}
