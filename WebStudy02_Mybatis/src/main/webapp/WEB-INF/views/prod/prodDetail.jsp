<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<table class="table table-bordered">
	<tr>
		<td colspan="2">
			<!-- go -> index 값으로 가고싶은 경로 지정 -->
			<button onclick="history.go(-1);">뒤로가기</button>
			<c:url value='/prod/prodUpdate.do' var="updateUrl">
				<c:param name="what" value="${prod.prodId}"/>
			</c:url>
			
			<a class="btn-btn-primary" href="${updateUrl}">상품 수정</a>
		</td>
	</tr>
	<tr>
		<th>상품코드</th>
		<td>${prod.prodId}</td>
	</tr>
	<tr>
		<th>상품명</th>
		<td>${prod.prodName}</td>
	</tr>
	<tr>
		<th>분류코드</th>
		<td>${prod.lprodGu}</td>
	</tr>
	<tr>
		<th>제조사코드</th>
		<td>${prod.buyerId}</td>
	</tr>
	<tr>
		<th>구매가</th>
		<td>${prod.prodCost}</td>
	</tr>
	<tr>
		<th>판매가</th>
		<td>${prod.prodPrice}</td>
	</tr>
	<tr>
		<th>세일가</th>
		<td>${prod.prodSale}</td>
	</tr>
	<tr>
		<th>요약정보</th>
		<td>${prod.prodOutline}</td>
	</tr>
	<tr>
		<th>상세정보</th>
		<td>${prod.prodDetail}</td>
	</tr>
	<tr>
		<th>이미지</th>
		<td>
			<img src="<c:url value='/resources/prodImages/${prod.prodImg}'/>" alt="" />
		</td>
	</tr>
	<tr>
		<th>총재고</th>
		<td>${prod.prodTotalstock}</td>
	</tr>
	<tr>
		<th>입고일</th>
		<td>${prod.prodInsdate}</td>
	</tr>
	<tr>
		<th>적정재고</th>
		<td>${prod.prodProperstock}</td>
	</tr>
	<tr>
		<th>크기</th>
		<td>${prod.prodSize}</td>
	</tr>
	<tr>
		<th>색상</th>
		<td>${prod.prodColor}</td>
	</tr>
	<tr>
		<th>배송방법</th>
		<td>${prod.prodDelivery}</td>
	</tr>
	<tr>
		<th>단위</th>
		<td>${prod.prodUnit}</td>
	</tr>
	<tr>
		<th>입고량</th>
		<td>${prod.prodQtyin}</td>
	</tr>
	<tr>
		<th>출고량</th>
		<td>${prod.prodQtysale}</td>
	</tr>
	<tr>
		<th>마일리지</th>
		<td>${prod.prodMileage}</td>
	</tr>


</table>
