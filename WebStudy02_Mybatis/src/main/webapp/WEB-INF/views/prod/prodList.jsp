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
					<select name="lprodGu" data-init-value="${condition.lprodGu }">
						<option value>상품분류</option>
						<c:forEach items="${lprodList}" var="lprod">
							<option value="${lprod.lprodGu}">${lprod.lprodNm }</option>
						</c:forEach>
					</select>
					<select name="buyerId" data-init-value="${condition.buyerId }">
						<option value>제조사선택</option>
						<c:forEach items="${buyerList}" var="buyer">
							<option value="${buyer.buyerId }">${buyer.buyerName }</option>
						</c:forEach>
					</select>
					<input type="text" name="prodName" placeholder="상품명" value="${condition.prodName }"/>
					<button type="button" id="search-btn">상세검색</button>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<!-- 전송용 -->
<form id="search-form">
	<input type="text" name="page"/>
	<input type="text" name="lprodGu" value="${condition.lprodGu }"/>
	<input type="text" name="buyerId" value="${condition.buyerId }"/>
	<input type="text" name="prodName" value="${condition.prodName }"/>
</form>
<script src="${pageContext.request.contextPath }/resources/js/prod/prodList.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/common/paging.js"></script>