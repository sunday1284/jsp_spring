package kr.or.ddit.servlet09;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/files")
public class ImageFileListControllerServlet extends HttpServlet {
	
	private ServletContext application;
	private Path folderPath;
	
		
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application =  getServletContext();
		Path parent = Paths.get(application.getInitParameter("mediasFolder"));
		folderPath = parent.resolve("images");
		application.setAttribute("folderPath", folderPath);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Path> fileList = new ArrayList<>();
		Files.walkFileTree(folderPath, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				fileList.add(file);
				return super.visitFile(file, attrs);
			}
		});
		
		// scope에 attribute로 저장.
		// request dispatch로 이동하는 경우 : request scope활용
		// redirect로 이동하는 경우 : session scope활용
		
		req.setAttribute("fileList", fileList);
		req.setAttribute("section4", "/WEB-INF/views/files/fileList.jsp");
		String path = "/WEB-INF/views/files/layout.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
