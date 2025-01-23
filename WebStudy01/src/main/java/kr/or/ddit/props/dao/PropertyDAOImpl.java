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

	@Override
	public List<PropertyVO> selectPropertyList() {
			List<PropertyVO> propList = new ArrayList<>();			
			try(
				Connection conn = ConnectionFactory.getConnection();
				Statement stmt = conn.createStatement();
			){
				System.out.println(conn);
				//비어있는 쿼리객체
				StringBuffer sql = new StringBuffer();
				sql.append(" select PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION " );
				sql.append(" from database_properties                          " );
				ResultSet rs = stmt.executeQuery(sql.toString());
				while(rs.next()) {
					String propertyName = rs.getString("PROPERTY_NAME");
					String propertyValue = rs.getString("PROPERTY_VALUE");
					String description = rs.getString("DESCRIPTION");
					PropertyVO vo = new PropertyVO(propertyName, propertyValue, description);
					propList.add(vo);
					System.out.printf("name : %s, value : %s \n  desc : %s \n "
										,propertyName, propertyValue, description);
				} //while end
				return propList;
			}catch (SQLException e) {
				//예외 전환 처리 
				throw new RuntimeException(e);				
			}
		
	}

	
	// MOCK 객체를 활용한 테스트 
	@Override
	public PropertyVO selectProperty(String propertyName) {
		try(
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
		){
			StringBuffer sql = new StringBuffer();
			sql.append(" select PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION " );
			sql.append(" from database_properties                          " );
			sql.append(" WHERE PROPERTY_NAME = '"+propertyName+"'          " );
			
			ResultSet rs = stmt.executeQuery(sql.toString());
			
			PropertyVO prop = null;
			
			if(rs.next()) {
				prop = new PropertyVO();
				prop.setPropertyName(rs.getString("PROPERTY_NAME"));
				prop.setPropertyValue(rs.getString("PROPERTY_VALUE"));
				prop.setDescription(rs.getString("DESCRIPTION"));
			}
			return prop;
		}catch (SQLException e) {
			//예외 전환 처리 
			throw new RuntimeException(e);				
		}
		
	}
}
