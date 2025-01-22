package hw04.bts;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bts/detail")
public class BtsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String whoParam = req.getParameter("who");

        // 멤버별 JSP 파일 경로 설정
        Map<String, String> map = new HashMap<>();
        map.put("B001", "/WEB-INF/view/bts/rm.jsp");
        map.put("B002", "/WEB-INF/view/bts/jin.jsp");
        map.put("B003", "/WEB-INF/view/bts/suga.jsp");
        map.put("B004", "/WEB-INF/view/bts/jhop.jsp");
        map.put("B005", "/WEB-INF/view/bts/jimin.jsp");
        map.put("B006", "/WEB-INF/view/bts/bui.jsp");
        map.put("B007", "/WEB-INF/view/bts/jungkuk.jsp");

        // 선택한 멤버의 컨텐츠 페이지 경로를 설정
        String memberPage = map.get(whoParam);
        if (memberPage != null) {
            req.setAttribute("section04", memberPage);
        } else {
            req.setAttribute("section04", "/WEB-INF/view/MemberUI.jsp");
        }

        // Layout.jsp로 포워딩
        String path = "/WEB-INF/view/Layout.jsp";
        req.getRequestDispatcher(path).forward(req, resp);
    }
}