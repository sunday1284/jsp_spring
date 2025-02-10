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

@WebServlet("/prod/prodUpdate.do")
@MultipartConfig
public class ProdUpdateController extends HttpServlet {
	

	private ProdService service = new ProdServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//what 파라미터 수신
		String prodId = req.getParameter("what");
		
		//필수 파라미터 누락시 400 에러 응답 
		if (StringUtils.isBlank(prodId)) {
			resp.sendError(400);
			return;
		}
		
		//what에 해당하는 상품 정보를 조회
		ProdVO prod = service.readProd(prodId);
		
		//prod 속성명으로 reqeusqst scope에 저장
		req.setAttribute("prod", prod);
		
		String logicalName = "prod/prodEdit";
		//5.타일즈로 이동 
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
		//1.VO 객체(command object) 생성
		ProdVO prod = new ProdVO();
		
		//2. 파라미타 수신 ->BeanUtils.populate
		try {
			BeanUtils.populate(prod, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// RuntimeExeption으로 매핑하여 전환
			throw new RuntimeException(e);
		}
		
		//검증 생략, 이후 스프링에서 추가 예정
		
		//상품 이미지 처리 
		processProdImage(req, prod);
		//3. 서비스에서 수정작업 시작
		service.modifyProd(prod);
		
		//4.수정이 됐으면 리스트로 보내줌
		String logicalName = "redirect:/prod/prodDetail.do?what="+prod.getProdId();
		//5.타일즈로 이동 
		if (logicalName.startsWith("redirect:")) {
			String location = logicalName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		} else {
			String path = "/" + logicalName + ".tiles";
			req.getRequestDispatcher(path).forward(req, resp);
		}
		
		
		
	}
	
	//이미지 url 생성
	private String prodImagesUrl = "/resources/prodImages";
	
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
			prod.setProdImg(prodImg); //메타데이터 저장
		}
	}
	
}
