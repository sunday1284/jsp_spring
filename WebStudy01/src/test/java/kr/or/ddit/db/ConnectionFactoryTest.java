package kr.or.ddit.db;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ConnectionFactoryTest {

	@Test // 27.716s
	void testGetConnection1000() {
		assertDoesNotThrow(()->{
			for(int i=1; i<=1000; i++) {			
				Connection conn =  ConnectionFactory.getConnection();
				assertNotNull(conn);
				conn.close();
			}
		});
	}
	@Disabled
	@Test // 3.3s
	void testGetConnection100() {
		assertDoesNotThrow(()->{
			for(int i=1; i<=100; i++) {			
				Connection conn =  ConnectionFactory.getConnection();
				assertNotNull(conn);
				conn.close();
			}
		});
	}
	
	@Disabled
	@Test // 0.73
	void testGetConnection10() {
		assertDoesNotThrow(()->{
			for(int i=1; i<=10; i++) {			
				Connection conn =  ConnectionFactory.getConnection();
				assertNotNull(conn);
				conn.close();
			}
		});
	}
	@Disabled
	@Test //0.443s
	void testGetConnection1() {
		assertDoesNotThrow(()->{
			Connection conn =  ConnectionFactory.getConnection();
			assertNotNull(conn);
			conn.close();
		});
	}

}
