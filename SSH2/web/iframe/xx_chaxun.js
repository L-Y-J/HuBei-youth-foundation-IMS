/**
 * Created by yongjie on 14-6-3.
 */

function Check(time){
    var result = time.match(/(\d\d\d\d)/);
    if (result==null)
        return false;
    return true;

}

function findSchool() {
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var area = $("#area").val();
    var oldSchoolName = $("#oldSchoolName").val();
    var newSchoolName = $("#newSchoolName").val();
    var subsidizeName = $("#subsidizeName").val();

    if (startTime.length>0 || endTime.length>0){
        var a = Check(startTime);
        var b = Check(endTime);
        if (a==false || b==false){
            alert("请输入正确的时间格式");
            return;
        }
    }

    $.ajax({
        url: 'find_school.do',
        type: 'POST',
        data: {'startTime': startTime,
            'endTime': endTime,
            'area': area,
            'oldSchoolName': oldSchoolName,
            'newSchoolName': newSchoolName,
            'subsidizeName': subsidizeName},
        dataType: 'text',
        success: function (data) {
            alert(data);
        }
    });


}
