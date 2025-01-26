<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
입력 UI(input, select) + 전송UI (form, submit button) 
<!-- url(action) 생략하면 서블릿에서 dispacher로 jsp로 보낸 url이 기본값이 된다. -->
<form method="post">
	<input type="number" name="operand" />
	<button type="submit">전송(동기 요청, HTML 수신)</button> <!-- 제출하는 순간 폼으로 이동  -->
	<button type="button" id="async-btn"
		 data-url="">비동기 요청 버튼(JSON 수신)</button>
</form>
<div id="result-area">
	
</div>
<script src="<%=request.getContextPath()%>/resources/js/05/factorialForm.js"></script>
</body>
</html>