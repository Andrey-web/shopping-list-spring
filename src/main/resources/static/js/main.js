$(document).ready(function () {

        $('.nameFull').on('click', function (e) {
            if ($(this).parents('tr').hasClass('status-0')) {
                $(this).hide();
                $(this).siblings(".inputProdName").show();
                $(this).siblings(".inputProdName").focus();

                $(this).parents('tr').find('.iconUpdate').show();
                $('.count').hide();
                $('.forHide').hide();
            } else {
                e.preventDefault();
            }
        });

    $(document).on('click', '.yesIcon, .okIcon, .removeBtn', function () {

        let id = $(this).attr('data-id');
        let name = $(this).attr('data-name');
        let count = $(this).attr('data-count');
        let formId = $(this).attr('data-formid');
        let tr = $(this).parents('tr');
        let th = $(this);

        let queryParams = new URLSearchParams(window.location.search);
        queryParams.set("tabId", formId);
        history.replaceState(null, null, "?"+queryParams.toString());

        let c;
        let postType;

        if (th.hasClass('yesIcon')) {
            th.removeClass('fa-cart-arrow-down').addClass('fa-spinner fa-spin');
            c = 'yesIcon';
            postType = 'yes';
        } else if (th.hasClass('okIcon')) {
            th.removeClass('fa-check-circle').addClass('fa-spinner fa-spin');
            c = 'okIcon';
            postType = 'change';
        } else if (th.hasClass('removeBtn')) {
            th.removeClass('fa-cart-arrow-down').addClass('fa-spinner fa-spin');
            c = 'removeBtn';
            postType = 'removeBtn';
        }

        function success(c, th) {
            if (c == 'yesIcon') {
                tr.hide();
            } else if (c == 'okIcon') {
                tr.removeClass('status-1').addClass('status-0');
                th.removeClass('fa-spinner fa-spin okIcon').addClass('fa-check-circle removeBtn');
                $('.retweet').addClass('active').text('Упорядочить');
            } else {
                tr.addClass('status-1').removeClass('status-0');
                th.removeClass('fa-spinner fa-spin removeBtn').addClass('fa-check-circle okIcon');
            }
        }

        $.ajax({
            data: {id: id, postType: postType, formId: formId, name: name, count: count},
            method: 'POST',
            success: function(data) {
                success(c, th);
            },
        });
    });

    $(document).on('click', '.delIcon', function (e) {
        e.preventDefault();
        let id = $(this).parents('tr').find('input[name=id]').val();
        let formId = $(this).parents('tr').find('input[name=formId]').val();
        let tr = $(this).parents('tr');
        $.ajax({
            data: {id: id, del: 1, formId: formId},
            method: 'POST',
            success: function(data) {
                tr.hide();
            },
        });
    })

    $('.retweet').on('click', function () {
        location.reload();
    });

    $('.nav-tabs li').on('click', function () {
        let queryParams = new URLSearchParams(window.location.search);
        queryParams.set("tabId", $(this).attr('data-tabId'));
        history.replaceState(null, null, "?"+queryParams.toString());
    });
});