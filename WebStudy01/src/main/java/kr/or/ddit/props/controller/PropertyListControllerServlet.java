package kr.or.ddit.props.controller;

import java.io.DataOutput;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.props.dao.PropertyDAO;
import kr.or.ddit.props.dao.PropertyDAOImpl;
import kr.or.ddit.props.service.PropertyService;
import kr.or.ddit.props.service.PropertyServiceImpl;
import kr.or.ddit.props.vo.PropertyVO;

@WebServlet("/props/list")
public class PropertyListControllerServlet extends HttpServlet{
	//1. 서비스인터페이스에서 service를 ServiceImpl 생성자를 통해 받아옴 
	private PropertyService service = new PropertyServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//2. List를 propList로 받아오고 -> service에서 dao에서 db작업한 것을 리턴시킨 readPropertyList를 받아옴
		List<PropertyVO> propList =  service.readPropertyList();
		
		//3. setAttribute에서 스코프값을 넘겨주고, jsp에서 꺼내올 값을 셋팅
		req.setAttribute("propList", propList);
		
		//4. 해당 jsp 경로를 지정하고 forward로 보내버린다.
		String path = "/WEB-INF/views/props/propList.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
