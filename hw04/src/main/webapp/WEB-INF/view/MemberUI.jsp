<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form</title>
</head>
<body>
<form action="<c:url value='/bts/detail'/>" method="get">
    <label for="who">Select BTS Member:</label>
    <select name="who" id="who" onchange="this.form.submit()">
        <option value="B001">RM</option>
        <option value="B002">진</option>
        <option value="B003">슈가</option>
        <option value="B004">제이홉</option>
        <option value="B005">지민</option>
        <option value="B006">뷔</option>
        <option value="B007">정국</option>
    </select>
</form>
</body>
</html>