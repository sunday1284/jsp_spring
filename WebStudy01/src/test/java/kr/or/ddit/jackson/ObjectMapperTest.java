package kr.or.ddit.jackson;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class ObjectMapperTest {

	@Test
	void testJacksonUnMarshalling() throws JsonMappingException, JsonProcessingException {
		String json = "{\"prop2\":true,\"prop1\":34,\"prop4\":[23,12],\"prop3\":\"DMY\"}";
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> nativeTarget = mapper.readValue(json, HashMap.class);
		System.out.println(nativeTarget);
	}
	
	@Test
	void testJacksonMarshalling() throws IOException {
//		JSON : 이기종 시스템간의 데이터를 교환하기 위해 사용되는 경량 데이터 표현 방식.
//		Java -> JSON (Marshalling) : native 데이터를 공통 데이터 표현 방식으로 변환하는 작업.
//		JSON -> Java (UnMarshalling) : 공통 데이터 표현 방식으로 표현된 데이터를 native 데이터로 변환하는 작업.
		Map<String, Object> nativeTarget = new HashMap<>();
		nativeTarget.put("prop1", 34);
		nativeTarget.put("prop2", true);
		nativeTarget.put("prop3", "DMY");
		nativeTarget.put("prop4", new int[] {23, 12});
		ObjectMapper mapper = new ObjectMapper();
		// Marshalling
//		String json = mapper.writeValueAsString(nativeTarget);
		// Serialization
//		System.out.println(json);
		mapper.writeValue(System.out, nativeTarget);
	}
	
	@Disabled
	@Test
	void test1() {
		System.out.println("test case1 실행");
	}
	
	@Test
	void test2() {
		System.out.println("test case2 실행");
	}

}











