package kr.or.ddit.db;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ConnectionPoolingFactoryTest {

	@Test // 27.716s vs 1.051
	void testGetConnection1000() {
		assertDoesNotThrow(()->{
			for(int i=1; i<=1000; i++) {			
				Connection conn = ConnectionPoolingFactory.getConnection();
				assertNotNull(conn);
				conn.close();
			}
		});
	}
	@Disabled
	@Test // 3.3s vs 0.672
	void testGetConnection100() {
		assertDoesNotThrow(()->{
			for(int i=1; i<=100; i++) {			
				Connection conn =  ConnectionPoolingFactory.getConnection();
				assertNotNull(conn);
				conn.close();
			}
		});
	}
	
	@Disabled
	@Test // 0.73 vs 0.663
	void testGetConnection10() {
		assertDoesNotThrow(()->{
			for(int i=1; i<=10; i++) {			
				Connection conn =  ConnectionPoolingFactory.getConnection();
				assertNotNull(conn);
				conn.close();
			}
		});
	}
	@Disabled
	@Test //0.443s vs 0.624
	void testGetConnection1() {
		assertDoesNotThrow(()->{
			Connection conn =  ConnectionPoolingFactory.getConnection();
			assertNotNull(conn);
			conn.close();
		});
	}

}
