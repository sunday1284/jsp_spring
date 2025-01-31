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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean CreatePerson(PersonVO person) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ModifyPerson(PersonVO person) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean RemovePerson(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
