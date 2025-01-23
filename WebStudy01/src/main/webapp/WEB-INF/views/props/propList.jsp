<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%-- ${propList } --%>
<body>
<table>
	<thead>
		<tr>
			<th>property name</th>
			<th>property value</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty propList } "></c:if>
			<c:forEach items="${propList }" var="prop">
				<tr>
					<td>
						<!-- 서블릿에서 받아온 매핑정보를 셋팅함 -> 파라미터+쿼리스트링값 -->
						<!-- url-> /prop/detail?what=prop.propertyName -->
						<c:url value="/prop/detail" var="detailUrl">
							<!-- /prop/detail 에서 설정한 파라미터값을 셋팅   -->
							<!-- 쿼리스트링 셋팅 -> prop.propertyName값으로 받아옴 -->
							<c:param name="what" value="${prop.propertyName }"></c:param>
						</c:url>
						<a href="${detailUrl }">${prop.propertyName }</a>
					</td>
					<td>${prop.propertyValue }</td>
				</tr>
			</c:forEach>
		<c:if test="${empty propList } ">
			<tr>
				<td colspan="2">조회된 결과 없음.</td>
			</tr>
		</c:if>
			
	</tbody>
</table>
</body>
</html>