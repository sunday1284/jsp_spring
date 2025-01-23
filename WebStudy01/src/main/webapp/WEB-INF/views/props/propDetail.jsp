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
		<th>property name</th>
		<td>${property.propertyName }</td>
	</tr>
	<tr>
		<th>property value</th>
		<td>${property['propertyValue'] }</td>
	</tr>
	<tr>
		<th>description</th>
		<td>${property.description }</td>
	</tr>
</table>
</body>
</html>