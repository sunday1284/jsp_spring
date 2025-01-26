package hw03;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;

import hw03.vo.FileInfoVO;
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

@WebServlet("/files/upload")
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 int status = fileDataProcess(req);
		 if(status == 200) {
			 String path = "/WEB-INF/views/09/uploadResult.jsp";
			 req.getRequestDispatcher(path).forward(req, resp);
		 }else {
			 resp.sendError(status,"요청 검증 실패~~");
		 }
		
	
	}
	//업로드 할 파일 
	private int fileDataProcess(HttpServletRequest req) throws IOException, ServletException {
		//vo 객체 생성 
		FileInfoVO fileInfo = new FileInfoVO();
		
		int status = 200;
		String uploard = req.getParameter("uploader");
		//vo에서 업로드 파라미터를 셋팅해줌
		fileInfo.setUploader(uploard);
		//실패 -> 400 성공 -> 200
		status = StringUtils.isBlank(uploard) ? 400 : 200;
		//업로드할 파일 셋팅
		Part uploadFile = req.getPart("uploadFile");
		
		if(uploadFile == null) {
			status = HttpServletResponse.SC_BAD_REQUEST;
		}else if(StringUtils.isBlank(uploadFile.getSubmittedFileName())){
			status = HttpServletResponse.SC_BAD_REQUEST;	
		} else {
			//원본 파일명 가져옴 getSubmittedFileName()-> 파일명 원본 그대로 가져올 때 씀
			fileInfo.setOriginalFilename(uploadFile.getSubmittedFileName());
			//클라이언트에서 파일명이 안겹치게 셋팅 
			String savename = "uploadFile"+System.currentTimeMillis();
			fileInfo.setSavename(savename);
			Path filepath = folderPath.resolve(savename);
			fileInfo.setFilePath(filepath);
			
			//try with closable -> **
			try(InputStream in = uploadFile.getInputStream()){
				Files.copy(in, filepath);
			}
		}
		//vo에 셋팅한 값을 attribute에 넘겨줌
		req.setAttribute("fileInfo", fileInfo);
		
		//세션에 업로드된 값을 저장하여 데이터 손실 x
		HttpSession session = req.getSession();
		session.setAttribute("uploadedFile", fileInfo);
		return status;
		
	}
}
