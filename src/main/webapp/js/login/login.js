function login() {
    var account = $("#account").val();
    var password = $('#password').val();
    $.ajax({
        url: "/api/login",
        contentType: 'application/json',
        type: 'POST',
        data: JSON.stringify({'account': account, 'password': password}),
        success: function (result) {
            console.log(result.success)
            if (result.success) {
                window.location.href = result.result.viewName
            } else {
                alert(result.errorMsg)
            }
        }
    });
}

function studentReg() {
    window.location.href = '/api/student/reg/index'
}