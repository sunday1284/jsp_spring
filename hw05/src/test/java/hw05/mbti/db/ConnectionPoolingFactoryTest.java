package hw05.mbti.db;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;


class ConnectionPoolingFactoryTest {

	@Test
	void testGetConnection() throws SQLException {
		Connection conn = ConnectionPoolingFactory.getConnection();
		assertNotNull(conn);
		conn.close();
	}

}
