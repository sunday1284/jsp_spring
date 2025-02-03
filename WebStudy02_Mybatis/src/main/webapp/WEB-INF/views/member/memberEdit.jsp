<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>


<form method="post">
	<table class="table">
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="memPass" class="form-control"/>
				<span class="text-danger">${errors.memPass}</span></td>
		</tr>
		<tr>
			<th>회원명</th>
			<td><input type="text" name="memName" class="form-control"
				value="${member.memName}" /><span class="text-danger">${errors.memName}</span></td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td><input type="text" name="memRegno1" class="form-control"
				value="${member.memRegno1}" /><span class="text-danger">${errors.memRegno1}</span></td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td><input type="text" name="memRegno2" class="form-control"
				value="${member.memRegno2}" /><span class="text-danger">${errors.memRegno2}</span></td>
		</tr>
		<tr>
			<th>생일</th>
			<td><input type="date" name="memBir" class="form-control"
				value="${member.memBir}" /><span class="text-danger">${errors.memBir}</span></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><input type="text" name="memZip" class="form-control"
				value="${member.memZip}" /><span class="text-danger">${errors.memZip}</span></td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><input type="text" name="memAdd1" class="form-control"
				value="${member.memAdd1}" /><span class="text-danger">${errors.memAdd1}</span></td>
		</tr>
		<tr>
			<th>주소2</th>
			<td><input type="text" name="memAdd2" class="form-control"
				value="${member.memAdd2}" /><span class="text-danger">${errors.memAdd2}</span></td>
		</tr>
		<tr>
			<th>집전화번호</th>
			<td><input type="text" name="memHometel" class="form-control"
				value="${member.memHometel}" /><span class="text-danger">${errors.memHometel}</span></td>
		</tr>
		<tr>
			<th>회사번호</th>
			<td><input type="text" name="memComtel" class="form-control"
				value="${member.memComtel}" /><span class="text-danger">${errors.memComtel}</span></td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td><input type="text" name="memHp" class="form-control"
				value="${member.memHp}" /><span class="text-danger">${errors.memHp}</span></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" name="memMail" class="form-control"
				value="${member.memMail}" /><span class="text-danger">${errors.memMail}</span></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><input type="text" name="memJob" class="form-control"
				value="${member.memJob}" /><span class="text-danger">${errors.memJob}</span></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><input type="text" name="memLike" class="form-control"
				value="${member.memLike}" /><span class="text-danger">${errors.memLike}</span></td>
		</tr>
		<tr>
			<th>기념일</th>
			<td><input type="text" name="memMemorial" class="form-control"
				value="${member.memMemorial}" /><span class="text-danger">${errors.memMemorial}</span></td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td><input type="date" name="memMemorialday"
				class="form-control" value="${member.memMemorialday}" /><span
				class="text-danger">${errors.memMemorialday}</span></td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit" class="btn btn-primary" >전송</button>
			</td>
		</tr>
</form>
<!-- 프롬프트 예시 오라클의 "COLS", "USER_COL_COMMENTS" 의 스키마 뷰로 SELECT 쿼리를 만들어줘 ##레코드
조회 결과 예시##
<tr>
	<th>회원아이디</th>
	<td><input type="text" name="memId" class="form-control" /></td>
</tr> -->