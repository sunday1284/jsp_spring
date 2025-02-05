package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.member.vo.MemberVO;


/**
 * DATA MAPPING 시 주의사항 
 * DATE 나 TIMESTAMP 에 해당하는 포로퍼티 타입을 String으로 설정한 경우.
 * select 에서는 fomatting 이 필요 : TO_CHAR의 컬럼 알리아스가 필요함.
 * insert/update 에서는 parsing 이 필요 : TO_DATE, TO_TIMESTAMP
 */
public class MemberDAOImpl implements MemberDAO {
	//mybatis하고 의존관계 형성 -> 공장 팩토리에 연결
	private SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertMember(MemberVO member) {

		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			//업데이트에도 추가가 가능함
//			sqlSession.update(null)
//			int rowcnt = sqlSession.insert("kr.or.ddit.member.dao.MemberDAO.insertMember", member);
			MemberDAO proxy = sqlSession.getMapper(MemberDAO.class);
			int rowcnt = proxy.insertMember(member);
			sqlSession.commit();
			return rowcnt;
		}
	}

	@Override
	public List<MemberVO> selectMemberList() {
		
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			//업데이트에도 추가가 가능함
//			sqlSession.update(null)
//			List<MemberVO> memList = sqlSession.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemberList");
			MemberDAO proxy = sqlSession.getMapper(MemberDAO.class);
			return proxy.selectMemberList();
		}
			
	}

	@Override
	public MemberVO selectMember(String memId) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
//			return sqlSession.selectOne("kr.or.ddit.member.dao.MemberDAO.selectMember");
			//다오 인터페이스에서 가져온다 -> 안정적인 방법 
			MemberDAO proxy = sqlSession.getMapper(MemberDAO.class);
			return proxy.selectMember(memId);
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
//			int rowcnt = sqlSession.update("kr.or.ddit.member.dao.MemberDAO.updateMember", member);
			MemberDAO proxy = sqlSession.getMapper(MemberDAO.class);
			int rowcnt = proxy.updateMember(member);
			sqlSession.commit();
			return rowcnt;
		} 
	}

	@Override
	public int deleteMember(String memId) {
		try(
		    SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){ 
//			return sqlSession.delete("kr.or.ddit.member.dao.MemberDAO.deleteMember",memId);
			MemberDAO proxy = sqlSession.getMapper(MemberDAO.class);
			return proxy.deleteMember(memId);
		}
	}

}
