<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> Location 헤더의 활용(Redirect) </h4>
<pre>
	: 3xx 번대 상태코드와 body가 없는 응답에서 활용되는 헤더로,
	  클라이언트는 응답을 수신하고, 
	  location 방향으로 새로운 요청을 전송함.(redirect).
	  
	  1. 자원의 위치 재지정. -> 새로운 자리
	  	ex) 비보호 일반 채널로 접속한 클라이언트를 보호 채널을 개방하도록 유도할 때 활용.
	  	http://www.naver.com---> 307, no body, location=https:www.naver.com 
	  	--> location방향으로 리다이렉트.
	  2. 신규 자원을 등록하기 위한 Post 요청이 발생하고, 
	  	 서버에서 등록 처리 후, 갱신된 자원의 목록을 새로 요청할 수 있도록 유도할 때 활용
	  	 Post -> Redirect (302, Location) -> Get (PRG 패턴) 방식으로 location 방향의 새로운 요청 발생. 
</pre>
<%--
	//response.setHeader("Location", "https://www.naver.com"); 영향 없음
	String location = request.getContextPath()+"/10/dest.jsp";
	response.sendRedirect(location);
--%>
<h4> Request Dispatch 활용</h4>
<pre>
	http 특성 : Connect Less, State Less 
	: 기존의 요청을 그대로 가지고 분기하는 이동 구조.
	1. forward : Medel2 구조의 기반 형태. A와 B가 각기 다른 역할을 수행함.
		ex) controller : request 처리(servlet)
			view : response 생성 (jsp)
		<%
			String path = "/10/dest.jsp";
// 			RequestDispatcher rd = request.getRequestDispatcher(path);
// 			rd.forward(request, response);
			//페이지가 합쳐진다 -> 두개의 jsp가 모듈화되서 합쳐짐
// 			rd.include(request, response);
		%>
		<!-- 커스텀테그 -> 백엔드에서 작동 -->
		<jsp:include page="<%=path %>"></jsp:include>
	2. include : view layer 에서 주로 활용되는 형태로,
				하나의 응답 페이지를 여러개의 jsp를 복합해 처리하는 모듈화 구조에서 활용됨.
</pre>
</body>
</html>