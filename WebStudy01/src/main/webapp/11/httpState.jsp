<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> session과 cookie 를 활용한 상태 유지</h4>
<pre>
	HTTP : Connect Less, State Less
	최소한의 대화를 유지하기 위한 상태정보를 저장하는 방법 
	1. session : 서버사이드 저장 개념(서버의 부하)
		세션의 생명 주기 
		생성 : 클라이언트의 최초 요청에 의한 세션 생성 
				-> 세션 아이디가 부여됨 -> 응답 헤더를 통해 클라이언트로 아이디 전송. -> 쿠키로 저장
			session id : <%=session.getId() %>
			생성 시점 : <%=new Date(session.getCreationTime()) %>
			유지(session tracking) : 쿠키로 저장된 세션의 아이디가 이후 요청에 서버로 재전송됨.
				session tracking mode : 세션 유지 방법(클라이언트와 서버가 세션 아이디를 공유하는 방법) 
				1) Cookie(***) : request.header 를 통해 공유 
				2) URL : <a href="<c:url value='/11/httpState.jsp'/>">리로딩</a>
				 	request line을 통해 공유(XXX) 
				3) SSL (Secured Socket Layer) 암호화 채널을 통해 세션 아이디 공유 
		마지막 요청 시점 : <%=new Date(session.getLastAccessedTime()) %>
		time-out : <%=session.getMaxInactiveInterval() %>
		
		소멸 : time-out 으로 만료 여부 결정 
			(마지막 요청과 새로운 요청 사이에 허용되는 최대 시간의 간격 : ex) 30분)
			직접적인 로그아웃(invalidate)
			
	2. cookie : 클라이언트사이드 저장 개념(보안에 취약)
	
</pre>
</body>
</html>