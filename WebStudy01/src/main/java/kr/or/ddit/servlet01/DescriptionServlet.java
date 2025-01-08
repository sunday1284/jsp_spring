package kr.or.ddit.servlet01;

import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 서블릿 (what /why / how)
 *  : 자바의 백엔드 모듈 개발시에 요청에 대한 분석과 동적 응답을 생성할 수 있는 객체(가 가져야하는 조건에 대한 명세서-HttpServlet).
 *    컨테이너 의존적인 객체.
 *    서블릿 컨테이너 : 싱글턴 객체(서블릿)의 생명주기 관리자
 *  : 웹상의 불특정 다수의 클라이언트로 부터 발생하는 다양한 요청을 처리하고,
 *    다양한 형태의 동적 응답을 생성하기 위한 백엔드 모듈.
 *  서블릿 스펙을 이용하는 개발 단계
 *  1.  HttpServlet 의 상속 클래스 구현.
 *  2.  상속받은 다양한 콜백을 필요에 따라 재정의.
 *  	callback : 관련된 이벤트가 발생한 경우 시스템(servlet container) 내부적으로 
 *                  자동 호출되는 함수나 메소드의 형태
 *      생명주기 콜백 : 싱글턴 객체가 생성된 직후 한번 실행됨. (init, destroy)
 *      요청 콜백 : 요청이 발생할때마다 반복적으로 실행됨.
 *      		service : 요청 발생시 컨테이너에 의해 직접 실행.
 *      		doXXX : service 내부에서 http request method 에 따라 실행됨.
 *  3. 서블릿 컨테이너에 서블릿을 등록함.
 *  	1) @WebServlet
 *  	2) web.xml
 *  4. 서블릿 매핑 : request URI(resource path)
 *  	1) @WebServlet(resource path)
 *  5. 컨테이너 재구동
 *  
 *  ** 서블릿 스펙에서 활용 가능한 객체(scope 형태의 저장소를 가짐.), setAttribute로 저장, getAttribute 로 조회
 *  1. 하나의 서버와 하나의 컨텍스트를 대상으로 한 싱글턴 객체 : ServletContext, application scope 를 가지고 있음.
 *  2. 하나의 클라이언트를 대상으로 한 객체 : HttpSession, session scope 를 가지고 있음.
 *  3. 하나의 요청을 대상으로 한 객체 : HttpServletRequest, request scope 를 가지고 있음.
 *  
 */
public class DescriptionServlet extends HttpServlet{
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String paramValue = config.getInitParameter("param1");
		System.out.printf("%s 초기화 완료, 파라미터 : %s\n"
							, this.getClass().getName(), paramValue);
		System.out.printf("ServletContext instance : %d\n", getServletContext().hashCode());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.printf("service  실행 - part1, method : %s\n", req.getMethod());
		super.service(req, resp);
		System.out.printf("service  실행 - part2\n");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.printf("doGet 실행\n");
	}
}















