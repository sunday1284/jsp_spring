/**
 * 
 */
$(document).ready(function() {
	$('#button').click(function(event) {
		event.preventDefault();

		//입력 값 
		var operand = $('#operand').val();

		// AJAX 요청
		$.ajax({
			url: $('#factorial-form').attr('action') + '?ajax=true', // 폼의 action URL
			type: 'GET', // GET 방식으로 전송
			data: { operand: operand }, // 전송할 데이터
			dataType: 'json',
			success: function(response) {
				// 서버에서 반환된 결과를 result-area에 표시
				$('#result-area').text(response.result);
			},
			error: function(xhr, status, error) {
				// 에러 처리
				$('#result-area').text('오류 발생: ' + error);
			}
		});

	});
});


