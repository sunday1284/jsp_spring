package kr.or.ddit.person.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.person.dao.PersonMapper;
import kr.or.ddit.person.dao.PersonMapperImpl;
import kr.or.ddit.person.vo.PersonVO;


/**
 *	/person GET
 *	/person/a001 GET
 *	/person/a001 DELETE
 *	/person POST
 */
@WebServlet({"/person", "/person/*"})
public class PersonRestControllerServlet extends HttpServlet {
	private PersonMapper dao = new PersonMapperImpl();
	
	private void getPersonList(HttpServletRequest req) {
		List<PersonVO> personList = dao.selectPersonList();
		req.setAttribute("personList", personList);
	}
	
	private void getPerson(String personId, HttpServletRequest req) {
		PersonVO person = dao.selectPerson(personId);
		req.setAttribute("person", person);
	}
	private String getPathVariable(HttpServletRequest req) {
		String uri = req.getRequestURI();
		String regex = req.getContextPath()+"/person/(\\w+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(uri);
		if(matcher.find()) {
			//personId 반환
			return matcher.group(1);
		}else {
			return null;
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		ex) /ws02/person, /ws02/person/a001
		
		String personId = getPathVariable(req);
		if(personId!=null) {
			getPerson(personId, req);
		}else {
			getPersonList(req);
		}
		
		String path = "/jsonView";
		req.getRequestDispatcher(path).forward(req, resp);
		
	}
	
	
	private PersonVO unMarshallFromBody(HttpServletRequest req) throws IOException{
		ObjectMapper ob = new ObjectMapper();
		try(
			InputStream is = req.getInputStream();
				
		){
			return ob.readValue(is, PersonVO.class);	
		} 
	}
	
	private PersonVO personVOFromParameterMap(HttpServletRequest req) {
		PersonVO person = new PersonVO();
		try {
			BeanUtils.populate(person, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		return person;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
//		PersonVO person = personVOFromParameterMap(req); //파라미터 수신 
		PersonVO person = unMarshallFromBody(req); //json payload 수신
		
		dao.insertPerson(person);
//		Post->Redirect->Get -> 갱신된 자원을 가져옴
		resp.sendRedirect(req.getContextPath()+"/person");
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String personId = getPathVariable(req);
		//PRG패턴 -> Put Delete는 안됨..
//		Post Redirect Get 
//		Put Redirect Put 
//		Delete Redirect Delete
		
		dao.deletePerson(personId);
		
		req.setAttribute("success", true);
		String path = "/jsonView";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
