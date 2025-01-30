<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core"  prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>MBTI 입력</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap-5.3.3-dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4 text-center">MBTI 정보 입력</h2>
    <form action="${pageContext.request.contextPath}/mbti/new" method="post"
          enctype="application/x-www-form-urlencoded" class="p-4 border rounded bg-light shadow">
        
        <div class="mb-3">
            <label for="mtSort" class="form-label">식별 번호</label>
            <input type="text" id="mtSort" name="mtSort" class="form-control" required />
        </div>

        <div class="mb-3">
            <label for="mtType" class="form-label">MBTI 타입</label>
            <input type="text" id="mtType" name="mtType" class="form-control" required />
        </div>

        <div class="mb-3">
            <label for="mtTitle" class="form-label">MBTI 유형</label>
            <input type="text" id="mtTitle" name="mtTitle" class="form-control" required />
        </div>

        <div class="mb-3">
            <label for="mtContent" class="form-label">MBTI 성격</label>
            <textarea id="mtContent" name="mtContent" class="form-control" rows="6">
                <c:out value="${mtContent}"/>
            </textarea>
        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-primary">추가</button>
            <button type="reset" class="btn btn-secondary">되돌리기</button>
        </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/resources/bootstrap-5.3.3-dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
