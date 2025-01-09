<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="radio" name="dataType" value="json"/>JSON
<input type="radio" name="dataType" value="html"/>HTML
<input type="radio" name="dataType" value="xml"/>XML
<form name="target-form" method="post">
<input type="number" name="amount"/>
<button type="submit">전송</button>
</form>
<div data-result="랜더링 영역">
	
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/currency/formUI.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.7.1.min.js"></script>
</body>
</html>