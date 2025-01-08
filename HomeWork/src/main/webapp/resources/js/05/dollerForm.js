/**
 * 
 */
$(function() {
	const $resultArea = $("#result-area");
	const $form = $("form");
	$("#async-btn").on("click", function() {
		let url = this.dataset.url;
		let data = $form.serialize();
		let settings = {
			url: url,
			method: "post",
			dataType: "json",
			data: data,
			success: function(resp) {
				if (this.dataType == "json") {
					let spanTag = `<span>${resp.result}</span>`;
					$resultArea.html(spanTag);
				} else {
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
})