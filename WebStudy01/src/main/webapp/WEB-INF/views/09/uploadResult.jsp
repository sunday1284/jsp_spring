<%@page import="kr.or.ddit.servlet08.vo.FileInfoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--  el 문자 규칙 -> 첫문자는 소문자.. -->
<%--
	FileInfoVO fileInfo = (FileInfoVO) request.getAttribute("fileInfo");
--%>
<h4>DB에 저장된 업로더의 이름 : ${fileInfo.uploader}</h4>
<h4>DB에 저장된 원본 파일명 :
 <a href="${pageContext.request.contextPath }/09/file-download.do?target=${fileInfo.savename}&name=${fileInfo.originalFilename}">
	${fileInfo.originalFilename }
</a>
</h4>
<h4>DB에 저장된 저장 파일명 : ${fileInfo.savename}</h4>
<h4>DB에 저장된 저장경로 : ${fileInfo.filePath}</h4>
</body>
</html>