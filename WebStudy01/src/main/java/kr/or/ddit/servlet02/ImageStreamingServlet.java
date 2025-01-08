package kr.or.ddit.servlet02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/02/image.do")
public class ImageStreamingServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String imagePath = "D:/contents/target.png";
		Path targetPath = Paths.get(imagePath);
		
		String mime = getServletContext().getMimeType(targetPath.getFileName().toString());
		
		resp.setContentType(mime);
		
		ServletOutputStream out = resp.getOutputStream();
		
		Files.copy(targetPath, out);
		
		out.close();
	}
}














