/**
 * 
 */
document.addEventListener("DOMContentLoaded", () => {
	const $form = $('[name="target-form"]');
	const $resultArea = $('[data-result]:first');

	const funcObj = {
		json: function(resp) {
			// csr 랜더링 방식
			let spanTag = `<span>최종 환산값 : ${resp.result} </span>`;
			$resultArea.html(spanTag);
		},
		html: function(resp) {
			$resultArea.html(resp);
		}
	}
	$form.on("submit", function(e) {
		e.preventDefault();
		$resultArea.empty();
		
		let propName = 'action';
		let url = this[propName];  //this.action;
		let method = this['method']; //this.method;
		//param_name =param_value&name=value
		let data = $(this).serialize();
		let dataType = $('[name="dataType"]:checked').val();
		
		if (!dataType) {
			dataType = "json";
		}
		
		//객체의 구성요소에 접근하는 방법
		//1. dot notation : 객체.프로퍼티명
		//2. association array : 객체['프로퍼티명']
		let success = funcObj[dataType]; 
	
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

			success: success,
			error: function(jqXHR, status, error) {
				if (dataType == "xml" && jqXHR.status === 406) {
					// 406 상태 코드 확인 
					let errorMsg = `<span>XML 컨텐츠는 서비스 불가합니다.</span>`;
					$resultArea.html(errorMsg);
				} else {
					console.log(jqXHR);
					console.log(status);
					console.log(error);
				}
			}
		};
		$.ajax(settings);
	});
});