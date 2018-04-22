$(function() {

    let url = 'http://localhost:8081/cookie/list';

    console.log('Hello...');

    $.get(url).done(data => {
        console.log(data);
        $('#test-target').html(data);
    })

});
