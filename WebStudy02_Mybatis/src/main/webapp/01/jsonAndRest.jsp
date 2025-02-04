<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>JSON(JavaScriptObjectNotation)</h4>
<pre>
	: 경량의 메시지 교환 형식 
	: 데이터(메시지)의 표현방식이 서로 다른 이기종 시스템간의 메시지를 교환하기 위해 공통 메시지 표현방식이 필요함 
		--> xml, json <name>값</name>, {"name":값}  
	주로, REST 구조에서 활용됨.
	
	: 네트워크 상에서 client 와 server 가 자원의 상태를 있는 그대로의 원형 메시지로 주고받는 서비스 구조.
	
	
	REST 구성 요소 
	1. 자원의 식별자 (명사) : URI(URL)
	2. 자원을 대상으로 한 행위 (동사) : method(POST/GET/PUT/DELETE)
	3. 자원의 상태(데이터) : JSON
	4. 자원의 메타데이터 : Content-Type
	
	RestFul (REST 스럽다.)
	RestFul API
	RestFul URI 
												동기x
	/member/memberList.do 		, 			/member (GET) 
	/member/memberDetail.do?who=a001, 		/member/a001 (GET) 
	/member/memberUpdate.do ,				/member/a001 (PUT)
	/member/memberDelete.do?who=a001, 		/member/a001 (DELETE)
	/member/memberInsert.do ,				/member/new (GET), /member (POST)
	
</pre>
</body>
</html>