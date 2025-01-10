<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 알바생의 정보 등록 -->
<!-- 이름, 전번, 이메일, 아이디, 경력, 성별, 나이, 생일, 비밀번호,  거주지, 학력, 취미-->
<!-- enctype -> post로 보낼 때 contentType 결정 -->
<form action="<%=request.getContextPath() %>/08/formProcess.do" 
	method="post" enctype="application/x-www-form-urlencoded">
<ul>
<li>
	아이디 : <input type="text" name="id" required placeholder="아이디"/>
</li>
<li>
	비밀번호 : <input type="password" name="password" required placeholder="비밀번호4자리 이상"/>
</li>
<li>
	이름 : <input type="text" name="name" required placeholder="이름"/>
</li>
<li>
	나이 : <input type="number" name="age" required placeholder="나이"/>
</li>
<li>
	생일 : <input type="date" name="birth" placeholder="생일"/>
</li>
<li>
	성별 : 
	<label><input type="radio" name="gender" value="F"/>여</label>
	<input type="radio" id="gender-M" name="gender" value="M"/>
	<label for="gender-M">남</label>
</li>
<li>
	이메일 : <input type="email" name="email" placeholder="메일주소"/>
</li>
<li>
	학력 : 
		<select name="grade" required>
			<option value>학력선택</option>
			<option label="고졸" value="G001"/>
			<option label="대재" value="G002"/>
			<option label="대졸" value="G003"/>
			<option label="석사" value="G004"/>	
			<option value="G005">박사</option>
		</select>
</li>
<li>
	주소 : <input type="text" name="address" placeholder="주소" value="기본 주소"/>
</li>
<li>
	휴대폰 : <input type="text" pattern="\d{3}-\d{3,4}-\d{4}" name="hp" placeholder="휴대폰번호">
</li>
<li>
	<!-- textarea은 기본값을 이렇게 준다. -->
	경력사항 : <textarea name="career">경력사항 기본값</textarea>
</li>
<li>
	취미 : 
	<label><input type="checkbox" name="hobby" value="coding"/>코딩</label>
	<label><input type="checkbox" name="hobby" value="programming"/>프로그래밍</label>
	<label><input type="checkbox" name="hobby" value="development"/>개발</label>
</li>
<li>
	<button type="submit">전송</button>
	<button type="reset">취소</button>
</li>
</ul>
</form>
</body>
</html>