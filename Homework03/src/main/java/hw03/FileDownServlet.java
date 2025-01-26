package hw03;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;

import hw03.vo.FileInfoVO;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/files/download")
public class FileDownServlet extends HttpServlet{
	
	private ServletContext application;
	private Path folderPath;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		folderPath = Paths.get(application.getInitParameter("mediasFolder"));
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//파일업로드에서 세션에 저장한 세션값 가져오기 
		HttpSession session = req.getSession();
		FileInfoVO fileinfo =  (FileInfoVO) session.getAttribute("uploadedFile");
		//target 파라미터, name 파라미터 셋팅
		String savename = fileinfo.getSavename();
		String filename = fileinfo.getOriginalFilename();
		
		//실패 시 -> 예외 처리
		if(StringUtils.isBlank(savename) || StringUtils.isBlank(filename)) {
			resp.sendError(400, "필수 파라미터 값 없음");
			return;
		}
		
		Path filePath = folderPath.resolve(savename);
		//파일이 존재하지않으면 오류!!
		if(!Files.exists(filePath) || Files.isDirectory(filePath)) {
			resp.sendError(400);
			return;
		}
		//url 인코딩 -> 자바에서는 \ 윈도우 디렉토리 기호가 안통하기 때문에 하나를 더 써줘야함
		String encodedName = URLEncoder.encode(filename, "utf-8")
								.replaceAll("\\+", " ");
		String urlpattern = "attatchment;filename=\"%s\"";
		//벨류값에 인코딩한 이름 넣음
		String dispositionVal = String.format(urlpattern, encodedName);
		resp.setHeader("content-disposition", dispositionVal);
		resp.setContentLengthLong(Files.size(filePath));
		resp.setContentType("application/octet-stream");
		//업로드한 파일(inputStream)을 다운로드함 -> resp.OutputStream
		try(OutputStream out = resp.getOutputStream()) {
			Files.copy(filePath, out);
		} 
	}
}
