package kr.or.ddit.servlet07;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.servlet07.vo.AlbaVO;

@WebServlet("/08/formProcess.do")
public class FormProcessServlet extends HttpServlet{
	
	private Map<String, AlbaVO> albaTable = new HashMap<>();
	//asd
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get 방식에서는 적용x -> server셋팅 useBodyEncodingForURI="true"가 필요
		req.setCharacterEncoding("UTF-8");
		Collection<AlbaVO> albas = albaTable.values(); // model 확보 
		req.setAttribute("albas", albas);
		String path = "/WEB-INF/views/alba/albaList.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	/**
	 * body 컨텐츠의 종류 : form 의 enctype
	 * 1. request parameter : application/x-www-form-urlencoded
	 * 	String getParameter(name)
	 *  String[] getParameterValues(name)
	 *  Map<String, String[]> getParameterMap()
	 *  Enumeration<String> getParameterNames()
	 * 2. multipart : multipart/form-data
	 * form 이 없는 경우, ex) 비동기 요청 
	 * 3. request payload : application/json[xml]
	 * 
	 * body 의 컨텐츠나 line의 쿼리 스트링형태로 전달되는 특수문자 처리 
	 *  : 1바이트로 표현할 수 없는 모든 문자는 인코딩이 된 상태로 전송됨.
	 *  --> 수신측에서 디코딩을 해야함.
	 *  line 의 쿼리 스트링 : 서버가 디코딩에 대한 책임을 가짐.
	 *  	쿼리 스트링에 대한 파싱 시점을 파라미터 수신 시점으로 지연시킨 경우,
	 *  	제한적으로 req.setCharacterEncoding(charset) 설정을 사용할 수 있음.
	 *  body 의 컨텐츠 : 수신측에서 직접 디코딩을 해야함. req.setCharacterEncoding(charset)
	 *  
	 *  
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
//		System.out.println("======================================");
//		
//		printParameterNames(req);
//		
//		System.out.println("======================================");
//		
//		printParameterMapCase1(req);
//		
//		System.out.println("======================================");
//		
//		printParameterMapCase2(req);
//		
//		System.out.println("======================================");
		
		AlbaVO vo = parameterMapToVO(req);
		System.out.println(vo);
		albaTable.put(vo.getId(), vo);
		resp.sendRedirect(req.getContextPath() + "/08/formProcess.do");
	}
	
	private AlbaVO parameterMapToVO(HttpServletRequest req) {
		AlbaVO vo = new AlbaVO();
		//이 상황에서 필요한 라이브러리 -> beanUtils
//		vo.setId(req.getParameter("id"));
//		vo.setPassword(req.getParameter("password"));
//		vo.setName(req.getParameter("name"));
//		vo.setAge(Integer.parseInt(req.getParameter("age")));
//		vo.setGrade(req.getParameter("grade"));
		
		//리플렉션 작업
		Class<?> voType =  vo.getClass();
		
		Map<String, String[]> parameterMap = req.getParameterMap();
		
		try {
			BeanUtils.populate(vo, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// 예외 전환 구조 
			throw new RuntimeException(e);
		}
		
//		Set<String> names = parameterMap.keySet();
//		for(String name : names) {
////			vo[name] = req.getParameterValues(name);
//			try {
//				Field property = voType.getDeclaredField(name);
//				
//				System.out.printf("name : %s, type : %s \n", 
//						property.getName(), property.getType());
//			} catch (NoSuchFieldException | SecurityException e) {
//				e.printStackTrace();
//			}
//		}
		return vo;
		
	}
	private void printParameterMapCase2(HttpServletRequest req) {
		Map<String, String[]> parameterMap = req.getParameterMap();
		Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
		for(Entry<String, String[]> entry : entrySet) {
			String single = entry.getKey(); //파라미터명
			String[] values = entry.getValue(); 
			System.out.printf("파라미터명 : %s, 파라미터값 : %s\n", 
					single, Arrays.toString(values));
		}
		
	}
	private void printParameterMapCase1(HttpServletRequest req) {
		Map<String, String[]> parameterMap = req.getParameterMap();
		Set<String> names = parameterMap.keySet();
		for(String single : names) {
//			parameterMap.get(single);
			String[] values = req.getParameterValues(single);
			System.out.printf("파라미터명 : %s, 파라미터값 : %s\n", 
					single, Arrays.toString(values));
		}
	}
	private void printParameterNames(HttpServletRequest req) {
		Enumeration<String> names = req.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			System.out.println(name);
		}
	}
}
