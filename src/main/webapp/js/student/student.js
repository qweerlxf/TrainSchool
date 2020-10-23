function regStudent() {
    var no = $("#no").val();
    var name = $('#name').val();
    var classId = $("#classSelect").val();
    var age = $('#age').val();
    var password = $("#password").val();
    var confirmPassword = $('#confirmPassword').val();

    $.ajax({
        url: "/api/student/reg",
        contentType: 'application/json',
        type: 'POST',
        data: JSON.stringify({
            'no': no,
            'name': name,
            'classId': classId,
            'age': age,
            'password': password,
            'confirmPassword': confirmPassword
        }),
        success: function (result) {
            if (result.success) {
                window.location.href = result.result
            } else {
                alert(result.errorMsg)
            }
        }
    });

}

function unSelectCourse(courseId) {
    console.log(courseId)
    $.ajax({
        url: "/api/auth/student/un/select/course/" + courseId,
        contentType: 'application/json',
        type: 'GET',
        success: function (result) {
            if (result.success) {
                window.location.href = result.result
            } else {
                alert(result.errorMsg)
            }
        }
    });
}

function courseInfo(courseId) {
    console.log(courseId)
    $.ajax({
        url: "/api/auth/student/course/info/" + courseId,
        contentType: 'application/json',
        type: 'GET',
        success: function (result) {
            if (result.success) {
                window.location.href = result.result
            } else {
                alert(result.errorMsg)
            }
        }
    });
}

function selectCourse(courseId) {
    $.ajax({
        url: "/api/auth/student/select/course/" + courseId,
        contentType: 'application/json',
        type: 'GET',
        success: function (result) {
            if (result.success) {
                window.location.href = result.result
            } else {
                alert(result.errorMsg)
            }
        }
    });
}




