package kr.or.ddit.db;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

import kr.or.ddit.member.vo.MemberVO;

class CustomSqlSessionFactoryBuilderTest {

	@Test
	void testGetSqlSessionFactory() {
		assertDoesNotThrow(()->{
			SqlSessionFactory factory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
			try(
			    SqlSession session = factory.openSession();		
			){
				List<MemberVO> list = session.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemberList");
				System.out.println(list);
			}
			
		});
	}

}
