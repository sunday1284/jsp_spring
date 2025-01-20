<%@page import="java.time.LocalDateTime"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> header를 이용한 메타 데이터 활용</h4>
<pre>
	: body를 통해 전송되는 컨텐츠를 수식하는 메타 데이터 
	
	* request header
		1. Accept* (희망사항)
		   Accept : 응답 컨텐츠의 종류를 표현하는 방법
		   	ex) accept : application/json --> 응답 컨텐츠 협상에 활용됨.
		   Accept-Language : 응답 컨텐츠를 표현하는 언어의 종류
		   	ex) accept-language : ko-KR,en-US
		   Accept-Encoding : 응답 컨텐츠를 압축하는 방법을 표현.
		   	ex) accept-encoding : gzip
		2. Content* : request body 의 컨텐츠에 대한 수식.
			Content-Type : request body content's MINE
				ex)
					content-type : application/x-www-form-urlencoded (request parameter)
					*** content-type : multipart/form-data -> 파일 관련 (request part)
				 	content-type : application/json (request payload)
			content-Length : request body content's size
			Content-Disposition : multipart request 에서 하나의 part 안의 컨텐츠에 대한 수식.
				ex)
					content-disposition:form-data;name="파트명";filename="업로드파일명"
					input type= "file"
					content-disposition:form-data;name="파트명"
					input type= "text", select 
	* response header
		1. Content* : response body 의 컨텐츠를 수식하는 표현.
					  클라이언트가 해당 컨텐츠를 소비하는 방식이나 형태를 결정할 때 사용됨.
		   1) Content-Type : response body content's MINE	
		   2) Content-Length : response body content's size
		   		<%--
		   			response.setContentLength(100);
		   		--%>
		   3) Content-Disposition : type[inline|attatchment]
		   	  inline(default) : 브라우저의 윈도우 내에서 해당 컨텐츠 소비
		   	  attatchment : 브라우저내에서 소비되지 않고 별도 파일의 형태로 다운로드 처리
		   	  	  ex) Content-Disposition:attatchment;filename="다운로드파일명"
		   	  <%--
		   	  	  String filename = "한글 저장명-abcd";
// 		   	  	  네트워크를 통해 전송되는 특수문자를 인코딩하는 방식 : percent encoding(url, encoding)
				  String encodedFilename = URLEncoder.encode(filename, "utf-8")
				  							.replaceAll("\\+", " ");
		   	  	  response.setHeader("Content-Disposition", 
		   	  			  		"attatchment;filename=\""+encodedFilename+"\"");
		   	  --%> 
		 2. Refresh : 클라이언트는 refresh 에 설정된 초단위 주기로 현재 페이지를 갱신함(새로 고침)
		 				현재 도큐먼트에 대한 락을 소유한 동기 요청에 대한 응답에서만 사용가능.
		 				비동기 요청으로 갱신 자원에 대한 처리시에는 스케쥴링 함수로 직접 처리함.
		 				setInterval : 주기적인 반복 처리에 사용됨.
		 				setTimeout : 특정 처리에 대한 실행 시점을 지연시킬때.
<!-- 		 		현재 사이트 리뉴얼중, 10초 뒤 네이버로 이동할게요.    -->
		 	<%--
		 		response.setHeader("refresh", "5;url=https://www.naver.com");
		 	--%>
		 	<span id="server-time"></span>
		 3. Cache-Control
		 4. Location
</pre>

<form method="post" action="${pageContext.request.contextPath }/09/file-upload.do"
	enctype="multipart/form-data"
>
	
	<input type="text" name="uploader"/>
	<input type="file" name="uploadFile"/>
	<button type="submit">전송</button>
</form>
<script>
	document.addEventListener("DOMContentLoaded", ()=>{
		const $serverTime = $("#server-time");
// 		setInterval(() => {
// 			$.ajax({
// 				url:"server-time.jsp",
// 				method:"get",
// 				dataType:"text", // json(application/json), html, text(text/plain), script(text/javascript), xml(application/xml)
// 				success:function(plain){
// 					$serverTime.text(plain);
// 				}
// 			});
// 		}, 1000);
	});
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
	
</body>
</html>