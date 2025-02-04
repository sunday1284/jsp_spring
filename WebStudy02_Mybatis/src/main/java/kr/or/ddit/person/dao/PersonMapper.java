package kr.or.ddit.person.dao;

import java.util.List;

import kr.or.ddit.person.vo.PersonVO;

public interface PersonMapper {
	public int insertPerson(PersonVO person);
	public List<PersonVO> selectPersonList();
	public PersonVO selectPerson(String id);
	public int updatePerson(PersonVO person);
	public int deletePerson(String id);
}
