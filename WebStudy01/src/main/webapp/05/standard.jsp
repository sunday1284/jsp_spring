<%@page import="java.time.LocalDateTime" trimDirectiveWhitespaces="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>JSP(Java Server Page)</h4>
<pre>
	: 서블릿 스펙에서 파생된 템플릿 엔진 
	: Model2 구조에서 view layer(UI) 를 구성할 수 있어야함.
	
	jsp 소스의 구성 요소 
	1. 정적 텍스트(프론트엔드 모듈 구성) : 일반 텍스트, HTML, CSS, JavaScript
	2. 백엔드 모듈 구성 
		1) scriptlet : 
		<% // java code -> 서블릿 소스가 파싱될 때 지역 코드화(request callback)됨.
			String text = "텍스트 데이터";
			
		%>
		2) expression(표현식) : <%--=// 출력 대상 표현 --%>
			<%=text %>
		3) directive(지시자) :<%--@ 지시자명 속성이름="속성값"  --%>
			page (required) : 실행 결과나 로직에는 영향이 없으나, 최종적인 웹 페이지에 대한 메타 정보를 설정할 때 활용됨.
				contentType : response content-type 결정 
				import : import 전처리 구문 포함.
			taglib (option)	: 커스텀 태그 로딩
			include (option) : 정적 내포에 사용
		4) declaration(선언기호) : 
			<%!
			// 전역 변수나 전역 메소드 선언 
				private String instanceVar = "전역 테스트";
				public void instanceMethod(){}
			%>
		5) comment : <%-- 백엔드 주석 --%>
			<!-- 프론트엔드 주석만 보인다.  -->
			<!-- html 주석  -->
			<script>
//				 javascript 주석 
			</script>
			<style>
/*				 css 주석 */
			</style>
			<%
//				java 주석
			%>
<%-- 			<% jsp 주석 %> --%>
		6) 표현언어(Expression Language)
		7) 커스텀 태그(JSTL)
</pre>
</body>
</html>