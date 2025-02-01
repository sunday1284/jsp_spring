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

@WebServlet("/personUpdate.do")
public class PersonUpdateServlet extends HttpServlet {
	//1. 서비스 셋팅
    private PersonService service = new PersonServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //2. vo에서 해당 값을 불러옴 -> beanutils도 가능
    	PersonVO person = new PersonVO();
        person.setId(req.getParameter("id"));
        person.setName(req.getParameter("name"));
        person.setGender(req.getParameter("gender"));
        person.setAge(Integer.parseInt(req.getParameter("age")));
        person.setAddress(req.getParameter("address"));
        //3. 서비스에서 값을 불러옴 
        service.ModifyPerson(person);
        //4. 해당 업데이트가 성공할 때만 personUpdate 실행 -> 200 상태 코드일 때 실행
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}