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
				<td colspan="7">
					상품 없음.
				</td>
			</tr>
		</c:otherwise>
	</c:choose>
		
		
	</tbody>
</table>

<script src="${pageContext.request.contextPath }/resources/js/prod/prodList.js"></script>