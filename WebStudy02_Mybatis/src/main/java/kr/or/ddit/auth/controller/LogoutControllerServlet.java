package kr.or.ddit.auth.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login/Logout")
public class LogoutControllerServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// session scope의 authMember 제거 
		// 강제로 세션 만료 
		HttpSession session = req.getSession();
		if(session.isNew()) {
			resp.sendError(400);
			return;
		}
		session.invalidate();
		//웰컴페이지 이동(redirect)
		String location = req.getContextPath() + "/?state=logout";
		resp.sendRedirect(location);
	}
}
