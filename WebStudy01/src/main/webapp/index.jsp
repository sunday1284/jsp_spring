<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>웰컴 페이지</h4>
<c:choose>
	<c:when test="${not empty authMember }">
		<h4>현재 사용자 : ${authMember}</h4>	
		<a href="<c:url value='/Login/Logout'/>">로그아웃</a>
	</c:when>
	<c:otherwise>
		<a href='<c:url value='/login/loginForm.jsp' />'>로그인</a>	
	</c:otherwise>
</c:choose>

</body>
</html>