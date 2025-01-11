<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>파일 목록</h1>
	<p>파일 선택:</p>
	<iframe src="<%=request.getParameter("target")%>" width="100%" height="600px"></iframe>
</body>
</html>