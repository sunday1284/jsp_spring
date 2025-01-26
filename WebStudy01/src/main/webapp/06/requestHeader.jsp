<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.accept{
		background-color: yellow;
	}
</style>
</head>
<body>
<h4>request Header</h4>
<pre>
	: request header 영역을 통해 전송되는 문자열 형태의 메타데이터(name / value).
	: client 와 request body 에 대한 부가적인 정보를 전송할 때 활용됨.
	ex)
	클라이언트 에이전트 시스템 정보 : user-agent
	request body 를 전송하는 컨텐츠에 대한 정보
		content-type
		content-length  
	response content 의 종류를 표현할 때
		accept 
	response content 가 텍스트 기반일 때, 해당 텍스트를 표현하는 언어를 결정할 수 있음.
		accept-language
</pre>
<table>
	<thead>
		<tr>
			<th>헤더이름</th>
			<th>헤더값</th>
		</tr>
	</thead>
	<tbody>
	<%
		Enumeration<String> names = request.getHeaderNames();
		while(names.hasMoreElements()){
			String headerName = names.nextElement();
			String headerValue = request.getHeader(headerName);
			%>
			<tr class="<%=headerName.toLowerCase() %>">
				<td><%=headerName %></td>
				<td><%=headerValue %></td>
			</tr>
			<%
		}
	
	%>	
	</tbody>
</table>
</body>
</html>