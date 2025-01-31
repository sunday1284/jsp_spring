package hw06.person.dao;

import java.util.List;

import hw06.person.vo.PersonVO;

public interface PersonDao {
	
	
	/** 전체 목록 불러오기
	 * @return x
	 */
	public List<PersonVO> personList();

	/** 해당 마이페이지 
	 * @param id
	 * @return 기본키 
	 */
	public PersonVO getPerson(String id);
	
	
	/** 데이터 추가하기
	 * @param person
	 * @return person 안에 있는 모든 객체 추가 가능
	 */
	public int insertPerson(PersonVO person);
	
	/** 데이터 수정하기
	 * @param person
	 * @return person 안에 있는 모든 객체 수정 가능
	 */
	public int updatePerson(PersonVO person);

	
	/** 데이터 삭제하기
	 * @param id
	 * @return 기본키를 받아서 삭제함
	 */
	public int deletePerson(String id);
}
