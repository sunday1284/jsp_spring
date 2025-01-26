package kr.or.ddit.servlet09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value="/files/delete")
public class ImageFileDeleteControllerServlet extends HttpServlet {
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
		//오류 났던 이유 -> getWriter로 resp를 보내버렸기때문에 안됐음..;
		//6. 파일 삭제 
		Files.delete(filePath);	
		
		//7. location 을 결정하고, 리다이렉트  
		String location = req.getContextPath()+"/files";
		resp.sendRedirect(location);
	}
}
