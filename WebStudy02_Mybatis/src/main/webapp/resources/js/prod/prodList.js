/**
 * 
 */


document.addEventListener('DOMContentLoaded', function() {
    $("tr[data-prod-id]").on('click', function() {
        let $table = $(this).parents('table');
        let detailUrl = $table.data("detailUrl");
        let what = this.dataset.prodId;
        location.href = `${detailUrl}?what=${what}`;
    });
});

