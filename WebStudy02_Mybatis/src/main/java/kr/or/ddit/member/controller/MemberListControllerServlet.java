package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.paging.DefaultPaginationRenderer;
import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.paging.PaginationRenderer;
import kr.or.ddit.paging.SimpleCondition;
import kr.or.ddit.prod.vo.ProdVO;

@WebServlet("/member/memberList.do")
public class MemberListControllerServlet extends HttpServlet{
	
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		//1.페이징과 검색 처리를 위한 파라미터를 받음
		String pageParam = req.getParameter("page");
		String searchType = req.getParameter("searchType");
		String searchWord = req.getParameter("searchWord");
		//
		SimpleCondition condition = new SimpleCondition(searchType, searchWord);
		req.setAttribute("condition", condition);
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		PaginationInfo<MemberVO> paging = new PaginationInfo<>(3, 3); //스크린,블럭 사이즈
		//startRow, endRow 결정을 위한 셋팅 
		paging.setCurrentPage(currentPage);
		paging.setSimpleCondition(condition);
		
		
		
		
		List<MemberVO> memberList = service.readMemberList(paging);
		//scope 저장
		req.setAttribute("memberList", memberList);
		
		//페이지 랜더링 작업 
		PaginationRenderer render = new DefaultPaginationRenderer();
		String pagingHtml = render.renderPagination(paging);
		req.setAttribute("pagingHTML", pagingHtml);
		
		
//		Miles
		String logicalName = "member/memberList";
		
		if(logicalName.startsWith("redirect:")) {
			String location = logicalName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		}else {
			String path = "/" + logicalName + ".tiles";
			req.getRequestDispatcher(path).forward(req, resp);
		}
		
	}
}
