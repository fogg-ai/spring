(function ($) {
    $(function () {

        $('.sidenav').sidenav();

        $(".dropdown-trigger").dropdown();
        $(".dropdown-trigger-mobile").dropdown();

        let dp = $('.datepicker').datepicker({
            format: "yyyy-mm-dd"
        });
        $('select').formSelect();
        $('.modal').modal();
    });
})(jQuery);