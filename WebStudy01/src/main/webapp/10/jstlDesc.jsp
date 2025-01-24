<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>JSTL(Java Standard Tag Library)</h4>
<pre>
	: 많이 활용되는 커스텀 태그를 표준화시켜놓은 라이브러리
	
	사용단계
	1. maven dependency 추가 : jstl-api, jstl 
	2. taglib 지시자로 태그 라이브러리 로딩 (uri, prefix )
	3. 커스텀 태그로 사용.
		&gt;prefix:tagName 속성명="속성값"... &lt;
		
		코어 태그의 종류 
		1) 속성 관리용 : set, remove 
		
		<c:set var="dummy" scope="session" value="더미" />
		${dummy }, ${sessionScope.dummy }
<!-- 		flash attribute : 저장한 속성을 한번 사용하고 삭제하는 구조.  -->
		<c:remove var="dummy" scope="request"/>
		지운 이후 --> ${dummy }, ${sessionScope.dummy }
		2) 조건문 : 
			단일조건문 : if 
			<c:if test="${empty dummy }">
				dummy 속성 삭제 됐음.
			</c:if>
			<c:if test="${not empty dummy }">
				dummy 속성 삭제한 적 없음.
			</c:if>
			다중조건문 : choose(switch) when(case) otherwise(default)
			<c:choose>
					<c:when test="${empty dummy }"></c:when>
					<c:otherwise>
						dummy 속성 삭제한 적 없음.
					</c:otherwise>
			</c:choose>
		3) 반복문 : forEach, forTokens
			forEach
				일반 for(선언절(초기값);조건절(종료);증감절) -> i를 1부터 10까지 i++
				<c:forEach var="i" begin="1" end="10" step="2" varStatus="vs">
					<c:if test="${vs.first }">
					-------------------------------
					</c:if>
						${i }, count : ${vs.count}
					<c:if test="${vs.last }">
					===============================
					</c:if>
				</c:forEach>
				향상 for(요소에 접근할 블럭 변수 : 집합객체 )
					<c:forEach items="<%=Arrays.asList(1,2,3) %>" var="num">
						${num }
					</c:forEach>
			forTokens :문장을 구성하는 요소
				<c:forTokens items="1,2,3,4,5" delims="," var="token">
					${token * 1000}
				</c:forTokens>
				inti=3; 
				
				int i = 3;
				아버지가방에들어가신다
				아버지 가방에 들어가신다
				아버지가 방에 들어가신다
				12345
				1 2 3 4 5 
		4) 기타 : url(Param), redirect
			url : origin이 생략된, 절대 경로 생성.
				param 태그와 병행하여, 쿼리 스트링 생성.
			<c:url value="/files/download" var="dummyUrl" scope="application">
				<c:param name="p1" value="v1"/>
				<c:param name="p2" value="v2"/>
			</c:url>
			<a href="${applicationScope.dummyUrl }">다운로드</a>
			<c:redirect url="/files" />
		
</pre>
</body>
</html>