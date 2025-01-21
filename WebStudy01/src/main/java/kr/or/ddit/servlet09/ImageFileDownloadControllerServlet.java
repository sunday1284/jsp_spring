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
import kr.or.ddit.servlet08.vo.FileInfoVO;

@WebServlet("/files/download")
public class ImageFileDownloadControllerServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//1.target 파라미터 확보 
		String filenamePar = req.getParameter("target");
		//2.필수 파라미터 검증
		//3. 누락 -> 400 
		//4. 정상 파라미터 -> 
		if(StringUtils.isBlank(filenamePar)) {
			resp.sendError(400, "필수 파라미터 누락");
			return;
		}
		//5. application에 있는 folderPath를 만듦
		Path folderPath = (Path)getServletContext().getAttribute("folderPath");
		//6. filePath 만듦
		Path filePath = folderPath.resolve(filenamePar); 
		//7. 파일 검증 -> 400 or 404
		//8. 다운받을 이름이 폴더면 400에러 
		if(Files.isDirectory(filePath)) {
			resp.sendError(400, "파일이 아닌 폴더를 선택하였습니다..");
			return;
		}
		//9. disposition; attachment filename 지정
		String encodedname = URLEncoder.encode(filenamePar, "utf-8").replaceAll("\\+", " ");
		String pattern = "attatchment;filename=\"%s\"";
		String dispoitionValue = String.format(pattern, encodedname);
		resp.setHeader("content-disposition", dispoitionValue);
		//10 contentLengthLong -> 8진수 octet지정
		resp.setContentLengthLong(Files.size(filePath));
		//11. outputStream 확보 try with resource 구문 
		resp.setContentType("application/octet-stream");
		try (
			OutputStream os = resp.getOutputStream(); 
		) {
			//12. Files.copy를 씀 
			Files.copy(filePath, os);
		}
		
	}
}
