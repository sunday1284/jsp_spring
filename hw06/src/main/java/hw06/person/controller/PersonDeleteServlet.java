package hw06.person.controller;


import hw06.person.service.PersonService;
import hw06.person.service.PersonServiceImpl;
import hw06.person.vo.PersonVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/personDelete.do")
public class PersonDeleteServlet extends HttpServlet {
	//1. 서비스 셋팅
    private PersonService service = new PersonServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //2. 파라미터 값 설정
    	String id = req.getParameter("who");
    	//3. 필수 파라미터 검증
        if (StringUtils.isBlank(id)|| id.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
            return;
        }
        //4. 서비스 값 셋팅
        service.RemovePerson(id);
        //5. 해당 삭제가 성공할 때만 personDelete 실행 -> 200 상태 코드일 때 실행
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}