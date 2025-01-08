<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/04/mimeForm.js"></script>
</head>
<body>
<form id="mime-form" action="<%=request.getContextPath()%>/04/mime/forJson" method="get">
<input type="text" name="filename" />
<button type="submit">MIME 조회, submit 버튼</button>
<button type="button">그냥 버튼</button>
</form>
<div>
	조회한 mime type : <span id="mime-area"></span>
</div>
</body>
</html>