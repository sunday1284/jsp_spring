package hw06.person.service;

import java.util.List;

import hw06.person.vo.PersonVO;

public interface PersonService {
	
	
	/** 전체 목록 보기
	 * @return x 
	 */
	public List<PersonVO> ReadpersonList();
	
	/** 마이페이지
	 * @param id
	 * @return 기본키
	 */
	public PersonVO ReadgetPerson(String id);
	
	
	/** 데이터 추가하기
	 * @param person
	 * @return 성공 -> true 실패-> false
	 */
	public boolean CreatePerson(PersonVO person);
	
	/**
	 * 데이터 업데이트
	 * @param person
	 * @return 성공 -> true 실패-> false
	 */
	public boolean ModifyPerson(PersonVO person);
	
	/**
	 * 데이터 삭제
	 * @param id -> 기본키 
	 * @return 성공 -> true 실패-> false
	 */
	public boolean RemovePerson(String id);
}
