package kr.or.ddit.streaming;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.streaming.vo.FileVO;

@WebServlet("/medias/files")
public class FileFormUIControllerServlet extends HttpServlet{
	private ServletContext application;
	private Path folderPath;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		folderPath =  Paths.get(application.getInitParameter("mediasFolder"));
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		File folder = new File("D:/00.medias/images");
//		String[] files = folder.list();
		//모델 생성
		List<FileVO> fileList = new ArrayList<>();
		
		Files.walkFileTree(folderPath, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				String label = file.getFileName().toString();
				String value = folderPath.relativize(file).toString();
				value = StringUtils.replace(value, "\\", "/");
				value = StringUtils.prependIfMissing(value,"/");
				FileVO vo = new FileVO();
				vo.setFilename(label);
				vo.setRealtivePath(value);
				vo.setMine(application.getMimeType(label));
				fileList.add(vo);
				return super.visitFile(file, attrs);
			}
		});
		
		//information
		req.setAttribute("fileList", fileList);
		req.setAttribute("folderPath", folderPath);

		String path = "/WEB-INF/views/streaming/fileUI.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
