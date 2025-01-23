<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<style type="text/css">
/* 	td,th { */
/* 		border: 1px solid black; */
/* 	} */
/* 	table{ */
/* 		border-collapse: collapse;  */
/* 		width: 80%; */
/* 		min-height: 500px; */
/* 	} */
/* 	.red{ */
/* 		background-color: red; */
/* 	} */
/* 	.yellow{ */
/* 		background-color: yellow; */
/* 	} */
/* 	.green{ */
/* 		background-color: green; */
/* 	} */
	
</style>
<h4>구구단(table)</h4>
<!-- 2단 ~ 9단까지 구구단 출력 (승수는 1~9까지...) -->
사용자가 2단부터 13까지의 구구단에서 범위(최소단~최대단)를 설정할 수 있음.
<form>
	 <input type="number" min="2" max="13" name="minDan" value="${gugudan.minDan }" />
	 <select name="maxDan">
	 	<c:forEach var="num" begin="2" end="13" varStatus="vs">
	 		<option value="${num }" ${num eq gugudan.maxDan ? "selected" : "" }>${num }단</option>	 	 		
	 	</c:forEach>
	 </select>
	<button type="submit">전송 </button>
</form>
<table class="table-bordered col-9 h-75">
	<thead>
		<tr>
			<th>몇단</th>
			<c:forEach var="cnt" begin="1" end="9">
				<th>승수 ${cnt }</th>
			</c:forEach>
		</tr>
	</thead>
	<tbody>	
		<c:forEach var="dan" begin="${gugudan.minDan }" end="${gugudan.maxDan }" step="1" varStatus="vs">
			<c:choose>
				<c:when test="${vs.first }">
					<c:set var="clzValue" value="bg-danger"></c:set>
				</c:when>
				<c:when test="${vs.last }">
					<c:set var="clzValue" value="bg-warning"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="clzValue" value="others"></c:set>
				</c:otherwise>
			</c:choose>
		
			<tr class="${clzValue }">
				<th>${dan}단</th>	
			<c:forEach var="mul" begin="1" end="9" >
				<td class="${mul eq 4 ? 'bg-success' : 'others'}">${dan} * ${mul} = ${dan * mul}</td>
			</c:forEach>
			</tr>
		</c:forEach>
	</tbody>
</table>
<!-- <script type="text/javascript">
	document.addEventListener("DOMContentLoaded", ()=>{
		$('[name="minDan"]').val(`${gugudan.minDan}`);
		$('[name=maxDan]').val("${gugudan.maxDan}");
	})
</script> -->