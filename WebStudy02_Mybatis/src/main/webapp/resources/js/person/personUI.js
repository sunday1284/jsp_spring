document.addEventListener("DOMContentLoaded", ()=>{
    const $personForm = $('#person-form');
    const $personTb = $('#person-tb');
    const $personTbody = $('#person-tb tbody');
    const baseURI = "../../person";

    const fnSuccess = function(resp){
        let list = resp.personList;
        let trTags = list.map(p=>
             `
                 <tr>
                     <td>${p.name}</td>
                     <td>${p.gender}</td>
                     <td>${p.age}</td>
                 </tr>
             `
        ).join("\n");
        $personTbody.html(trTags);
     }

    $.ajax({
        url:baseURI,
        dataType:"json",
        success:fnSuccess
    }); // ajax end

    $personForm.on("submit", function(e){
        e.preventDefault();
        let queryString = $(this).serialize();
        $.ajax({
            url:baseURI,
            method:"post",
            dataType:"json",
            data:queryString, // parameter --> json
            success:fnSuccess         
        });

    });
});