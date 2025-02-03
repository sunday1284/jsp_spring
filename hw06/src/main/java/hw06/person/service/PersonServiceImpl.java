package hw06.person.service;

import java.util.List;

import hw06.person.dao.PersonDao;
import hw06.person.dao.PersonDaoImpl;
import hw06.person.vo.PersonVO;

public class PersonServiceImpl implements PersonService {
	
	private PersonDao dao = new PersonDaoImpl();
	
	@Override
	public List<PersonVO> ReadpersonList() {
		return dao.personList();
	}

	@Override
	public PersonVO ReadgetPerson(String id) {		
		return dao.getPerson(id);
	}

	@Override
	public boolean CreatePerson(PersonVO person) {
		int cnt = dao.insertPerson(person);
		return cnt > 0;
	}

	@Override
	public boolean ModifyPerson(PersonVO person) {
		int cnt = dao.updatePerson(person);
		return cnt > 0;
	}

	@Override
	public boolean RemovePerson(String id) {
		int cnt = dao.deletePerson(id);
		return cnt > 0;
	}
	
}
