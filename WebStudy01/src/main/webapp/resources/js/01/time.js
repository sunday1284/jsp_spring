/**
 * 
 */
let clientNow = new Date();
const clientTimeArea = document.querySelector("#client-time");
clientTimeArea.innerHTML = clientNow;

const asyncA = document.getElementById("async-a");
const serverTimeArea = document.getElementById("server-time");
asyncA.addEventListener("click", (e)=>{
	e.preventDefault(); // 이벤트 고유 액션 중단.
	console.log(asyncA === e.target);
//	fetch, ajax(XMLHttpRequest, jQuery)
	let url = e.target.href;
	fetch(url).then((resp)=>{
		if(resp.ok){
			//return resp.text();
			return resp.json();
		}else{
			throw new Error(`에러 발생, 상태 코드 ${resp.status}`);
		}
	}).then((json)=>{
		serverTimeArea.innerHTML = json.time;
	})
	.catch((error)=>{
		console.log(error);
	});
		
});
















