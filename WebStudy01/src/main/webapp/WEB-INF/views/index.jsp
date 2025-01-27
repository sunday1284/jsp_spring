<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<h4>웰컴 페이지</h4>
<c:choose>
	<c:when test="${not empty authMember }">
		<h4>현재 사용자 : 
		
			<a href="<c:url value='/mypage'/>">${authMember.memName}</a>
		
		</h4>	
		<a href="<c:url value='/Login/Logout'/>">로그아웃</a>
	</c:when>
	<c:otherwise>
		<a href='<c:url value='/login/loginForm.jsp'/>'>로그인</a>	
	</c:otherwise>
</c:choose>
