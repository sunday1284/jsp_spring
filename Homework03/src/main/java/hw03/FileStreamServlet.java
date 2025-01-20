package hw03;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import hw03.vo.FileInfoVO;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/files/stream")
public class FileStreamServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ServletContext application;
    private Path folderPath;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        application = getServletContext();
        folderPath = Paths.get(application.getInitParameter("mediasFolder"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        FileInfoVO fileInfo = (FileInfoVO) session.getAttribute("uploadedFile");

        if (fileInfo == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No uploaded file found in session.");
            return;
        }

        Path filePath = fileInfo.getFilePath();
        File file = filePath.toFile();

        if (!file.exists() || file.isDirectory()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found.");
            return;
        }

        response.setContentType(Files.probeContentType(filePath));
        response.setContentLengthLong(Files.size(filePath));
        
        //파일 크기 지정해서 해당 파일을 로드함 -> 동영상이면 다운로드되기 함
        try (FileInputStream fis = new FileInputStream(file); OutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error streaming file.");
        }
    }
}