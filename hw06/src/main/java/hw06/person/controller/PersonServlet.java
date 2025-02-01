package hw06.person.controller;



import com.google.gson.Gson;
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

@WebServlet("/person.do")
public class PersonServlet extends HttpServlet {
	//1. 서비스 셋팅
    private PersonService service = new PersonServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//2. 파라미터 값 받아옴
    	String id = req.getParameter("who");
    	//3. 필수 파라미터 검증
        if (StringUtils.isBlank(id) || id.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
            return;
        }
        //4. 서비스 값에 해당 파라미터 값을 넘겨줌
        PersonVO person = service.ReadgetPerson(id);
        //5. 서비스에서 받은 데이터값을 json에 넘겨줌
        String json = new Gson().toJson(person);
        //6. json 파싱 준비 
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        // 7. json 형식으로 데이터를 읽기 위해 셋팅
        resp.getWriter().write(json);
    }
}