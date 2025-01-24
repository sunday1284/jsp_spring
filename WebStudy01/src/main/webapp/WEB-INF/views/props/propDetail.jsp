<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- ${property } --%>
<table>
	<tr>
		<!-- property name값만 꺼내옴 -->
		<th>property name</th>
		<td>${property.propertyName }</td>
	</tr>
	<tr>
		<!-- property value값만 꺼내옴 -->
		<th>property value</th>
		<td>${property['propertyValue'] }</td>
	</tr>
	<tr>
		<!-- description값만 꺼내옴 -->
		<th>description</th>
		<td>${property.description }</td>
	</tr>
</table>
</body>
</html>