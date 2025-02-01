<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>
var contextPath = '${pageContext.request.contextPath}';
document.addEventListener("DOMContentLoaded", function(){
    $(document).ready(function() {
        function loadParticipants() {
            $.get(contextPath + '/personList.do', function(data) {
                console.log("받은 데이터:", data); 
                var content = '<h2>참가자 목록</h2><table class="table"><tr><th>ID</th><th>이름</th><th>성별</th><th>나이</th><th>주소</th><th>작업</th></tr>';
                if (Array.isArray(data) && data.length > 0) {
                    data.forEach(function(person) {
                        content += '<tr>' +
                            '<td>' + person.id + '</td>' +
                            '<td>' + person.name + '</td>' +
                            '<td>' + person.gender + '</td>' +
                            '<td>' + person.age + '</td>' +
                            '<td>' + person.address + '</td>' +
                            '<td>' +
                                '<button type="button" class="btn btn-info" onclick="viewParticipant(\'' + person.id + '\')">보기</button>' +
                                '<button type="button" class="btn btn-info" onclick="deleteParticipant(\'' + person.id + '\')">삭제</button>' +
                            '</td>' +
                        '</tr>';
                    });
                } else {
                    content += '<tr><td colspan="6">참가자가 없습니다.</td></tr>';
                }
                content += '</table>';
                content += '<button type="button" class="btn btn-secondary" onclick="showInsertForm()">새 참가자 추가</button>';
                $('#content').html(content);
            }).fail(function() {
                alert('참가자 목록을 불러오는 데 실패했습니다.');
            });
        }

        window.viewParticipant = function(id) {
            $.get(contextPath + '/person.do?who=' + id, function(person) {
                console.log("참가자 상세 정보:", person); // 디버깅용 콘솔 로그 추가
                var content = '<h2>참가자 상세 정보</h2>' +
	                '<p class="mb-2"><strong>ID:</strong> ' + person.id + '</p>' +
	                '<p class="mb-2"><strong>이름:</strong> ' + person.name + '</p>' +
	                '<p class="mb-2"><strong>성별:</strong> ' + person.gender + '</p>' +
	                '<p class="mb-2"><strong>나이:</strong> ' + person.age + '</p>' +
	                '<p class="mb-2"><strong>주소:</strong> ' + person.address + '</p>' +
                    '<button type="button" class="btn btn-secondary" onclick="showUpdateForm(\'' + person.id + '\')">수정</button>' +
                    '<button type="button" class="btn btn-secondary" onclick="goBackToList()">목록으로 돌아가기</button>';
                $('#content').html(content);
            }).fail(function() {
                alert('참가자 상세 정보를 불러오는 데 실패했습니다.');
            });
        }
        
        window.goBackToList = function() {
            // 'personList.do' 페이지로 리디렉션 (새로 고침)
            window.location.href = contextPath + '/index.do';  // 여기에 contextPath를 포함해서 전체 URL로 이동
        }


        window.showInsertForm = function() {
            var content = '<h2>새 참가자 추가</h2>' +
                '<form id="insertForm">' +
                    '<p>ID: <input class="form-control" id="disabledInput" type="text" name="id"></p>' +
                    '<p>이름: <input class="form-control" id="disabledInput" type="text" name="name"></p>' +
                    '<p>성별: <input class="form-control" id="disabledInput" type="text" name="gender"></p>' +
                    '<p>나이: <input class="form-control" id="disabledInput" type="text" name="age"></p>' +
                    '<p>주소: <input  class="form-control" id="disabledInput" type="text" name="address"></p>' +
                    '<button class="btn btn-secondary" type="submit">추가</button>' +
                '</form>' +
                '<button type="button" class="btn btn-secondary" onclick="goBackToList()">목록으로 돌아가기</button>';
            $('#content').html(content);

            $('#insertForm').submit(function(event) {
                event.preventDefault();
                $.post(contextPath + '/personInsert.do', $(this).serialize(), function() {
                    loadParticipants();
                }).fail(function() {
                    alert('참가자를 추가하는 데 실패했습니다.');
                });
            });
        }

        window.showUpdateForm = function(id) {
            $.get(contextPath + '/person.do?who=' + id, function(person) {
                console.log("수정할 참가자:", person); // 디버깅용 콘솔 로그 추가
                var content = '<h2>참가자 수정</h2>' +
                    '<form id="updateForm">' +
                        '<input type="hidden" name="id" value="' + person.id + '">' +
                        '<p>이름: <input class="form-control" id="disabledInput" type="text" name="name" value="' + person.name + '"></p>' +
                        '<p>성별: <input class="form-control" id="disabledInput" type="text" name="gender" value="' + person.gender + '"></p>' +
                        '<p>나이: <input class="form-control" id="disabledInput" type="text" name="age" value="' + person.age + '"></p>' +
                        '<p>주소: <input class="form-control" id="disabledInput" type="text" name="address" value="' + person.address + '"></p>' +
                        '<button class="btn btn-secondary" type="submit">수정</button>' +
                    '</form>' +
                    '<button type="button" class="btn btn-secondary" onclick="goBackToList()">목록으로 돌아가기</button>';
                $('#content').html(content);

                $('#updateForm').submit(function(event) {
                    event.preventDefault();
                    $.post(contextPath + '/personUpdate.do', $(this).serialize(), function() {
                        loadParticipants();
                    }).fail(function() {
                        alert('참가자 수정에 실패했습니다.');
                    });
                });
            }).fail(function() {
                alert('참가자 정보를 불러오는 데 실패했습니다.');
            });
        }

        window.deleteParticipant = function(id) {
            if (confirm('정말로 이 참가자를 삭제하시겠습니까?')) {
                $.post(contextPath + '/personDelete.do', { who: id }, function() {
                    loadParticipants(); //삭제 후 바로 로드 
                }).fail(function() {
                    alert('참가자 삭제에 실패했습니다.');
                });
            }
        }
		//바로 리스트 불러옴
        loadParticipants();
    });
})
</script>
<body>
    <h1>참가자 관리</h1>
    <div id="content"></div>
</body>
