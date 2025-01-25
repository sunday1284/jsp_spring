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
<h4>${mbtiInfo.mtTitle }의 정보 </h4>

<table>
	<tr>
		<th>식별 번호</th>
		<td>${mbtiInfo.mtSort }</td>
	</tr>
	<tr>
		<th>MBTI 유형</th>
		<td>${mbtiInfo.mtTitle }</td>
	</tr>
	<tr>
		<th>MBTI 성격</th>
		<td>${mbtiInfo.mtContent }</td>
	</tr>
</table>
</body>
</html>