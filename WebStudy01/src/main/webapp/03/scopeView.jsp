<%@page import="java.time.LocalDateTime"%>
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
	LocalDateTime food = (LocalDateTime) request.getAttribute("food");
	String sessionAttr = (String) session.getAttribute("sessionAttr");
	String applicationAttr = (String) application.getAttribute("applicationAttr");
%>
<pre>
	request scope attribute : <%=food %>
	session scope attribute : <%=sessionAttr %>
	application scope attribute : <%=applicationAttr %>
</pre>
</body>
</html>