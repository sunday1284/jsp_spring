<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> Http Response packaging </h4>
<pre>
Http : Connect Less, State Less
	Connect Full : 의도적인 연결 수립과 의도적인 연결 종료가 가능한 형태.
	Connect Less : 한번의 연결이 수립되고, 자동으로 해당 연결이 종료되는 형태.
	State Less(무상태 특성) : Cookie와 Session을 통해 상태를 유지할 수도 있음.
1. Response Line : Status Code(응답 상태 코드)
	1xx : ING..., Connect full 구조로 동작할 수 있는 WebSocket 프로토콜에서 활용.
	2xx : OK, SUCCESS 
	3xx : response body가 없고, 클라이언트의 추가 액션이 필요함.
		301 / 302 / 307 : MOVED(Location 헤더와 함께 사용됨.)
			->location 방향으로 새로운 요청(redirect 요청)이 전송됨.
		304 : Not Modified 브라우저의 캐싱 특성과 연관된 상태코드로 
			  캐싱된 이후 자원이 갱신된적 없음을 표현함.
	4xx : FAIL , 클라이언트측 문제로 인한 실패.
		404 : HttpServletResponse.SC_NOT_FOUND
		405	: HttpServletResponse.SC_METHOD_NOT_ALLOWED
		400 : HttpServletResponse.SC_BAD_REQUEST, 
				요청 검증에 활용되는 상태코드.
				필수 파라미터 누락, 요청 데이터 타입 오류, 요청 데이터 길이 제한 오류...
		401(HttpServletResponse.SC_UNAUTHORIZED) : 보안처리 (보호자원에 접근하기 위해 인증이 필요한 경우)
		403(HttpServletResponse.SC_FORBIDDEN) : 보안처리 (보호자원에 접근하는 인증된 사용자가 권한이 없음.)
		406 : HttpServletResponse.SC_NOT_ACCEPTABLE, 
				클라이언트가 accept 헤더를 통해 요청한 컨텐츠를 처리할 수 없음.
		415 : HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE 
				클라이언트가 body를 통해 전송한 컨텐츠를 해석할 수 없음. 
	5xx : FAIL , 서버측 문제로 인한 실패. 보안상의 이유로 상태코드 500 을 주로 사용함.
		500 ( Internal Server Error )
2. Response Header : response body content를 표현할 수 있는 meta data
	Content-Type
	Content-Length 
3. Response body (content Body, Message Body) : 서버가 클라이언트에게 제공할 컨텐츠 전송 영역.
</pre>
</body>
</html>