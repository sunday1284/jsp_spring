package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.paging.DefaultPaginationRenderer;
import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.paging.PaginationRenderer;
import kr.or.ddit.paging.SimpleCondition;
import kr.or.ddit.prod.dao.LprodMapper;
import kr.or.ddit.prod.dao.LprodMapperImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.vo.BuyerVO;
import kr.or.ddit.prod.vo.LprodVO;
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
	private LprodMapper lprodDao = new LprodMapperImpl();
	
	public void addAttribute(HttpServletRequest req) {
		List<LprodVO> lprodList = lprodDao.selectLprodList();
		List<BuyerVO> buyerList = new ArrayList<>();
		
		for(LprodVO lp : lprodList) {
		  List<BuyerVO> innerList =	lp.getBuyerList();
		  if(innerList.isEmpty()) continue;
		  else buyerList.addAll(innerList);
		}
		
		
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//위에서 받은 리스트들을 받아옴
		addAttribute(req);
		
		req.setCharacterEncoding("utf-8");
		//페이징 기법 
		String pageParam = req.getParameter("page");
		
		//상세 검색용 객체 생성
		ProdVO condition = new ProdVO();
		try {
			BeanUtils.populate(condition, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		req.setAttribute("condition", condition);
		
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		
		PaginationInfo<ProdVO> paging = new PaginationInfo<>(5, 3);
		paging.setCurrentPage(currentPage);
		paging.setDetailCondition(condition);
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
