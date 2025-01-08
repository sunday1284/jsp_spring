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
<pre>
<%
	LocalDateTime food = (LocalDateTime) request.getAttribute("food");
	String sessionAttr = (String) session.getAttribute("sessionAttr");
%>
	모델을 포장해서 UI 로 만들어진 컨텐츠 : <%=food %>
	세션에 저장된 데이터 : <%=sessionAttr %>
	<a href="<%=request.getContextPath() %>/03/scopeView.jsp">scope 내의 데이터의 전달 범위 확인</a>
</pre>
</body>
</html>


















