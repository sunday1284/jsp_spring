package kr.or.ddit.db;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

class ConnectionFactoryTest {

	@Test
	void testGetConnection() {
		assertDoesNotThrow(()->{
			Connection conn =  ConnectionFactory.getConnection();
			assertNotNull(conn);
			conn.close();
		});
	}

}
