package kr.or.ddit.servlet09;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/files/download")
public class ImageFileDownloadControllerServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
//		1. target 파라미터 확보 
		String filename = req.getParameter("target");
//		2. 필수 파라미터로 검증, 검증 통과하지 못한 경우, 400 에러 전송 
		if(StringUtils.isBlank(filename)) {
			resp.sendError(400);
			return;
		}
//		3. 검증 통과시, application scope에 있는 folderPath 를 확보하고, Path 타입 캐스팅.
		Path folderPath = (Path)getServletContext().getAttribute("folderPath");
//		4. folderPath 와 파일명에 해당하는 target 파라미터의 값으로 filePath 생성 
		Path filePath = folderPath.resolve(filename);
//		5. filePath에 해당하는 파일이 없거나, 실제 파일이 맞는지 검증, 검증 통과하지 못한 경우, 400에러 전송.
		if(!Files.exists(filePath) || Files.isDirectory(filePath)) {
			resp.sendError(400);
			return;
		}
//		6. 검증 통과시, Content-Length 헤더를 파일의 크기로 결정.
		resp.setContentLengthLong(Files.size(filePath));
//		7. Content-Type 헤더를 바이트 스트림으로 결정.
		resp.setContentType("application/octet-stream");
//		8. Content-Disposition 헤더를 attatchment 타입으로 결정. filename 지시자로 저장 파일명 결정. 
		String encodedFilename = URLEncoder.encode(filename, "utf-8")
				.replaceAll("\\+", " ");
		String pattern = "attatchment;filename=\"%s\"";
		String dispositionValue = String.format(pattern, encodedFilename);
		resp.setHeader("Content-Disposition",dispositionValue);
//		9. 다운로드 처리를 위한 스트림 복사.
		try(
			OutputStream os = resp.getOutputStream();	
		){
			Files.copy(filePath, os);
		}
	}
}
