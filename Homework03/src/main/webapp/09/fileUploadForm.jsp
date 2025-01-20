<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 화면</title>
</head>
<body>
<h4>파일 과제</h4>
<hr>
<h4>파일 업로드</h4>
<form method="post" action="${pageContext.request.contextPath}/files/upload" enctype="multipart/form-data">
    <input type="text" name="uploader" placeholder="업로더 이름"/>
    <input type="file" name="uploadFile"/>
    <button type="submit">전송</button>
</form>

<hr>
<h4>파일 삭제</h4>
<form method="get" action="${pageContext.request.contextPath}/files/delete" enctype="multipart/form-data">
    <input type="file" name="target" value="target"  placeholder="삭제할 파일명을 입력하세요"/>
    <button type="submit">삭제</button>
</form>
</body>
</html>