<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<jsp:include page="/WEB-INF/views/includee/preScript.jsp"/>
</head>
<body class="vh-100">
	<header>
		<jsp:include page="/WEB-INF/views/includee/title.jsp"/>
	</header>
	<main class ="bg-primary-subtle h-100">
		<jsp:include page="${content }"/>
	</main>
	<jsp:include page="/WEB-INF/views/includee/postScript.jsp"/>
</body>
</html>