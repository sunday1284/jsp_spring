<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!-- data -> information -> content -->
<body>
<h4>ServletContext instance : <%=getServletContext().hashCode() %></h4>
<h4>현재 클라이언트 시각 : <span id="client-time"></span></h4>
<a href="<%=request.getContextPath() %>/02/now.do">현재 서버의 시각(동기요청)</a>
<a id="async-a" href="<%=request.getContextPath() %>/02/now.json">현재 서버의 시각(비동기요청)</a>

<div id="server-time">

</div>

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/01/time.js"></script>
</body>
</html>