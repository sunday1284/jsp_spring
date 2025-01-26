<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 결과</title>
</head>
<body>
    <h1>선택한 이미지</h1>
    <%
    String selectedImage = request.getParameter("image");
    if (selectedImage != null) {
        String imagePath = request.getContextPath() + "/images/imageForm.do?image=" + selectedImage;
    %>
        <h2>이미지 이름: <%= selectedImage %></h2>
        <img src="<%= imagePath %>" alt="<%= selectedImage %>" />
    <%
    } else {
    %>
        <h2>선택된 이미지가 없습니다.</h2>
    <%
    }
    %>
</body>
</html>
