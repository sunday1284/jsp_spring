package kr.or.ddit.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;

import oracle.jdbc.pool.OracleDataSource;

/**
 * JDBC(JavaDataBaseConnectivity) 단계
 * 
 * 1. jdbc 드라이버를 벤더에서 찾고, 빌드패스에 추가 
 * 	  ex) oracle.com, maven, dependency
 * 2. 드라이버 로딩 
 * 3. Connection 생성 
 * 		DriverManager.getConnection
 * 		DataSource.getConnection
 * 4. SQL을 컴파일하고, 실행해주는 쿼리 객체 생성 
 * 		1) Statement : 실행 시점에 쿼리 컴파일
 * 		2) PreparedStatement(선컴파일된 쿼리 객체) : 쿼리 객체 생성시점에 미리 쿼리가 컴파일되었음.
 * 		프로시저 -> return 값 x function -> return 값 O
 * 		3) CallableStatement : 프로시저와 같은 절차적인 쿼리 집합을 실행하기 위한 쿼리 객체.
 * 5. 쿼리 실행 
 * 		1) SELECT : ResultSet executeQuery
 * 				ResultSet : 포인터를 가진 커서 형태의 질의 결과 집합 
 * 		2) INSERT, UPDATE, DELETE : int executeUpdate
 * 				int : 질의 결과가 반영된 레코드수 
 * 6. ResultSet 핸들링 
 * 7. 사용한 자원의 release
 *  
 */
class OracleDataBaseConnectionTest {
	@Test
	void testConnection() throws SQLException {
		String user = "choi";
		String password = "java";
		String url = "jdbc:oracle:thin:@localhost:1588/FREEPDB1";
		
		OracleDataSource ds = new OracleDataSource();
		ds.setURL(url);
		ds.setUser(user);
		ds.setPassword(password);
		
		try(
			Connection conn = ds.getConnection();
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
				System.out.printf("name : %s, value : %s \n desc : %s \n"
									,propertyName,propertyValue,description);
			}
		}	
	}
}
