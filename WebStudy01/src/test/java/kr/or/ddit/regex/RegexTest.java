package kr.or.ddit.regex;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

class RegexTest {
	
	@Test
	void test() {
		Map<String, Object> requestScope = new HashMap<>();
		requestScope.put("now", LocalDateTime.now());
		
		String regex = "#\\[(\\w+)\\]";
		Pattern pattern = Pattern.compile(regex);
		String checkTarget = "<h4>TITLE</h4>\n<h4>현재 서버의 시간 : #[now]</h4>\n</body>";
		StringBuffer content = new StringBuffer();
		Matcher matcher = pattern.matcher(checkTarget);
		while(matcher.find()) {
			String name = matcher.group(1);
			System.out.println(name);
			Object data = requestScope.get(name);
			matcher.appendReplacement(content, data.toString());
		}
		
		matcher.appendTail(content);
		
		System.out.println(content);
	}

}
