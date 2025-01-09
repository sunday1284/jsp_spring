/**
 * 
 */
document.addEventListener("DOMContentLoaded", () => {
	const $form = $('[name="target-form"]');
	const $resultArea = $('[data-result]:first');

	$form.on("submit", function(e) {
		e.preventDefault();

		let url = this.action;
		let method = this.method;
		//param_name =param_value&name=value
		let data = $(this).serialize();
		let $radioArea = $('[name="dataType"]:checked').val();
		let dataType = $radioArea;
		let settings = {
			url: url,
			method: method,
			//서버 사이드에 있는 순수한 데이터만 가져오고 싶을 때 -> json 
			// 반대로 꾸미고 싶을 때 -> html
			dataType: dataType, // request accept header / response content-type header
			data: data,
			/*
			js : `${변수이름을 비롯한 expression}` (템플릿)
			jsp : ${attributename 을 비롯한 expression} (EL, 표현언어)
			xml : ${property_name} (placeholder)
			*/
			success: function(resp) {
				// csr 랜더링 방식
				if (dataType == "json") {
					let spanTag = `<span>최종 환산값 : ${resp.result} </span>`;
					$resultArea.html(spanTag);
				}else {
					$resultArea.html(resp)
				}
			},
			error: function(jqXHR, status, error) {
				if (dataType == "xml" && jqXHR.status === 406) { 
					// 406 상태 코드 확인 
					let errorMsg = `<span>XML 컨텐츠는 서비스 불가합니다.</span>`; 
					$resultArea.html(errorMsg); 
				}else {
					console.log(jqXHR);
					console.log(status);
					console.log(error);		
				}
			}
		};
		$.ajax(settings);
	});
});