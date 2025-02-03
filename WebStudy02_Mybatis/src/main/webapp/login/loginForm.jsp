<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>

<!-- flash attribute 방식 jstl 사용 -->
<c:if test="${not empty sessionScope.message }">
	<script type="text/javascript">
		alert("${sessionScope.message}");
	</script>
	<c:remove var="message" scope="session"/>
</c:if>

</head>
<body>
<h4> 웹에서 인증 처리 구조 </h4>
	아이디와 비밀번호 기반의 기본 인증 구조 사용.
	2FA : 기본 인증에 단계를 추가하여 보안을 강화하기 위한 2차 인증구조.
	<form action="${pageContext.request.contextPath }/Login/LoginProcess"
		 method="post" enctype="application/x-www-form-urlencoded">
		<ul>
			<li>
				아이디 : <input type="text" name="memId" />
			</li>
			<li>
				비밀번호 : <input type="password" name="memPass" />
				<button type="submit">로그인</button>
			</li>
		</ul>
	</form>
</body>
</html>
