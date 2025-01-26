<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap-5.3.3-dist/css/bootstrap.min.css">
</head>
<body>
<form action="${pageContext.request.contextPath}/mbti/new" method="post"
enctype="application/x-www-form-urlencoded">
<ul>
	<li>
	식별 번호 : <input class="form-control" type="text" name="mtSort" required />
	</li>
	<li>
	MBTI 타입 : <input class="form-control" type="text" name="mtType"  required />
	</li>
	<li>
	MBTI 유형 : <input class="form-control" type="text" name="mtTitle" required />
	</li>
	<li>
	MBTI 성격 : <textarea class="form-control" class="form-control" name="mtContent" cols="100" rows="8"><c:out value= "${mtContent }"/></textarea>
	</li>
	<li>
		<button type="submit">추가</button>
		<button type="reset">되돌리기</button>
	</li>
</ul>
</form>

</body>
</html>