package kr.or.ddit.servlet03;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.servlet03.service.DummyModelService;

@WebServlet("/03/dummy")
public class DummyModel2ControllerServlet extends HttpServlet{
	private ServletContext application;
	private DummyModelService service = new DummyModelService(); // 의존관계 형성

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
 		application = getServletContext();
 		application.setAttribute("applicationAttr", "서버 전체에서 공유할 데이터");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("sessionAttr", String.format("동일 사용자의 공유 데이터 %s", session.getId()));
		
		LocalDateTime information = service.readModel();
		req.setAttribute("food", information);
		String path = "/WEB-INF/views/dummy/dummyView.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
		dispatcher.forward(req, resp);
	}
}





















