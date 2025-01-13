package kr.or.ddit.streaming;

import java.io.IOException;
import java.io.OutputStream;
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

@WebServlet("/medias/streaming")
public class FileStreamingServlet extends HttpServlet {
	private String folderPath;
	private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		folderPath = application.getInitParameter("mediasFolder");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// server.xml에서 bodyencoding설정을 해서 Get에서도 인코딩 가능
		req.setCharacterEncoding("UTF-8");
		String relativePath = req.getParameter("file");
		if (StringUtils.isBlank(relativePath)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
			return;
		}
		
		Path filePath = Paths.get(folderPath, relativePath);
		System.out.println(filePath);
		if(!Files.exists(filePath)) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 파일이 없음.");
			return;
		}
		
		String mine = application.getMimeType(filePath.getFileName().toString());
		resp.setContentType(mine);
		
		OutputStream os =  resp.getOutputStream();
		Files.copy(filePath, os);
		os.close();
	}
}
