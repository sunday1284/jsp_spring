<%@page import="kr.or.ddit.streaming.vo.FileVO"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.nio.file.Path"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath() %>/medias/streaming">
	<!-- 파일을 선택했을때 바로 전송하는 방법  -->
<select name="file" onchange="this.form.requestSubmit();">
	<option value>파일 선택</option>
<%
	List<FileVO> fileList =  (List)request.getAttribute("fileList");
	for(FileVO single : fileList){
		%>
			<option value="<%=single.getRealtivePath() %>" 
					label="<%=single.getFilename() %>" 
					data-mine="<%=single.getMine() %>"		
			/>
		<% 
	}
%>
</select>
</form>
<div id="result-area">
</div>
<script src="<%=request.getContextPath() %>/resources/js/streaming/fileUI.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.7.1.min.js"></script>
</body>
</html>