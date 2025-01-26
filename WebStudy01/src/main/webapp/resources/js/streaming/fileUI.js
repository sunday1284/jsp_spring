/**
 * 
 */
document.addEventListener("DOMContentLoaded",()=>{
	const $form = $("form:first");
	const $options = $("[data-mine]");
	const $resultArea = $("#result-area");
	// 모듈화 -> 중요!! display에서 각종 함수들에 대한 처리를 담당함
	const display = {
		image:function(src){
			let img = `<img src="${src}"/>`;
			$resultArea.html(img);
		},
		video:function(src){
			let video = `<video autoplay controls src="${src}"></video>`;
			$resultArea.html(video);
		},
		text:function(url, queryString){
			// 비동기 요청 전송
			$.ajax({
				url:url,
				dataType:"text",
				data:queryString,
				success:function(resp){
					let pre = `<pre>${resp}</pre>`;
					$resultArea.html(pre);
				},
				error:function(jqXHR, status, error){
					console.log(jqXHR, status, error);
				}
			});
		}
	}
	$form.on("submit",function(event){
		event.preventDefault();
		$resultArea.empty();
		
		//url 액션으로 받아옴
		let url = this.action;
		//?뒤에 있는 값을 직렬화 시킴!!
		let queryString =  $(this).serialize();
		
		//추가적으로 선택을 할때 filter함수를 씀
		let $selected = $options.filter(":selected");
		//$selected[0].dataset.mine; // 위랑 같은 코드
		
		//mine 키를 가져옴
		let mineType = $selected.data("mine");
		
		if($selected.length == 0 || !mineType){
			alert("정상 옵션을 선택하세요.");
			return false;
		}
		
		if(mineType.indexOf("image")==0){
			display.image(`${url}?${queryString}`);
		}else if(mineType.indexOf("video")==0){
			display.video(`${url}?${queryString}`);
		}else {
			display.text(url, queryString);
		}
	});
})
