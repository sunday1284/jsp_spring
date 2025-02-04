package kr.or.ddit.person.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.person.dao.PersonMapper;
import kr.or.ddit.person.dao.PersonMapperImpl;
import kr.or.ddit.person.vo.PersonVO;


/**
 *	/person GET
 *	/person/a001 GET
 *	/person/a001 DELETE
 *	/person POST
 */
@WebServlet("/person")
public class PersonRestControllerServlet extends HttpServlet {
	private PersonMapper dao = new PersonMapperImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<PersonVO> personList = dao.selectPersonList();
		req.setAttribute("personList", personList);
		String path = "/jsonView";
		req.getRequestDispatcher(path).forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		PersonVO person = new PersonVO();
		try {
			BeanUtils.populate(person, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		dao.insertPerson(person);
//		Post->Redirect->Get -> 갱신된 자원을 가져옴
		resp.sendRedirect(req.getContextPath()+"/person");
	}
}
