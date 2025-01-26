<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MBTI 정보 수정</title>
</head>
<body>
<h4>${info.mtTitle} 수정하기</h4>

<c:if test="${not empty errorMessage}">
    <p style="color: red;">${errorMessage}</p>
</c:if>

<form action="${pageContext.request.contextPath}/mbti/edit" method="post">
    <input type="hidden" name="mtType" value="${info.mtType}">

    <table border="1" cellpadding="10" cellspacing="0">
        <tr>
            <th>식별 번호</th>
            <td><input type="text" name="mtSort" value="${info.mtSort}" required></td>
        </tr>
        <tr>
            <th>MBTI 성격</th>
            <td><input type="text" name="mtTitle" value="${info.mtTitle}" required></td>
        </tr>
        <tr>
            <th>MBTI 내용</th>
            <td><textarea name="mtContent" required>${info.mtContent}</textarea></td>
        </tr>
    </table>
    <button type="submit">수정</button>
</form>

<a href="${pageContext.request.contextPath}/mbti/list">목록으로 돌아가기</a>
</body>
</html>
