<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- ${memberList} --%>
<table class = "col-10 table-bordered">
    <thead>
        <tr>
            <th>회원명</th>
            <th>이메일</th>
            <th>휴대폰</th>
            <th>거주지역</th>
            <th>마일리지</th>
        </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test ="${not empty memberList}">
            <c:forEach items="${memberList}" var="member">
                <tr>
                    <td>
                        <c:url var="detailUrl" value="/member/memberDetail.do" >
                            <c:param name="who" value="${member.memId}"/>
                        </c:url>
                        <a href="${detailUrl}" data-bs-toggle="modal" data-bs-target="#exampleModal">${member.memName}</a>
                    </td>
                    <td>${member.memMail }</td>
                    <td>${member.memHp }</td>
                    <td>${member.memAdd1 }</td>
                    <td>${member.memMileage }</td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="5">
                    회원이 없음.
                </td>
            </tr>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/member/memberList.js"></script>