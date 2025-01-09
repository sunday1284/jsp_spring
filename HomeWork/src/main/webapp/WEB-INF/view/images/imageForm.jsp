<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 목록</title>
</head>
<body>
    <h1>이미지 선택</h1>
    <form action="<%=request.getContextPath()%>/images/imageForm.do" method="post">
        <select name="image">
            <% 
            List<String> imageList = (List<String>) request.getAttribute("imageList");
            if (imageList != null && !imageList.isEmpty()) {
                for (String imageName : imageList) {
            %>
                    <option value="<%= imageName %>"><%= imageName %></option>
            <% 
                }
            } else { 
            %>
                <option>이미지가 없습니다</option>
            <% 
            } 
            %>
        </select>
        <button type="submit">이미지 랜더링</button>
    </form>
</body>
</html>
