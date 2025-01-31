package hw06.person.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;

import hw06.db.ConnectionPoolingFactory;
import hw06.person.vo.PersonVO;

public class PersonDaoImpl implements PersonDao {

	@Override
	public List<PersonVO> personList() {
		StringBuffer sql = new StringBuffer();
		sql.append("	SELECT   ");
		sql.append("    ID,      ");
		sql.append("    NAME,    ");
		sql.append("    GENDER,  ");
		sql.append("    AGE,     ");
		sql.append("    ADDRESS  ");
		sql.append(" FROM         ");
		sql.append("    PERSON   ");
		try(
			Connection conn = ConnectionPoolingFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			ResultSet rs = pstmt.executeQuery();
			
			List<PersonVO> perList = new ArrayList<>();
			while(rs.next()) {
				PersonVO person = new PersonVO();
				person.setId(rs.getString("ID"));
				person.setName(rs.getString("NAME"));
				person.setGender(rs.getString("GENDER"));
				person.setAge(rs.getInt("AGE"));
				person.setAddress(rs.getString("ADDRESS"));
				perList.add(person);
			}
			
			return perList;		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public PersonVO getPerson(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertPerson(PersonVO person) {
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO PERSON (  ");
		sql.append(" 	     ID            ");
		sql.append(" 	    ,NAME          ");
		sql.append(" 	    ,GENDER        ");
		sql.append(" 	    ,AGE           ");
		sql.append(" 	    ,ADDRESS       ");
		sql.append(" 	) VALUES (         ");
		sql.append(" 	    ?            ");
		sql.append(" 	    ,?           ");
		sql.append(" 	    ,?           ");
		sql.append(" 	    ,?           ");
		sql.append(" 	    ,?           ");
		sql.append(" 	)                 ");
		return 0;
	}

	@Override
	public int updatePerson(PersonVO person) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePerson(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
