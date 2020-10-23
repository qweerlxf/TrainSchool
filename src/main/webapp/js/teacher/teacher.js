function inputScore(btn){
    var tdList = $(btn).parent().parent().find("td");

    var stuId = tdList.eq(0).text();
    var courseId = tdList.eq(2).text();
    var score = tdList.eq(4).children(0).val();
    $.ajax({
        url: "/api/auth/teacher/input/score",
        contentType: 'application/json',
        type: 'POST',
        data: JSON.stringify({
            'studentId': stuId,
            'courseId': courseId,
            'score': score,
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