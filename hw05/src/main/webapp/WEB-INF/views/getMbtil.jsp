<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MBTI 정보</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap-5.3.3-dist/css/bootstrap.min.css">
</head>
<body>
<h4>${mbtiInfo.mtTitle}의 정보</h4>
<table border="1" cellpadding="10" cellspacing="0" class="table">
    <tr>
        <th>식별 번호</th>
        <td>${mbtiInfo.mtSort}</td>
    </tr>
     <tr>
        <th>MBTI 타입</th>
        <td>${mbtiInfo.mtType}</td>
    </tr>
    <tr>
        <th>MBTI 성격</th>
        <td>${mbtiInfo.mtTitle}</td>
    </tr>
    <tr>
        <th>MBTI 내용</th>
        <td>${mbtiInfo.mtContent}</td>
    </tr>
</table>

<form action="${pageContext.request.contextPath}/mbti/edit" method="post" onsubmit="showAlertModAndRedirect();">
    <input type="hidden" name="mtSort" value="${mbtiInfo.mtSort}">
    <input type="hidden" name="mtType" value="${mbtiInfo.mtType}">
    <input class="form-control" type="text" name="mtTitle" value="${mbtiInfo.mtTitle}">
    <textarea class="form-control" name="mtContent" rows="6" cols="50">${mbtiInfo.mtContent}</textarea>
    <button type="submit">수정</button>
</form>

<form action="${pageContext.request.contextPath}/mbti/delete" method="get" onsubmit="showAlertAndRedirect();">
	<input type="hidden" name="what" value="${mbtiInfo.mtType}">
	<button type="submit">삭제</button>
</form>

<a href="${pageContext.request.contextPath}/mbti/list">목록으로 돌아가기</a>

<script>
function showAlertModAndRedirect() {
    alert('수정이 완료되었습니다.');
    window.location.href = "${pageContext.request.contextPath}/mbti/list"; // 목록 페이지로 리다이렉트
}
// 삭제 후 alert 창을 띄운 후 목록 페이지로 이동하는 함수
function showAlertAndRedirect() {
    alert('삭제가 완료되었습니다.');
    window.location.href = "${pageContext.request.contextPath}/mbti/list"; // 목록 페이지로 리다이렉트
}
</script>
</body>
</html>
