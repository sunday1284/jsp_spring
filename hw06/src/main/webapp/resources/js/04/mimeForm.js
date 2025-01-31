/**
 * 
 */

/*document.addEventListener("DOMContentLoaded", ()=>{
	const mimeForm = document.getElementById("mime-form");
	mimeForm.addEventListener("submit", (e)=>{
		e.preventDefault();
	});
});*/

$(function(){
	const $mieForm = $("#mime-form");
	$mieForm.on("submit", function(e){
		e.preventDefault();
		// 비동기 요청 전송
		let url = this.action;
		let method = this.method;
		let queryString = $(this).serialize();
		console.log(queryString);
		$.ajax({
			url : url,
			method : method,
			data: queryString, 
			dataType:"json",
			success:function(jsonObj){
				$("#mime-area").html(jsonObj.mime);
			}
		});
	});
});












