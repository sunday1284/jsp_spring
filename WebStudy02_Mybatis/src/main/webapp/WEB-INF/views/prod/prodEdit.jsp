<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<form method="post" enctype="multipart/form-data">
	<input type="text" readonly name="prodId" value="${prod.prodId }"/>
	<div class="row mb-3">
		<label for="prodName" class="col-sm-2 col-form-label">상품명</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="prodName" id="prodName"
				value="${prod.prodName}"><span class="text-danger">${errors.prodName}</span>
		</div>
	</div>
	<div class="row mb-3">
		<label for="lprodGu" class="col-sm-2 col-form-label">분류코드</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="lprodGu" id="lprodGu"
				value="${prod.lprodGu}"><span class="text-danger">${errors.lprodGu}</span>
		</div>
	</div>
	<div class="row mb-3">
		<label for="buyerId" class="col-sm-2 col-form-label">제조사코드</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="buyerId" id="buyerId"
				value="${prod.buyerId}"><span class="text-danger">${errors.buyerId}</span>
		</div>
	</div>
	<div class="row mb-3">
		<label for="prodCost" class="col-sm-2 col-form-label">구매가</label>
		<div class="col-sm-10">
			<input type="number" class="form-control" name="prodCost"
				id="prodCost" value="${prod.prodCost}"><span
				class="text-danger">${errors.prodCost}</span>
		</div>
	</div>
	<div class="row mb-3">
		<label for="prodPrice" class="col-sm-2 col-form-label">판매가</label>
		<div class="col-sm-10">
			<input type="number" class="form-control" name="prodPrice"
				id="prodPrice" value="${prod.prodPrice}"><span
				class="text-danger">${errors.prodPrice}</span>
		</div>
	</div>
	<div class="row mb-3">
		<label for="prodSale" class="col-sm-2 col-form-label">세일가</label>
		<div class="col-sm-10">
			<input type="number" class="form-control" name="prodSale"
				id="prodSale" value="${prod.prodSale}"><span
				class="text-danger">${errors.prodSale}</span>
		</div>
	</div>
	<div class="row mb-3">
		<label for="prodOutline" class="col-sm-2 col-form-label">요약정보</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="prodOutline"
				id="prodOutline" value="${prod.prodOutline}"><span
				class="text-danger">${errors.prodOutline}</span>
		</div>
	</div>
	<div class="row mb-3">
		<label for="prodDetail" class="col-sm-2 col-form-label">상세정보</label>
		<div class="col-sm-10">
			<input type="clob" class="form-control" name="prodDetail"
				id="prodDetail" value="${prod.prodDetail}"><span
				class="text-danger">${errors.prodDetail}</span>
		</div>
	</div>
	<div class="row mb-3">
		<label for="prodImg" class="col-sm-2 col-form-label">이미지</label>
		<div class="col-sm-10">
			<input type="file" name="prodImage" />
			<span class="text-danger">${errors.prodImg}</span>
		</div>
	</div>
	<div class="row mb-3">
		<label for="prodTotalstock" class="col-sm-2 col-form-label">총재고</label>
		<div class="col-sm-10">
			<input type="number" class="form-control" name="prodTotalstock"
				id="prodTotalstock" value="${prod.prodTotalstock}"><span
				class="text-danger">${errors.prodTotalstock}</span>
		</div>
	</div>
	<div class="row mb-3">
		<label for="prodProperstock" class="col-sm-2 col-form-label">적정재고</label>
		<div class="col-sm-10">
			<input type="number" class="form-control" name="prodProperstock"
				id="prodProperstock" value="${prod.prodProperstock}"><span
				class="text-danger">${errors.prodProperstock}</span>
		</div>
	</div>
	<div class="row mb-3">
		<label for="prodSize" class="col-sm-2 col-form-label">크기</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="prodSize" id="prodSize"
				value="${prod.prodSize}"><span class="text-danger">${errors.prodSize}</span>
		</div>
	</div>
	<div class="row mb-3">
		<label for="prodColor" class="col-sm-2 col-form-label">색상</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="prodColor"
				id="prodColor" value="${prod.prodColor}"><span
				class="text-danger">${errors.prodColor}</span>
		</div>
	</div>
	<div class="row mb-3">
		<label for="prodDelivery" class="col-sm-2 col-form-label">배송방법</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="prodDelivery"
				id="prodDelivery" value="${prod.prodDelivery}"><span
				class="text-danger">${errors.prodDelivery}</span>
		</div>
	</div>
	<div class="row mb-3">
		<label for="prodUnit" class="col-sm-2 col-form-label">단위</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="prodUnit" id="prodUnit"
				value="${prod.prodUnit}"><span class="text-danger">${errors.prodUnit}</span>
		</div>
	</div>
	<div class="row mb-3">
		<label for="prodQtyin" class="col-sm-2 col-form-label">입고량</label>
		<div class="col-sm-10">
			<input type="number" class="form-control" name="prodQtyin"
				id="prodQtyin" value="${prod.prodQtyin}"><span
				class="text-danger">${errors.prodQtyin}</span>
		</div>
	</div>
	<div class="row mb-3">
		<label for="prodQtysale" class="col-sm-2 col-form-label">출고량</label>
		<div class="col-sm-10">
			<input type="number" class="form-control" name="prodQtysale"
				id="prodQtysale" value="${prod.prodQtysale}"><span
				class="text-danger">${errors.prodQtysale}</span>
		</div>
	</div>
	<div class="row mb-3">
		<label for="prodMileage" class="col-sm-2 col-form-label">마일리지</label>
		<div class="col-sm-10">
			<input type="number" class="form-control" name="prodMileage"
				id="prodMileage" value="${prod.prodMileage}"><span
				class="text-danger">${errors.prodMileage}</span>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col-sm-10">
			<button type="submit" class="btn btn-primary">저장</button>
		</div>
	</div>
</form>
<script>
	document.addEventListener("DOMContentLoaded", ()=>{
		const $buyerId = $('[name="buyerId"]');
		$('[name="lprodGu"]').on("change", function(){
			// 'P101'
			let selectedLprod = $(this).val();
			$buyerId.find("option").hide();
			$buyerId.find("option:first").show();
			// 'P101', 'P10101', 'P10102'
			if(selectedLprod)
				$buyerId.find(`option[value^=\${selectedLprod}]`).show();
			else
				$buyerId.find("option").show();
		});
	});
</script>


















