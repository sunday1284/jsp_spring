package kr.or.ddit.multipart;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Multipart request 에 대한 필터링 처리(원본 request 를 wrapper로 전환)
 */
@Slf4j
public class MultipartFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String contentType = req.getContentType();
		//contentType (multipart/) 이미지 일 떄 
		if(contentType!=null && contentType.startsWith("multipart/")) {
			
			HttpServletRequest filtered = new MultipartHttpServletRequest(req);
			log.info("멀티 파트 요청이 필터링됨, request to {}", filtered.getClass().getSimpleName());
			chain.doFilter(filtered, response);
		}else {
			chain.doFilter(request, response);
		}
	}

}
