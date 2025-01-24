package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


import oracle.jdbc.pool.OracleDataSource;

public class ConnectionFactory {
	private static String driverClassName;
	private static String url;
	private static String user;
	private static String password;
	
	// static code block : 클래스가 메모리에 로딩될때 한번 실행되는 코드 블럭.
	static {
		// properties 파일 읽기 
		Properties dbInfo = new Properties();
		try(
				InputStream is = 
					ConnectionFactory.class.getResourceAsStream("/kr/or/ddit/db/DBInfo.properties");	
		){
			dbInfo.load(is);
			
			driverClassName = dbInfo.getProperty("driverClassName");
			url = dbInfo.getProperty("url");
			user = dbInfo.getProperty("user");
			password = dbInfo.getProperty("password");
			Class.forName(driverClassName); // 드라이버 로딩.
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		
	}
	public static Connection getConnection() {
		try {		
			return DriverManager.getConnection(url, user, password);
			
		} catch(SQLException e) {
				throw new RuntimeException(e);
		}
		
	}
	//신버전 -> 다른 db로 바꾸기 번거로움 -> 사용 x
	public static Connection getConnectionBak(){
		try {		
			OracleDataSource ds = new OracleDataSource();
			ds.setURL(url);
			ds.setUser(user);
			ds.setPassword(password);
			
			return ds.getConnection();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
