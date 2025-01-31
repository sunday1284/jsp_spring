package hw06.person.controller;

import java.io.IOException;
import java.util.List;

import hw06.person.service.PersonService;
import hw06.person.service.PersonServiceImpl;
import hw06.person.vo.PersonVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/personList.do")
public class PersonListServlet extends HttpServlet{
	//1. 서비스 객체 생성
	private PersonService service = new PersonServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//2. 인코딩 utf-8 설정
		req.setCharacterEncoding("utf-8");
		
		//3. 파라미터 생성 -> 리스트는 생성 x 
		
		//4. 필수 파라미터 검증 -> 리스트 x 
		
		//5. 서비스에서 값 가져옴
		List<PersonVO> perList = service.ReadpersonList();
		
		//6. scope값 생성 
		req.setAttribute("perList", perList);
		
		//7. 마일즈 활용 
		String logicalName = "person/PersonList";
		
		if(logicalName.startsWith("redirect:")) {
			String location = logicalName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		}else {
			String path = "/" + logicalName + ".miles";
			req.getRequestDispatcher(path).forward(req, resp);
		}
	}
}
