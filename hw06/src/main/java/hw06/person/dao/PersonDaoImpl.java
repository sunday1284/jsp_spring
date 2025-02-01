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
		StringBuffer sql = new StringBuffer();
		sql.append("	SELECT   ");
		sql.append("    ID,      ");
		sql.append("    NAME,    ");
		sql.append("    GENDER,  ");
		sql.append("    AGE,     ");
		sql.append("    ADDRESS  ");
		sql.append(" FROM         ");
		sql.append("    PERSON   ");
		sql.append(" WHERE   	 ");
		sql.append(" 	ID = ?   ");
		
		try(
			Connection conn = ConnectionPoolingFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			PersonVO person = null;
			
			if(rs.next()) {
				person = new PersonVO();
				person.setId(rs.getString("ID"));
				person.setName(rs.getString("NAME"));
				person.setGender(rs.getString("GENDER"));
				person.setAge(rs.getInt("AGE"));
				person.setAddress(rs.getString("ADDRESS"));
			}
			
			return person;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
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
		
		try(
			Connection conn = ConnectionPoolingFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			int idx = 1;
			
			pstmt.setString(idx++, person.getId());
			pstmt.setString(idx++, person.getName());
			pstmt.setString(idx++, person.getGender());
			pstmt.setInt(idx++, person.getAge());
			pstmt.setString(idx++, person.getAddress());
			
			return pstmt.executeUpdate();	
			
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int updatePerson(PersonVO person) {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE PERSON    ");
		sql.append(" SET              ");
		sql.append("     NAME = ?,     ");
		sql.append("     GENDER = ?,   ");
		sql.append("     AGE = ?,      ");
		sql.append("     ADDRESS = ?    ");
		sql.append(" WHERE            ");
		sql.append("     id = ?     ");
		try (
			Connection conn = ConnectionPoolingFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			int idx = 1;
			pstmt.setString(idx++, person.getName());
			pstmt.setString(idx++, person.getGender());
			pstmt.setInt(idx++, person.getAge());
			pstmt.setString(idx++, person.getAddress());
			pstmt.setString(idx++, person.getId());
			return pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		
	}

	@Override
	public int deletePerson(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE FROM    ");
		sql.append(" 	PERSON      ");
		sql.append(" WHERE          ");
		sql.append("     id = ?   ");
		
		try (
			Connection conn = ConnectionPoolingFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, id);
			
			return pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
}
