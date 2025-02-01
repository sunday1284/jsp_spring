<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 동기로 먼저 테스트 -> 페이지 전환구조 현재는 비동기 형식이라 해당 서블릿x -->
<h2>참가자 리스트</h2>

<table class="table">
	<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>성별</th>
		<th>나이</th>
		<th>주소</th>
	</tr>
	
	<c:forEach items="${perList}" var="perList">
		<tr>
			<td><a href="person.do?who=${perList.id}">${perList.id}</a></td>
			<td>${perList.name}</td>
			<td>${perList.gender}</td>
			<td>${perList.age}</td>
			<td>${perList.address}</td>
		</tr>
	
	</c:forEach>
	
</table>


</body>
</html>