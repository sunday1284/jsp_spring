<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
/* 리팩토링 작업 */
private long factorial(int operand){
	if(operand <= 0){
		// 예외를 직접 만듦
		throw new IllegalArgumentException("팩토리얼 연산은 양의 정수만 처리 가능.");
	}
	if(operand == 1){
		return 1;
	}else{
		return operand * factorial(operand - 1);		
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
// 	10! 
// 	10 * 9!
// 		 9 * 8!
// 		 	.. 
// 		 		2 * 1!
	int operand = 10;
	int result = 1;
	for(int num = operand; num >= 1; num--){
		result = result * num;
	}
%>
<body>
<h4> 10! = <%=result %></h4>
<h4> 10! = <%=factorial(operand) %></h4>
</body>
</html>