package kr.or.ddit.servlet09;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.nio.file.*;
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

@WebServlet("/files/upload")
@MultipartConfig
public class ImageFileUploadControllerServlet extends HttpServlet{
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("section4", "/WEB-INF/views/files/fileUpload.jsp");
		String path = "/WEB-INF/views/files/layout.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); // 모든 코드에서 시작
//		1. 업로드된 파일을 Part로 확보
		Part uploadFile = req.getPart("uploadFile");
//		2. empty part 체크 
		if(uploadFile==null || StringUtils.isBlank(uploadFile.getSubmittedFileName())) {
//		3. empty part ? 400 전송 
			resp.sendError(400,"필수 파일 누락");
			return;
		}
		if(!uploadFile.getContentType().startsWith("image/")) {
			resp.sendError(400, "이미지 이외 업로드 불가.");
			return;
		}
		
//		4. not empty part ? -> part데이터가 있을때
//		5. folderPath 확보 
		Path folderPath = (Path) getServletContext().getAttribute("folderPath");
//		6. savename 생성 
		String savename = UUID.randomUUID().toString();
//		7. filePath 생성 
		Path filePath = folderPath.resolve(savename);	
//		8. filePath 에 저장(stream copy)
		try(
			InputStream is = uploadFile.getInputStream();
		){
			Files.copy(is, filePath);
		}
		HttpSession session = req.getSession();
		session.setAttribute("uploaded", filePath);
//		9. PRG 패턴으로 이동 : /files
		String location = req.getContextPath()+"/files";
		resp.sendRedirect(location);
		
	}
}
