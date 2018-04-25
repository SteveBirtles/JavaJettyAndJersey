function reload() {
    $.getJSON('http://localhost:8081/api/test').done(function(data) {

        let content = '';
        for (let fruit of data) {
            content += '<span class="badge badge-primary p-2 m-2">' + fruit.name +
                '<button class="delete_fruit btn btn-sm btn-danger ml-2"> x</button></span>';
        }

        $('#test-target').html(content);

        $(".delete_fruit").click(function() {
            $(this).parent().hide();
        });

    });
}

$(function() {

    reload();

    $('#fruit_form').submit(function(e){
        e.preventDefault();

        $.ajax({
                url: 'http://localhost:8081/api/fruitform',
                type: 'POST',
                contentType: 'application/x-www-form-urlencoded',
                data: {'new_fruit': $('#new_fruit').val()},
                success: function(data) {
                    reload();
                }});
    });


});
