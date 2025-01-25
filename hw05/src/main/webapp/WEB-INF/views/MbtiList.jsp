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
<h4>Mbti 리스트</h4>
<table>
	<thead>
		<tr>
			<th>식별번호</th>
			<th>MBTI 타입</th>
			<th>MBTI 형식</th>
			<th>MBTI 성격</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty mbtilist }"></c:if>
			<c:forEach items="${mbtilist}" var="mbti">
				<tr>
					<td>${mbti.mtSort }</td>
					
					<td> 
						<c:url value="/mbti/detail" var="MbtiUrl">
							<c:param name="what" value="${mbti.mtType }"></c:param>
						</c:url>
						<a href="${MbtiUrl}">${mbti.mtType }</a>	
					</td>
					<td>${mbti.mtTitle }</td>
					<td>${mbti.mtContent }</td>
				</tr>
			</c:forEach>
		<c:if test="${empty mbtilist }">
			<tr>
				<td colspan="4">mbti 결과가 없습니다..</td>
			</tr>
		</c:if>
	</tbody>
</table>
</body>
</html>