<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String mime = (String)request.getAttribute("mime");
%>
<h4>조회한 MIME type : <%=mime %></h4>
</body>
</html>