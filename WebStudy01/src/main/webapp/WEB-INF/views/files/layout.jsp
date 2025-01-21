<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<jsp:include page="/WEB-INF/views/files/frags/section1.jsp"/>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/files/frags/section3.jsp"/>
	</header>
	<main class ="bg-primary-subtle">
		<jsp:include page="${section4 }"/>
	</main>
	<jsp:include page="/WEB-INF/views/files/frags/section2.jsp"/>
</body>
</html>