package kr.or.ddit.encoding;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * encoding(부호화) : 특정 매체에서 인식할 수 있는 방식으로 데이터의 표현 방법을 바꾸는 작업.
 * 	ex) URL encoding(네트워크),
 * 		Base64 encoding(문자로 데이터를 표현해야 하는 모든 시스템, html 페이지, 문자 기반의 이메일등...) 
 * encrypting(암호화) : 권한이 없는 자에 의해 데이터가 노출되는 상황을 막기 위해 데이터의 표현을 바꾸는 작업(Https).
 * 
 */
class EncodingDescTest {

	@Test
	void testBase64Encoding() {
		String plain = "한글을 비롯한 특수문자를 가진 평문";
		byte[] input = plain.getBytes();
		String encoded = Base64.getEncoder().encodeToString(input);
		System.out.println(encoded);
		byte[] decoded = Base64.getDecoder().decode(encoded);
		String result = new String(decoded);
		System.out.println(result);
	}
	
	@Test
	@Disabled
	void testURLEncoding() throws UnsupportedEncodingException {
		String plain = "한글을 비롯한 특수문자를 가진 평문";
		String encoded = URLEncoder.encode(plain, "UTF-8"); // --> form enctype 속성에 의해...
		System.out.println(encoded);
		String decoded = URLDecoder.decode(encoded,"UTF-8"); // --> request.setCharacterEncoding("UTF-8")
		System.out.println(decoded);
	}

}
