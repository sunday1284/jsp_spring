package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.multipart.MultipartHttpServletRequest;
import kr.or.ddit.prod.dao.LprodMapper;
import kr.or.ddit.prod.dao.LprodMapperImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.vo.BuyerVO;
import kr.or.ddit.prod.vo.LprodVO;
import kr.or.ddit.prod.vo.ProdVO;

@WebServlet("/prod/prodInsert.do")
@MultipartConfig
public class ProdInsertControllerServlet extends HttpServlet{
	private String prodImagesUrl = "/resources/prodImages";
	
	
	//서비스 생성
//	@Inject
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
		addAttribute(req);
		String logicalName = "prod/prodForm";
		
		if (logicalName.startsWith("redirect:")) {
			String location = logicalName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		} else {
			String path = "/" + logicalName + ".tiles";
			req.getRequestDispatcher(path).forward(req, resp);
		}
	
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//utf-8 셋팅 -> 이미 init으로 불러옴
		//beanutils로 데이터를 불러옴
		ProdVO prod = new ProdVO();
		try {
			BeanUtils.populate(prod, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		//객체 검증 코드 필요!!
		
		
		//서비스에서 데이터를 받아옴
		
		processProdImage(req, prod); 
		
		service.createProd(prod);
		
		
		String logicalName = "redirect:/prod/prodDetail.do?what="+prod.getProdId();
		
		if (logicalName.startsWith("redirect:")) {
			String location = logicalName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		} else {
			String path = "/" + logicalName + ".tiles";
			req.getRequestDispatcher(path).forward(req, resp);
		}
		
	
	}

	private void processProdImage(HttpServletRequest req, ProdVO prod) throws IOException, ServletException {
		//타입 체킹 ->instanceof
		if(req instanceof MultipartHttpServletRequest) {
			MultipartFile prodImage = ((MultipartHttpServletRequest) req).getFile("prodImage");
			if(prodImage==null || prodImage.isEmpty()) {
				return; 
			}
			//2진 데이터 저장
			//파일이 저장될때
			String prodImg = UUID.randomUUID().toString();
			String folderPath = req.getServletContext().getRealPath(prodImagesUrl);
			Path filePath = Paths.get(folderPath, prodImg);
			File destFile = new File(folderPath, prodImg);
			prodImage.transferTo(destFile);
			prod.setProdImg(prodImg);
		}
		
	}
}
