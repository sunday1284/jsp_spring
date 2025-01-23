package kr.or.ddit.props.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.props.vo.PropertyVO;
import oracle.jdbc.pool.OracleDataSource;

public class PropertyDAOImpl implements PropertyDAO {

	/**
	 * 전체 리스트를 보여준다.
	 * 
	 */
	@Override
	public List<PropertyVO> selectPropertyList() {
			//1. List를 PropertyVO로 초기화 시킨다.
			List<PropertyVO> propList = new ArrayList<>();	
			//2. try with resource 구문을 활용해 db 연동을 위한 작업을 수행함 () ->에서
			try(
				Connection conn = ConnectionFactory.getConnection();
				Statement stmt = conn.createStatement();
			){
				System.out.println(conn);
				//3. 비어있는 쿼리객체 생성 -> 관련 sql문을 생성한다. 
				StringBuffer sql = new StringBuffer();
				sql.append(" select PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION " );
				sql.append(" from database_properties                          " );
				//4. 보여주는 값이므로 ResultSet으로 sql문을 저장한다.
				ResultSet rs = stmt.executeQuery(sql.toString());
				//5. 전체 리스트 -> while rs.next() 항상 참 이므로 rs.getString(Coulmm 값 셋팅)함
				while(rs.next()) {
					String propertyName = rs.getString("PROPERTY_NAME");
					String propertyValue = rs.getString("PROPERTY_VALUE");
					String description = rs.getString("DESCRIPTION");
					PropertyVO vo = new PropertyVO(propertyName, propertyValue, description);
					propList.add(vo);
					System.out.printf("name : %s, value : %s \n  desc : %s \n "
										,propertyName, propertyValue, description);
				} //while end
				//6. 리턴값 셋팅
				return propList;
			}catch (SQLException e) {
				//7.예외 전환 처리 -> 자동으로 sql문이 닫히게 해준다.
				throw new RuntimeException(e);				
			}
		
	}

	
	// MOCK 객체를 활용한 테스트 
	@Override
	public PropertyVO selectProperty(String propertyName) {
		//1. db연결을 위한 작업
		try(
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
		){
			//2. sql문 생성을 위한 StringBuffer값 초기화 -> 관련 sql문에 넘겨준 값을 셋팅한다.
			StringBuffer sql = new StringBuffer();
			sql.append(" select PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION " );
			sql.append(" from database_properties                          " );
			sql.append(" WHERE PROPERTY_NAME = '"+propertyName+"'          " );
			//3. ResultSet -> sql 문을 셋팅한다.
			ResultSet rs = stmt.executeQuery(sql.toString());
			//4. VO에 prop 값을 초기화한다.
			PropertyVO prop = null;
			//5. rs.next()를 if문으로 쓰는 이유 -> 해당 값만 받아서 출력하기 때문에 
			if(rs.next()) {
				prop = new PropertyVO();
				prop.setPropertyName(rs.getString("PROPERTY_NAME"));
				prop.setPropertyValue(rs.getString("PROPERTY_VALUE"));
				prop.setDescription(rs.getString("DESCRIPTION"));
			}
			//6. 리턴값 지정
			return prop;
		}catch (SQLException e) {
			
			//예외 전환 처리 -> 자동 db close
			throw new RuntimeException(e);				
		}
		
	}
}
