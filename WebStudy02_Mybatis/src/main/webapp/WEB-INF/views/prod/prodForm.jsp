<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form method="post" enctype="multipart/form-data">
   <div class="row mb-3">
      <label for="prodName" class="col-sm-2 col-form-label">상품명</label>
      <div class="col-sm-10">
        <input name="prodName" type="text" class="form-control">
      </div>
    </div>
    <div class="row mb-3">
      <label for="lprodGu" class="col-sm-2 col-form-label">분류코드</label>
      <div class="col-sm-10">
        <select name="lprodGu" data-init-value="${codition.lprodGu }" class="form-control">
         <option value>상품분류</option>
         <c:forEach items="${lprodList }" var="lprod"> 
            <option value="${lprod.lprodGu }">${lprod.lprodNm }</option>
         </c:forEach>
      </select>
      </div>
    </div>
    <div class="row mb-3">
      <label for="buyerId" class="col-sm-2 col-form-label">제조사코드</label>
      <div class="col-sm-10">
        <select name="buyerId" data-init-value="${codition.buyerId }" class="form-control">
         <option value>제조사코드</option>
         <c:forEach items="${buyerList }" var="buyer"> 
            <option value="${buyer.buyerId }">${buyer.buyerName }</option>
         </c:forEach>
      </select>
      </div>
    </div>
    <div class="row mb-3">
      <label for="prodCost" class="col-sm-2 col-form-label">구매가</label>
      <div class="col-sm-10">
        <input name="prodCost" type="number" class="form-control">
      </div>
    </div>
    <div class="row mb-3">
      <label for="prodPrice" class="col-sm-2 col-form-label">판매가</label>
      <div class="col-sm-10">
        <input name="prodPrice" type="number" class="form-control">
      </div>
    </div>
    <div class="row mb-3">
      <label for="prodSale" class="col-sm-2 col-form-label">세일가</label>
      <div class="col-sm-10">
        <input name="prodSale" type="number" class="form-control">
      </div>
    </div>
    <div class="row mb-3">
      <label for="prodOutline" class="col-sm-2 col-form-label">요약정보</label>
      <div class="col-sm-10">
        <input name="prodOutline" type="text" class="form-control">
      </div>
    </div>
    <div class="row mb-3">
      <label for="prodDetail" class="col-sm-2 col-form-label">상세정보</label>
      <div class="col-sm-10">
        <input name="prodDetail" type="text" class="form-control">
      </div>
    </div>
    <div class="row mb-3">
      <label for="prodImage" class="col-sm-2 col-form-label">업로드이미지</label>
      <div class="col-sm-10">
      
        <input type="file" name="prodImage"  id="prodImage" class="form-control">
        
      </div>
    </div>
    <div class="row mb-3">
      <label for="prodTotalstock" class="col-sm-2 col-form-label">총재고</label>
      <div class="col-sm-10">
        <input name="prodTotalstock" type="number" class="form-control">
      </div>
    </div>

    <div class="row mb-3">
      <label for="prodProperstock" class="col-sm-2 col-form-label">적정재고</label>
      <div class="col-sm-10">
        <input name="prodProperstock" type="number" class="form-control">
      </div>
    </div>
    <div class="row mb-3">
      <label for="prodSize" class="col-sm-2 col-form-label">크기</label>
      <div class="col-sm-10">
        <input name="prodSize" type="text" class="form-control">
      </div>
    </div>
    <div class="row mb-3">
      <label for="prodColor" class="col-sm-2 col-form-label">색상</label>
      <div class="col-sm-10">
        <input name="prodColor" type="text" class="form-control">
      </div>
    </div>
    <div class="row mb-3">
      <label for="prodDelivery" class="col-sm-2 col-form-label">배송방법</label>
      <div class="col-sm-10">
        <input name="prodDelivery" type="text" class="form-control">
      </div>
    </div>
    <div class="row mb-3">
      <label for="prodUnit" class="col-sm-2 col-form-label">단위</label>
      <div class="col-sm-10">
        <input name="prodUnit" type="text" class="form-control">
      </div>
    </div>
    <div class="row mb-3">
      <label for="prodQtyin" class="col-sm-2 col-form-label">입고량</label>
      <div class="col-sm-10">
        <input name="prodQtyin" type="number" class="form-control">
      </div>
    </div>
    <div class="row mb-3">
      <label for="prodQtysale" class="col-sm-2 col-form-label">출고량</label>
      <div class="col-sm-10">
        <input name="prodQtysale" type="number" class="form-control">
      </div>
    </div>
    <div class="row mb-3">
      <label for="prodMileage" class="col-sm-2 col-form-label">마일리지</label>
      <div class="col-sm-10">
        <input name="prodMileage" type="number" class="form-control">
      </div>
    </div>
    <div class="row mb-3">
      <div class="col-sm-10">
          <button type="submit" class="btn btn-primary">저장</button>
      </div>
    </div>
</form>

<script type="text/javascript">
   document.addEventListener("DOMContentLoaded", ()=>{
      const $buyerId = $('[name="buyerId"]');
      $('[name="lprodGu"]').on("change",function(){
         // 'P101'
         let selectedLprod = $(this).val();
         $buyerId.find("option").hide();
         $buyerId.find("option:first").show();
         // 'P101' , 'P10101', 'P10102'
         $buyerId.find(`option[value^=\${selectedLprod}]`).show();
      })
   })
</script>