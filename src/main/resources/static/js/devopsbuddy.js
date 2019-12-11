$(document).ready(main); // invoke when document document has fully loaded

function main(){

    $('.btn-collapse').click(function(e){
        e.preventDefault();
        var $this = $(this);
        var $collapse = $this.closest('.collapse-group').find('.collapse');
        $collapse.collapse('toggle');
    });
}