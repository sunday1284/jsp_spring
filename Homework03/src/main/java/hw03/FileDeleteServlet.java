package hw03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/files/delete")
public class FileDeleteServlet extends HttpServlet {
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
        String targetFileName = req.getParameter("target");

        if (targetFileName == null || targetFileName.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Path filePath = folderPath.resolve(targetFileName);

        if (!Files.exists(filePath) || Files.isDirectory(filePath)) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "파일이 존재하지 않습니다!!");
            return;
        }

        try {
            Files.delete(filePath);
            resp.getWriter().write("파일 삭제 성공!!");
        } catch (IOException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "파일 삭제 실패");
        }
    }
}