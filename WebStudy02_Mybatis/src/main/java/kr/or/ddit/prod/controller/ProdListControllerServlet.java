package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.paging.DefaultPaginationRenderer;
import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.paging.PaginationRenderer;
import kr.or.ddit.paging.SimpleCondition;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.vo.ProdVO;

/**
 * /prod/prodList.do GET 
 * /prod/prodDetail.do?what=P10100001 GET
 * /prod/prodInsert.do GET : form UI 제공 /prod/prodInsert.do POST : form data 처리
 * /prod/prodUpdate.do?what=P10100001 GET : form UI 제공
 * /prod/prodUpdate.do?what=P10100001 POST : form data 처리
 * 
 */
@WebServlet("/prod/prodList.do")
public class ProdListControllerServlet extends HttpServlet {
	// 1. 서비스 의존관계
	private ProdService service = new ProdServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//페이징 기법 
		String pageParam = req.getParameter("page");
		String searchType = req.getParameter("searchType");
		String searchWord = req.getParameter("searchWord");
		
		SimpleCondition condition = new SimpleCondition(searchType, searchWord);
		req.setAttribute("condition", condition);
		
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		
		PaginationInfo<ProdVO> paging = new PaginationInfo<>(5, 3);
		paging.setCurrentPage(currentPage);
		paging.setSimpleCondition(condition);
		//2. 리스트에 서비스 담아줌
		List<ProdVO> prodList = service.readProdList(paging);
		//3. 스코프 값 설정
		req.setAttribute("prodList", prodList);
		// 페이징 처리된 값을 jsp로 보내줌
		PaginationRenderer renderer = new DefaultPaginationRenderer();
		String pagingHTML = renderer.renderPagination(paging);
		req.setAttribute("pagingHTML", pagingHTML);
		//4.logicalName 설정 
		String logicalName = "prod/prodList";
		//5.타일즈로 이동 
		if (logicalName.startsWith("redirect:")) {
			String location = logicalName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		} else {
			String path = "/" + logicalName + ".tiles";
			req.getRequestDispatcher(path).forward(req, resp);
		}
	}
}
