<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!-- section4 -->


session scope를 통해 공유한 방금전 업로드한 파일 ===  ${uploaded }

<ul class="list-group">
  	<c:forEach items="${fileList}" var="element">
  		<!-- 문자 비교 => eq 같으면 -->
  		<c:if test="${element eq uploaded}">
  			<li class="list-group-item active">
  			<c:remove var="uploaded" scope="session"/>
  		</c:if>
  		<c:if test="${element ne uploaded}">
  			<li class="list-group-item">
  		</c:if>
  			<c:url value="/files/download" var="downloadUrl">
  				<c:param name="target" value="${element.fileName}"/>
  				<c:param name="param1" value="value1"/>
  				<c:param name="param1" value="value2"/>
  			</c:url>
  			<a href="${downloadUrl}">${element.fileName}</a>
  			<span class ="btn btn-danger del-btn" data-filename="${element.fileName}">삭제</span>
  		</li>
  	</c:forEach>
</ul>
<!-- url value-> contextpath 생략가능  -->
<c:url value="/files/upload" var="uploadUrl"/>
<a href="${uploadUrl}" class="btn btn-primary">신규 파일 업로드</a>
<script>
	document.addEventListener("DOMContentLoaded", ()=>{
		console.log($);
		$(".del-btn").on("click", function() {
// 			1. 삭제 대상 파일명 확보 -> 연관 배열 구조
			let filename = this.dataset['filename'];
// 			2. 파일명이 있는지 검증, 검증에 통과하지 못하면, alert 으로 메시지 출력.
			if(!filename){		
				alert("파일명이 필요함.")		
				return false;
			}
// 			3. 통과한 경우, 삭제를 동기 요청을 전송(파일명을 파라미터로 전송)
			location.href=`${pageContext.request.contextPath}/files/delete?target=\${filename}`;
			
		});
	});
</script>






