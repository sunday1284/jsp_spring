<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<style type="text/css">
	tr[data-prod-id]{
		cursor: pointer;
	}
</style>
<table class="table table-bordered" data-detail-url="<c:url value='/prod/prodDetail.do'/>">
	<thead class="table-dark">
		<tr>
			<th>일련번호</th>
			<th>상품명</th>
			<th>상품분류명</th>
			<th>제조사명</th>
			<th>구매가</th>
			<th>판매가</th>
			<th>총재고량</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${not empty prodList}">
			<c:forEach items="${prodList}" var="prod">
				<tr data-prod-id="${prod.prodId}">
					<td>${prod.rnum }</td>
					<td>${prod.prodName}</td>
					<td>${prod.lprod.lprodNm}</td>
					<td>${prod.buyer.buyerName}</td>
					<td>${prod.prodCost}</td>
					<td>${prod.prodPrice}</td>
					<td>${prod.prodTotalstock}</td>
					<td>${prod.prodMileage}</td>
				</tr>
			</c:forEach>
			
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="8">
					상품 없음.
				</td>
			</tr>
		</c:otherwise>
	</c:choose>	
	</tbody>
	<tfoot>
		<tr>
			<td colspan="8">
				${pagingHTML }	
				<!-- 입력용 -->
				<div id="search-ui">
					<select name="searchType" data-init-value="${condition.searchType}">
						<option value>전체</option>
						<option value="lpord">상품분류</option>
						<option value="buyer">제조사</option>
						<option value="name" >상품명</option>
					</select>
					<input type="text" name="searchWord" value="${condition.searchWord}"/>
					<button type="button" id="search-btn">검색</button>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<!-- 전송용 -->
<form id="search-form">
	<input type="text" name="page"/>
	<input type="text" name="searchType" value="${condition.searchType }"/>
	<input type="text" name="searchWord" value="${condition.searchWord }"/>
</form>
<script src="${pageContext.request.contextPath }/resources/js/prod/prodList.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/common/paging.js"></script>