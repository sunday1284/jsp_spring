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
	private PropertyService service = new PropertyServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		List<PropertyVO> propList =  service.readPropertyList();
		
		req.setAttribute("propList", propList);
		
		String path = "/WEB-INF/views/props/propList.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
