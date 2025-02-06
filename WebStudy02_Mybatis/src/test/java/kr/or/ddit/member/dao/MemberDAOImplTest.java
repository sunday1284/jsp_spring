package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.member.vo.MemberVO;

class MemberDAOImplTest {
	MemberDAO dao = new MemberDAOImpl();

	@Test
	void testInsertMember() {
		MemberVO dummy = dao.selectMember("a001");
		System.out.printf("mem_bir : %s\n", dummy.getMemBir());
		System.out.printf("mem_memorialday : %s\n", dummy.getMemMemorialday());
		dummy.setMemId("n002");
		assertDoesNotThrow(()->dao.insertMember(dummy));
	}

	@Test
	void testSelectMemberList() {
		assertDoesNotThrow(()->{
			List<MemberVO> memberList = dao.selectMemberList(paging);
			assertNotEquals(0, memberList.size());
		});
	}

	@Test
	void testSelectMember() {
		assertDoesNotThrow(()->{
			MemberVO member = dao.selectMember("a001");
			assertNotNull(member);
			assertEquals(false, member.isMemDelete());
			member.isMemDelete();
			String id = "admin' or ' 1'=' 1";
			member = dao.selectMember(id);
			assertNull(member);
		});
	}

	@Test
	void testUpdateMember() {
		MemberVO member = dao.selectMember("a004");
		member.setMemAdd1("대전");
		member.setMemAdd2("광역시");
		assertDoesNotThrow(()->{
			assertEquals(1, dao.updateMember(member));
		});
	}

	
	@Test
	void testDeleteMember() {
		assertDoesNotThrow(()->{
			assertEquals(1, dao.deleteMember("a001"));	
		});
	}

}
