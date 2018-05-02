function reload() {

    $.ajax({
        url: '/api/message/list',
        type: 'GET',
        success: function (data) {

            let content = '';
            for (let message of data) {
                content += `<div class="border border-primary p-2 m-2">` +
                                `<div>` +
                                    `<span class="badge badge-primary mr-2">${message.author}</span>` +
                                    `<span class="badge badge-info">${message.postdate}</span>` +
                                    `<button class="delete_message btn btn-sm btn-danger float-right" data-message-id="${message.id}">x</button>` +
                                `</div>` +
                                `<div class="py-2 mx-2">${message.text}</div>` +
                           `</div>`;
            }

            $('#test-target').html(content);

            $('.delete_message').click(function () {

                $.ajax({
                    url: '/api/message/remove',
                    type: 'POST',
                    data: {'old_message_id': $(this).attr('data-message-id')},
                    success: function (response) {
                        if (response === 'OK') {
                            reload();
                        } else {
                            alert(response);
                        }
                    }
                });

            });

            $('#message_form_submit').prop('disabled', false);

        }
    });
}

$(function() {

    reload();

    setInterval(function(){ reload(); }, 2500);

    let last_author = Cookies.get('last_author');
    if (last_author !== undefined) {
        $('input[name=new_author]').val(last_author);
    }

    $('#message_form').submit(function(event){

        event.preventDefault();

        $('#message_form_submit').prop('disabled', true);

        $.ajax({
            url: '/api/message/add',
            type: 'POST',
            data: $(this).serialize(),
            success: function (response) {
                if (response === 'OK') {
                    reload();
                    $('input[name=new_message]').val("").focus();
                    Cookies.set('last_author', $('input[name=new_author]').val());
                } else {
                    alert(response);
                }
            }
        });
    });

});
