package kr.or.ddit.images;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
//과제 -> 이미지 스트리밍 서비스 Part2 -> FileViewerServlet -> FileList.jsp 
@WebServlet("/medias/files")
public class FileViewerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    //기본 디렉토리 설정 -> 본인 외장하드 디렉토리 -> 집에서 테스트를 위함
    private static final String BASE_DIR = "E:/00.medias";
    //기본 디렉토리 설정 -> 학원 디렉토리
//  private static final String BASE_DIR = "D:/00.medias";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = req.getParameter("target");
        String fileParam = req.getParameter("file");

        if (fileParam != null) {
            // 파일 뷰어 처리
            handleFileView(req, resp, fileParam);
        } else {
            // 파일 목록 표시 처리
            handleFileList(req, resp, target);
        }
    }
    
    // 파일 목록을 처리함 
    private void handleFileList(HttpServletRequest req, HttpServletResponse resp, String target) throws ServletException, IOException {
        File targetDir; //파일 디렉토리 초기변수 생성 
        
        //디렉토리 안에 관련 파일이 없거나 비어 있으면 해당 처리 
        if (target == null || target.isEmpty()) {
        	//타겟이 있으면 기본디렉토리 사용
            targetDir = new File(BASE_DIR);
        } else {
        	// 없으면 해당 디렉토리 사용
            targetDir = new File(BASE_DIR, target);
        }
        
        //디렉토리가 존재하지않거나 디렉토리 자체가 없으면 해당 예외처리 -> 404
        if (!targetDir.exists() || !targetDir.isDirectory()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 디렉토리가 존재하지 않습니다!!");
            return;
        }

        List<String> fileList = new ArrayList<>();
        //파일을 배열로 선언하고, 디렉토리 안에 있는 모든 파일 목록을 읽어옴 
        //true면 확장자명에 상관없이 모든 파일을 읽어온다.
        File[] allFiles = targetDir.listFiles((dir, name) -> true);
        if (allFiles != null) {
            for (File file : allFiles) {
                fileList.add((target == null || target.isEmpty() ? "" : target + "/") + file.getName());
            }
        }

        req.setAttribute("fileList", fileList);
        req.setAttribute("currentPath", target == null ? "" : target);
        req.getRequestDispatcher("/WEB-INF/view/medias/FileList.jsp").forward(req, resp);
    }
    
    //파일을 브라우저에서 띄워 해당 파일을 볼 수 있는 메서드이다.
    private void handleFileView(HttpServletRequest req, HttpServletResponse resp, String filePath) throws ServletException, IOException {
        File file = new File(BASE_DIR, filePath); // 파일 객체를 생성함
        //파일이 존재하지 않았을때의 404예외처리
        if (!file.exists() || !file.isFile()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 파일이 존재하지 않습니다!!");
            return;
        }
        
        //파일의 MINE타입을 설정
        resp.setContentType(getServletContext().getMimeType(file.getName()));
        //파일 길이 설정 
        resp.setContentLengthLong(file.length());

        // 다운로드 대신 브라우저에서 파일을 열기 위한 헤더 설정
        resp.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");

        try (FileInputStream in = new FileInputStream(file); 
        		OutputStream out = resp.getOutputStream()) {
            byte[] buffer = new byte[1024]; // 버퍼 크기 설정
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
            	//파일 데이터를 출력할 때 씀
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}
