<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 및 다운로드</title>
</head>
<body>
<h4>저장 파일 업로드한 사람 : ${fileInfo.uploader}</h4>
<h4>저장된 원본 파일명 : 
    <a href="${pageContext.request.contextPath}/files/download?target=${fileInfo.savename}&name=${fileInfo.originalFilename}">
        ${fileInfo.originalFilename}
    </a>
</h4>
<h4>저장된 저장 파일명 : ${fileInfo.savename}</h4>
<h4>저장된 저장 파일명 : ${fileInfo.filePath}</h4>
<button onclick="window.location.href='${pageContext.request.contextPath}/files/stream'">파일 보기</button>
</body>
</html>