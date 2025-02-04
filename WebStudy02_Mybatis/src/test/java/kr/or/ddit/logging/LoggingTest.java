package kr.or.ddit.logging;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j //-> logging 어노테이션 추가하면 선언x
class LoggingTest {
//	final Logger log = LoggerFactory.getLogger(LoggingTest.class);
	
	@Test
	void test() {
		System.out.println("출력 테스트");
		log.debug("debug 등급으로 기록한 로그");
		int number = 34;
		log.info(" number 의 값 : {}",number);
	}

}
