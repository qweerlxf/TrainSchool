function addTeacherIndex(teacherId) {
    window.location.href = "/api/auth/admin/teacher/detail/" + teacherId;
}

function saveTeacher(id) {
    var name = $('#name').val();
    var phone = $("#phone").val();
    var specialty = $('#specialty').val();

    $.ajax({
        url: "/api/auth/admin/teacher/save",
        contentType: 'application/json',
        type: 'POST',
        data: JSON.stringify({
            'id': id,
            'name': name,
            'phone': phone,
            'specialty': specialty,
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

function updateTeacherStatus(id, status) {
    if (status === 0) {
        status = 1;
    } else {
        status = 0;
    }

    $.ajax({
        url: "/api/auth/admin/teacher/update/status",
        contentType: 'application/json',
        type: 'POST',
        data: JSON.stringify({
            'id': id,
            'status': status,
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

function deleteTeacher(id) {
    $.ajax({
        url: "/api/auth/admin/teacher/delete/" + id,
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

function addCourseIndex(courseId) {
    window.location.href = "/api/auth/admin/course/detail/" + courseId;
}

function saveCourse(id) {
    var name = $('#name').val();
    var courseDay = $("#courseDay").val();
    var courseStart = $('#courseStart').val();
    var courseEnd = $('#courseEnd').val();
    var maxStuNum = $('#maxStuNum').val();
    var teacherId = $('#teacherSelect').val();
    var classroomId = $('#classroomSelect').val();

    $.ajax({
        url: "/api/auth/admin/course/save",
        contentType: 'application/json',
        type: 'POST',
        data: JSON.stringify({
            'id': id,
            'name': name,
            'courseDay': courseDay,
            'courseStart': courseStart,
            'courseEnd': courseEnd,
            'maxStuNum': maxStuNum,
            'teacherId': teacherId,
            'classroomId': classroomId
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

function updateCourseStatus(id, status) {
    if (status === 0) {
        status = 1;
    } else {
        status = 0;
    }

    $.ajax({
        url: "/api/auth/admin/course/update/status",
        contentType: 'application/json',
        type: 'POST',
        data: JSON.stringify({
            'id': id,
            'status': status,
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

function deleteCourse(id) {
    $.ajax({
        url: "/api/auth/admin/course/delete/" + id,
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


function addClassroom(classroomId) {
    window.location.href = "/api/auth/admin/classroom/detail/" + classroomId;
}

function saveClassroom(classroomId) {
    var name = $('#name').val();
    var maxStuNum = $("#maxStuNum").val();
    var hasVideo = $('#videoSelect').val();
    var address = $('#address').val();

    $.ajax({
        url: "/api/auth/admin/classroom/save",
        contentType: 'application/json',
        type: 'POST',
        data: JSON.stringify({
            'id': classroomId,
            'name': name,
            'hasVideo': hasVideo,
            'address': address,
            'maxStuNum': maxStuNum,
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



function updateClassroomStatus(id, status) {
    if (status === 0) {
        status = 1;
    } else {
        status = 0;
    }

    $.ajax({
        url: "/api/auth/admin/classroom/update/status",
        contentType: 'application/json',
        type: 'POST',
        data: JSON.stringify({
            'id': id,
            'status': status,
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

function deleteClassroom(id) {
    $.ajax({
        url: "/api/auth/admin/classroom/delete/" + id,
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