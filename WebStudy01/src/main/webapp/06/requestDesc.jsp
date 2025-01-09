<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Http request packaging : HttpServletRequest</h4>
<form action="requestDesc.jsp" method="get">
	<input type="text" name="param" />
	<input type="text" name="param" />
	<input type="text" name="param" />
	<button type="submit">전송</button>
</form>
<pre>
	1. Request Line : URL, method
		url : <%=request.getRequestURL() %>
		uri : <%=request.getRequestURI() %>
		resource path : <%=request.getServletPath()%>
		http method : <%=request.getMethod()%>
		<a href="requestDesc.jsp">새로고침</a>
		
		URL(수신자, 자원의 식별자) : protocol://server식별자:port + context path + resource path
		protocol://server식별자:port --> origin (자원의 출처)
		http method : 요청의 의도(목적)와 패키징 방식에 대한 표현.
		GET(R) : form 을 제외한 나머지 UI 에 의해 발생하는 요청. (body 가 없는 조회용 요청)
		POST(C) : form 의 method 속성에 의해 발생하는 요청. (body가 있는 등록용 요청)
		
		PUT(전체 수정)/PATCH(부분 수정)(U) : 자원에 대한 수정을 의도로 사용되는 요청.
		DELETE(D) : 자원에 대한 삭제를 의도로 사용되는 요청.
		OPTIONS : 사전 요청(preFlight)에 사용되는 메소드
		HEAD : 응답데이터에 response body 가 없는 형태로 응답이 전송됨. -> 내 세션만 연장
		TRACE : 서버를 디버깅해서 상태를 확인할 목적의 요청.
		
	2. <a href="requestHeader.jsp">Request Header</a> : 클라이언트와 요청에 대한 메타 데이터 전송 영역.
			header name 과 header value로 표현되는 문자열의 형태.
	<%
		String userAgent = request.getHeader("user-agent");
		String contentType = request.getContentType();
		long contentLength = request.getContentLengthLong();
	%>		
	user-agent : <%=userAgent %>
	content-type : <%=contentType %>
	content-length : <%=contentLength %>
	3. Request Body(Content Body, Message Body) : only POST 요청인 경우 형성되는 영역.
			: 클라이언트가 서버쪽으로 의도적으로 전송하는 메시지(컨텐츠) 영역.
		content 종류 
		1) request parameter : name 과 value 의 문자열 형태로 전송되는 컨텐츠 
			String request.getParamter(name)
			String[] request.getParamterValues(name)
			Map&lt;String,String[]&gt; request.getParamterMap()
			
			경우에 따라, 제한적으로 body가 없는 파라미터가 전송되기도 함.
			URL?QueryString 형태 전송 
			QueryString 형태 
			?파라미터명=파라미터값&파라미터명=파라미터값...
		2) multipart 데이터 
		3) request payload
	
</pre>
</body>
</html>