package kr.or.ddit.servlet08;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import kr.or.ddit.servlet08.vo.FileInfoVO;


/**
 * multipart request 처리 방법(enctype="multipart/form-data" --> Content-Type 헤더)
 * 
 * 1. multipart config 설정 필요 
 * 	  case 1 : @MultipartConfig
 * 	  case 2 : web.xml 을 사용해서 등록한 경우.
 * 		servlet -> multipart-config
 * 2. 전송된 part들에 대한 처리 -> getParts
 * 	  1) 문자 기반 part : getParameter
 * 	  2) 파일 기반 part : getPart
 * 
 */
@WebServlet("/09/file-upload.do")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
	private ServletContext application;
	private Path folderPath;
		
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application =  getServletContext();
		folderPath = Paths.get(application.getInitParameter("mediasFolder"));
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//	case1
//		partsProcess(req);
		
		// case2
		int status = formDataProcess(req);
		if(status == 200) {
			//forward 작업
			String path = "/WEB-INF/views/09/uploadResult.jsp";
			req.getRequestDispatcher(path).forward(req, resp);	
		}else {
			resp.sendError(status, "요청 검증을 통과하지 못했음.");
		}
	}
	
	private int formDataProcess(HttpServletRequest req) throws ServletException, IOException{
		FileInfoVO fileInfo = new FileInfoVO();
		//상태코드
		int status = 200;
		String uploader = req.getParameter("uploader");
		fileInfo.setUploader(uploader);
		//비어있으면 400, 비어있지 않으면 성공
		status = StringUtils.isBlank(uploader) ? 400 : 200;
		
		Part uploadFile = req.getPart("uploadFile");
		
		if(uploadFile == null) {
			status = HttpServletResponse.SC_BAD_REQUEST;
		}else if(StringUtils.isBlank(uploadFile.getSubmittedFileName())) {
			status = HttpServletResponse.SC_BAD_REQUEST;
		} else {
			//원본 파일명
			fileInfo.setOriginalFilename(uploadFile.getSubmittedFileName());
			String savename = "uploadFile"+System.currentTimeMillis(); //시간 
			fileInfo.setSavename(savename);
			Path filePath = folderPath.resolve(savename);
			fileInfo.setFilePath(filePath);
			
			System.out.printf("DB에 저장할 데이터 : %s\n",fileInfo);
	//		InputStream is = null;
	//		try {
	//			is =  uploadFile.getInputStream();
	//			Files.copy(is, filePath);
	//		} finally {
	//			if(is != null)
	//				is.close();
	//		}
	//		try with Closable resource 문법 -> 자동 close 기능 finally -> x
			try(
					InputStream is = uploadFile.getInputStream();	
			){
				Files.copy(is, filePath);
			}// try end
		} // if~else end
		req.setAttribute("fileInfo", fileInfo);
		//세션 값 저장 
		HttpSession session =  req.getSession();
		session.setAttribute("uploadedFile", fileInfo);
		return status;
	}
	private void partsProcess(HttpServletRequest req) throws ServletException, IOException {
		FileInfoVO fileinfo = new FileInfoVO();
		
		Collection<Part> parts =  req.getParts();
		for(Part single : req.getParts()) {
			String name = single.getName();
			String contentType = single.getContentType();
			System.out.printf("part name : %s, mine : %s\n", name, contentType);
			if(contentType == null) {
				// 문자 기반 part
				String value = req.getParameter(name);
				System.out.printf("DB에 저장할 문자기반의 %s 에 해당하는 텍스트값 : %s\n", name, value);
				//vo 값 셋팅 
				fileinfo.setUploader(value);
			}else {
				// 파일 기반 part -> db에 넣을 데이터 두개 -> 파일 관련 작업시 필요
				String savename = UUID.randomUUID().toString();
				String originalFilename = single.getSubmittedFileName(); // 원본파일명
				//db관련 작업**
				Path filePath = folderPath.resolve(savename);//파일 경로 작업
				
				//파일 읽기
				InputStream is = single.getInputStream();
				//StandardCopyOption.REPLACE_EXISTING 파일이 있으면 새로 저장한다.
				Files.copy(is, filePath, StandardCopyOption.REPLACE_EXISTING);
				is.close();
				
				System.out.printf("DB에 저장할 원본명 : %s, 저장명 : %s, 저장 경로 : %s\n"
								,originalFilename, savename, filePath);
				fileinfo.setOriginalFilename(originalFilename);
				fileinfo.setSavename(savename);
				fileinfo.setFilePath(filePath);
			}
		}
		
		//전달할 모델명(scope 내의 attribute name) : fileInfo
		
		//attribute 값 셋팅
		req.setAttribute("fileInfo", fileinfo);
	}
}
