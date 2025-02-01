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
import java.util.List;

@WebServlet("/personList.do")
public class PersonListServlet extends HttpServlet {
	//1. service를 불러옴
    private PersonService service = new PersonServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//2.인코딩 셋팅
    	req.setCharacterEncoding("utf-8");
    	//3. 해당 서비스에서 관련 값을 불러옴
    	List<PersonVO> personList = service.ReadpersonList();
    	//4. Gson라이브러리를 이용한 json으로 데이터 넘김
        String json = new Gson().toJson(personList);
        //5.json 형식으로 셋팅
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        //6.json에 있는 파싱 데이터를 보여줌
        resp.getWriter().write(json);
    }
}