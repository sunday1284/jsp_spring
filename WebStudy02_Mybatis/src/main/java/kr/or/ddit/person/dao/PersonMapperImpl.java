package kr.or.ddit.person.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.person.vo.PersonVO;

public class PersonMapperImpl implements PersonMapper {
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertPerson(PersonVO person) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			PersonMapper proxy = sqlSession.getMapper(PersonMapper.class);
			return proxy.insertPerson(person);
		}
	}

	@Override
	public List<PersonVO> selectPersonList() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			PersonMapper proxy = sqlSession.getMapper(PersonMapper.class);
			return proxy.selectPersonList();
		}
	}

	@Override
	public PersonVO selectPerson(String id) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			PersonMapper proxy = sqlSession.getMapper(PersonMapper.class);
			return proxy.selectPerson(id);
		}
	}

	@Override
	public int updatePerson(PersonVO person) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePerson(String id) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			PersonMapper proxy = sqlSession.getMapper(PersonMapper.class);
			return proxy.deletePerson(id);
		}
	}

}
