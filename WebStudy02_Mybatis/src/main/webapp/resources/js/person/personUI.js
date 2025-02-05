document.addEventListener("DOMContentLoaded", ()=>{
    const $personForm = $('#person-form'); //insert
    const $updateForm = $("#update-form");
    const $personTb = $('#person-tb');
    const $personTbody = $('#person-tb tbody');
    const baseURI = "../../person";
    const modalEl = document.getElementById('exampleModal');
    const myModalAlternative = new bootstrap.Modal(modalEl, {});
    const $delBtn = $("#del-btn");

    const fnSuccess = function(resp){
        let list = resp.personList;
        let trTags = list.map(p=>
             `
                 <tr class="data-tr" data-id="${p.id}">
                     <td>${p.name}</td>
                     <td>${p.gender}</td>
                     <td>${p.age}</td>
                 </tr>
             `
        ).join("\n");
        $personTbody.html(trTags);
        $personForm[0].reset();
     };

    $.ajax({
        url:baseURI,
        dataType:"json",
        success:fnSuccess
    }); // ajax end

    $personForm.on("submit", function(e){
        e.preventDefault();
        // let queryString = $(this).serialize();
        let data = {};
        //input 태그의 name->key  두번째요소 value값 
        let fd = new FormData(this);
        for (let p of fd.keys()){
            // console.log(p, fd.get(p));
            data[p] =fd.get(p);
        }
        $.ajax({
            url:baseURI,
            method:"post",
            dataType:"json",
            processData:false,
            contentType:"application/json",
            data:JSON.stringify(data), // parameter --> json
            success:fnSuccess         
        });

    }); // form submit 이벤트 처리 end 
    //이벤트 버블링 구조 -> 자식을 타겟으로 잡음
    $personTbody.on("click", "tr.data-tr" ,function(){
       let id = this.dataset.id;
       $.ajax({
           url:`${baseURI}/${id}`,
           dataType:"json",
           success:function(resp){
               resp.person;
               myModalAlternative.show(resp.person); 
            }
       });
    }); // click event handler end 

    modalEl.addEventListener("show.bs.modal",function(e){
        // let trTag = e.relatedTarget
        // let id = trTag.dataset.id;
        let person = e.relatedTarget;
        let $modalBody = $(this).find(".modal-body");
        // let fragHtml =
        //     `
        //         <div>
        //            <p>${person.name}</p>
        //            <p>${person.age}</p>
        //            <p>${person.gender}</p>
        //            <p>${person.address}</p>
        //         </div>
        //     `;
        // $modalBody.html(fragHtml);
        // $updateForm 입력 태그들을 person 의 포로포티들로 초기화


        $delBtn[0].dataset.id = person.id;
    });
    modalEl.addEventListener("hidden.bs.modal",function(e){
        let $modalBody = $(this).find(".modal-body");
        $modalBody.empty();
        //마지막에 id값을 초기화
        $delBtn[0].dataset.id = "";
    });

    $delBtn.on("click",function(){
        let personId = this.dataset.id;
        // /person/a001 DELETE
        let url = `${baseURI}/${personId}`;
        // 1. 비동기 삭제 요청 전송 
        $.ajax({
            url:url,
            method:"delete",
            dataType:"json",

            success:function(resp){
                if(resp.success){
                    // 2. tbody 의 목록 갱신 -> 하나의 tr 제거 
                    $(`tr[data-id=${personId}]`).remove();
                    // 3. 모달 종료 
                    myModalAlternative.hide();
                }

            }
        });
        
    }); //삭제 버튼 클릭 이벤트 처리 end

    $updateForm.on("submit",function(e){
        e.preventDefault();
        // 1. /person/a001 PUT 
        // 2. 비동기 수정 요청 전송 
        // 3. tbody 의 목록 갱신 -> 수정된 tr 수정
        // 4. 모달 종료
    });
});