package hw06.person.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import hw06.db.CustomSqlSessionFactoryBuilder;
import hw06.person.vo.PersonVO;

public class PersonDaoImpl implements PersonDao {
	private SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	@Override
	public List<PersonVO> personList() {
		
		try(
			SqlSession session = sqlSessionFactory.openSession();
		){
			
			List<PersonVO> perList = new ArrayList<>();
			
			perList = session.selectList("hw06.person.dao.PersonDao.personList");
			
			
			return perList;		
		}
		
	}

	@Override
	public PersonVO getPerson(String id) {	
		
		try(
			SqlSession session = sqlSessionFactory.openSession();
		){
			PersonVO person = null;
			
			person = session.selectOne("hw06.person.dao.PersonDao.getPerson", id);
			
			
			return person;	
		} 
		
		
	}

	@Override
	public int insertPerson(PersonVO person) {	
		try(
			SqlSession session = sqlSessionFactory.openSession();
		){
			
			int cnt = session.insert("hw06.person.dao.PersonDao.insertPerson", person);
			
			if(cnt > 0)
				session.commit();
			
			return cnt;
		} 
		
	}

	@Override
	public int updatePerson(PersonVO person) {
		
		try (
			SqlSession session = sqlSessionFactory.openSession();
		) {
			
			int cnt = session.update("hw06.person.dao.PersonDao.updatePerson", person);
			
			if(cnt>0)
				session.commit();
			
			return cnt;
		} 
		
		
		
	}

	@Override
	public int deletePerson(String id) {
		try (
			SqlSession session = sqlSessionFactory.openSession();
		) {
			
			int cnt = session.delete("hw06.person.dao.PersonDao.deletePerson", id);
			
			if(cnt > 0)
				session.commit();
			
			return cnt;
		} 
		
		
	}
	
}
