package kr.or.ddit.images;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

//과제 -> get (imageForm.jsp) -> post(imageResult.jsp)로 이미지 띄우기 
@WebServlet("/images/imageForm.do")
public class ImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 이미지 디렉토리 경로
    private static final String IMAGE_DIR = "D:/00.medias/images";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //서블릿을 실행하면 먼저 get으로 디렉토리 안에 있는 파일들을 불러온다.
    	File imageDir = new File(IMAGE_DIR);
        List<String> imageList = new ArrayList<>();

        // 디렉토리가 존재하고 파일이 있는지 확인 -> 잘몰라서 구글링 
        if (imageDir.exists() && imageDir.isDirectory()) {
            File[] imageFiles = imageDir.listFiles((dir, name) -> 
                name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpg")
            );
            if (imageFiles != null) {
                for (File file : imageFiles) {
                    imageList.add(file.getName());
                }
            }
        }

        // 이미지 목록 전달
        req.setAttribute("imageList", imageList);

        // JSP로 포워딩
        req.getRequestDispatcher("/WEB-INF/view/images/imageForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //이미지 결과 화면은 Post로 보여줌 
    	String selectedImage = req.getParameter("image");
        // 이미지 파일 넣을때 디렉토리명 /를 무조건 해줘야 파일 값이 들어간다...
        File imageFile = new File(IMAGE_DIR + "/" + selectedImage);

        if (imageFile.exists() && imageFile.isFile()) {
            resp.setContentType(Files.probeContentType(imageFile.toPath()));
            Files.copy(imageFile.toPath(), resp.getOutputStream());
            resp.getOutputStream().flush();
        } else {
            // 이미지가 없을 경우 에러 처리
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,"해당 디렉토리에 이미지가 없습니다.");
        }
        
        
        req.getRequestDispatcher("/WEB-INF/view/images/imageResult.jsp").forward(req, resp);
    }
}
