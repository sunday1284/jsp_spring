package kr.or.ddit.servlet08;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.servlet08.vo.FileInfoVO;

@WebServlet("/09/file-download.do")
public class FileDownloadServlet extends HttpServlet {
	private ServletContext application;
	private Path folderPath;
		
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application =  getServletContext();
		folderPath = Paths.get(application.getInitParameter("mediasFolder"));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//세션 -> statusFull
		HttpSession session = req.getSession();
		FileInfoVO fileInfoVO = (FileInfoVO)session.getAttribute("uploadedFile");
		
//		1. target 파라미터 확보, name 파라미터 확보 
		String savename = fileInfoVO.getSavename(); // 세션에서 이름값 가져옴
		String filename = fileInfoVO.getOriginalFilename();
//		2. 검증(필수 파라미터)
		if(StringUtils.isBlank(savename) || StringUtils.isBlank(filename)) {
//		3. 검증 실패 : 400 코드 전송 후 종료
			resp.sendError(400, "필수 파라미터 누락");
			return;
		}
		
		Path filePath = folderPath.resolve(savename);
//		short circuit 연산자 : &&, true ||
		
		if(!Files.exists(filePath) || Files.isDirectory(filePath)) {
			resp.sendError(400);
			return;
		}
//		4. 검증 성공 
//		---> attatchment;filename=name파라미터로 결정 
		//url 인코딩 작업
		String encodedName = URLEncoder.encode(filename, "utf-8")
								.replaceAll("\\+", " ");
		String pattern = "attatchment;filename=\"%s\"";
		String dispostionValue = String.format(pattern, encodedName);
		resp.setHeader("content-disposition", dispostionValue);	
		resp.setContentLengthLong(Files.size(filePath));
		resp.setContentType("application/octet-stream");
//		5. floderpath 를 대상으로 target에 해당하는 절대 경로 생성 	
// 		6. response로 부터 출력 스트림 확보
		try(
			OutputStream os = resp.getOutputStream();
		){
// 		7. stream copy (Files.copy...)
			Files.copy(filePath, os);
		}
				
		
	}
}
