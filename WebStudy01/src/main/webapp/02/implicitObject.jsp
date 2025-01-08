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
ServletContext : <%=application.hashCode() %>
HttpSession : <%=session.hashCode() %>
HttpServletRequest : <%=request.hashCode() %>
</pre>
</body>
</html>