package hw05.mbti.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

import oracle.jdbc.pool.OracleDataSource;

public class ConnectionPoolingFactory {
	private static String driverClassName;
	private static String url;
	private static String user;
	private static String password;
	private static BasicDataSource ds;
	
	// static code block : 클래스가 메모리에 로딩될때 한번 실행되는 코드 블럭.
	static {
		// properties 파일 읽기 
		Properties dbInfo = new Properties();
		try(
				InputStream is = 
					ConnectionPoolingFactory.class.getResourceAsStream("/kr/or/ddit/db/DBInfo.properties");	
		){
			dbInfo.load(is);
			
			driverClassName = dbInfo.getProperty("driverClassName");
			url = dbInfo.getProperty("url");
			user = dbInfo.getProperty("user");
			password = dbInfo.getProperty("password");
			
			ds = new BasicDataSource();
			ds.setDriverClassName(driverClassName);
			ds.setUrl(url);
			ds.setUsername(user);
			ds.setPassword(password);
			//커넥션 여러개 생성 
			ds.setInitialSize(2);
			ds.setMaxWait(Duration.of(2, ChronoUnit.SECONDS));
			ds.setMaxTotal(3);
			
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	public static Connection getConnection() {
		try {		
			return ds.getConnection();
			
		} catch(SQLException e) {
				throw new RuntimeException(e);
		}
		
	}
	
}
