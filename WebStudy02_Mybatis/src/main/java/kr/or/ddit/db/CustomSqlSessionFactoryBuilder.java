package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomSqlSessionFactoryBuilder {
	/**
	 * 어플리케이션에서 싱글턴으로 관리됨
	 */
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		//클래스 패스 자원 -> 맨앞 / 제거
		String configFile = "kr/or/ddit/mybatis/Configuration.xml";
		try(
			InputStream is = Resources.getResourceAsStream(configFile);
		) {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
