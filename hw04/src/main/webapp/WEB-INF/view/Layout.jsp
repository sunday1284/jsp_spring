<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<jsp:include page="/WEB-INF/view/frags/section1.jsp"></jsp:include>
<body>
	<header>
		<jsp:include page="/WEB-INF/view/frags/section3.jsp"></jsp:include>
	</header>
	<main>
		<jsp:include page="${section04}"></jsp:include>
	</main>
	<jsp:include page="/WEB-INF/view/frags/section2.jsp"></jsp:include>
</body>
</html>