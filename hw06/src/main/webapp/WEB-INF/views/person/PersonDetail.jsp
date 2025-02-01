<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>참가자 상세 조회</title>
</head>
<body>
<h4>참가자 상세 정보</h4>
<!-- 동기로 먼저 테스트 -> 페이지 전환구조 현재는 비동기 형식이라 해당 서블릿x -->
<table>
	<tr>
		<td>아이디 :${person.id }</td>
		<td>이름 : ${person.name }</td>
		<td>성별 : ${person.gender }</td>
		<td>나이 : ${person.age }</td>
		<td>주소 : ${person.address }</td>
	</tr>
</table>


</body>
</html>