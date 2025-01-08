/**
 * 
 */
$(function(){
	const $resultArea =  $("#result-area");
	const $form = $("form");
	$("#async-btn").on("click", function(){
		/*line : url, method - url, method[type]
		header : 응답 컨텐츠 형식(accept header) 
			- dataType (accept request header / content-type response header)
		body : 클라이언트가 서버로 보낼 컨텐츠 - data 
		응답 수신 방법 
			- success : function(response_content){}
			- error : function(jqXHR, errorStatus, error){}*/
//		data - 으로 시작되는 data 속성으로 data-[key]="value" 형태 데이터 저장.
		let url = this.dataset.url;
		let data = $form.serialize();
		let settings = { 
			url: url, 
			method: "post", 
			dataType: "json", 
			data: data,
			success: function(resp) { // JSON 응답을 파싱하여 결과를 표시 
				if(this.dataType == "json"){
					let spanTag = `<span>${resp.result}</span>`; 
					$resultArea.html(spanTag);		
				}else{
					//dataType이 html이나 xml으로 바뀌었을때의 응답
					$resultArea.html(resp);	
				}
			}, 
			error: function(jqXHR, status, error) { 
				console.log(jqXHR); 
				console.log(status);
				console.log(error); 
			} 
		}; 
		$.ajax(settings);
	});
});