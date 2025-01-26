package hw05.mbti.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import hw05.mbti.service.MbtiService;
import hw05.mbti.service.MbtiServiceImpl;
import hw05.mbti.vo.MbtiVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mbti/edit")
public class MbtiUpdateServlet extends HttpServlet {

    private MbtiService service = new MbtiServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 인코딩 설정
        req.setCharacterEncoding("utf-8");

        // 파라미터 수신
        String mtSort = req.getParameter("mtSort");
        String mtType = req.getParameter("mtType");
        String mtTitle = req.getParameter("mtTitle");
        String mtContent = req.getParameter("mtContent");

        // 디버깅 로그 추가
        System.out.println("Received Parameters -");
        System.out.println("mtSort: " + mtSort);
        System.out.println("mtType: " + mtType);
        System.out.println("mtTitle: " + mtTitle);
        System.out.println("mtContent: " + mtContent);

        // 입력값 검증
        if (StringUtils.isBlank(mtSort) || !StringUtils.isNumeric(mtSort)) {
            resp.sendError(400, "mtSort가 숫자가 아니거나 누락되었습니다.");
            return;
        }
        if (StringUtils.isBlank(mtType) || StringUtils.isBlank(mtTitle) || StringUtils.isBlank(mtContent)) {
            resp.sendError(400, "필수 파라미터가 누락되었습니다.");
            return;
        }

        // MbtiVO 객체 생성
        MbtiVO mvo = new MbtiVO();
        mvo.setMtSort(Integer.parseInt(mtSort));
        mvo.setMtType(mtType);
        mvo.setMtTitle(mtTitle);
        mvo.setMtContent(mtContent);

        // 서비스 호출
        int cnt = service.updateMbti(mvo);

        // 결과 처리
        if (cnt > 0) {
            resp.sendRedirect(req.getContextPath() + "/mbti/list");
        } else {
            req.setAttribute("errorMessage", "MBTI 정보 수정에 실패했습니다.");
            req.getRequestDispatcher("/WEB-INF/views/editPage.jsp").forward(req, resp);
        }
    }
}
