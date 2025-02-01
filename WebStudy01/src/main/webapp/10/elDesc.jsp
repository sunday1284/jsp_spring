<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="kr.or.ddit.servlet08.vo.FileInfoVO"%>
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
<h4>EL(Expression Language, 표현언어)</h4>
<pre>
	: 표현식의 대체로 사용되는 scope에 저장된 attribute 데이터를 출력하는 것을 목적으로 하는 DSL(특수목적어)
	: \${속성명 }
	<%
		String text = "텍스트값";
		/* scope 값이 있으면 el를 쓰는게 편리함 (attrName) */
		pageContext.setAttribute("attrName", text);
		FileInfoVO fileInfo = new FileInfoVO();		
		fileInfo.setOriginalFilename("원본파일명");
		fileInfo.setSavename("저장명");
		request.setAttribute("infoAttr", fileInfo);
		
		String[] array = new String[]{"value1", "value2"};
		List<String> list = Arrays.asList(array);
		Set<String> set = new HashSet<>(list);
		
		Map<String, Object> map = new HashMap<>();
		map.put("key1", "mapValue1");
		map.put("key2", "mapValue2");
		map.put("key-3", "mapValue3");
		
		session.setAttribute("arrayAttr", array);
		session.setAttribute("listAttr", list);
		session.setAttribute("setAttr", set);
		session.setAttribute("mapAttr", map);
	%>
	el : ${text }, 표현식 : <%=text %>
	el : ${attrName }, 표현식 : <%=pageContext.getAttribute("attrName") %>
	
	el 연산자 
	<!--  el연산자는 js처럼 동적타입 -> 자바처럼 피연산자 타입 필요x   -->
	산술연산 : ${3 + 4 }, ${3 * 4 }, ${3 / 4 }, ${ "3" + 4 }, ${dummy + 4 }
	논리연산 : &&(and), ||(or), !(not)
			${ true and true }, ${true and dummy }, ${false or "true" }, ${not dummy }
	비교연산 : ==(eq), !=(ne), >(gt), >=(ge), <(lt), <=(le)
			 ${3 lt 5 }, ${3 ne 5}
			 
	3항연산자 : 조건식?참의표현식:거짓의표현식
			${3 gt 5 ? "크다" : "작다"}
	단항연산자 : empty(전위연산자)
		${empty dummy}, ${not empty dummy}
		${not empty attrName ? attrName : "그런 속성 없음" }
		
	el 이용한 객체 구조 접근 (dot, notation : .property, associative array : ['propery'])
	${infoAttr.savename }, ${infoAttr['savename'] }
	
	el 이용한 집합객체의 요소 접근 
	${sessionScope.arrayAttr[1] }, ${sessionScope['arrayAttr'][3] }
	<c:forEach items="${arrayAttr }" var="element">
		${element }
	</c:forEach>
	
	
	${listAttr.get(0)}, ${listAttr[0] }
	${setAttr }
	<!-- 두번째 구조가 제일 안정적 -->
	${mapAttr.get("key2") }, ${mapAttr["key2"] }, ${mapAttr.key2 }
	${mapAttr.get("key-3") }, ${mapAttr["key-3"] }, ${mapAttr.key-3 }
	
	el 기본객체 
	<!-- el에서는 pageContext밖에 못씀 -->
	pageContext : jsp PageContext 객체. (현재 jsp 페이지에 대한 모든 정보를 가진 객체)
	context path : 	<%=request.getContextPath() %>, ${pageContext.request.contextPath }
	scope 객체 : pageScope, requestScope, sessionScope, applicationScope
	
</pre>
</body>
</html>