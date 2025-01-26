package hw05.mbti.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import hw05.mbti.service.MbtiService;
import hw05.mbti.service.MbtiServiceImpl;
import hw05.mbti.vo.MbtiVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//첨부한 스키마 파일에서 테이블에 등록하지 않는 나머지 유형을 등록할 수 있는 신규 mbti 유형 추가
@WebServlet("/mbti/new")
public class MbtiInsertServlet extends HttpServlet {
	private MbtiService service = new MbtiServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		//beanutils로 한번에 데이터를 받아옴
		MbtiVO mbvo = new MbtiVO();
		Map<String, String[]> param = req.getParameterMap();
		try {
			BeanUtils.populate(mbvo, param);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "데이터 처리 중 오류 발생");
		    return;
		}
		
		//서비스에서 셋팅한 값을 가져옴
		int cnt =service.insertMbti(mbvo);
		
		String path = req.getContextPath() +"/mbti/list";
		if(cnt>0) {
			resp.sendRedirect(path);
		}else {
			resp.sendError(400, "파라미터 값 누락");
			req.getRequestDispatcher("/Form/MbtiinsertForm.jsp").forward(req, resp);
			return;
		}
	}
}
