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
            var tbody = $("#tbody");
            $(tbody).children().remove();

            var json = $.parseJSON(data);
            for (var i=0; i<json.length; i++){
                var tr = $("<tr></tr>");
                var td1 = $("<td>"+json[i].name+"</td>");
                var td2 = $("<td>"+json[i].subsidizeName+"</td>");
                var td3 = $("<td>"+json[i].date+"</td>");
                var td4 = $("<td>"+json[i].address+"</td>");
                var td5 = $("<td>"+json[i].mail+"</td>");
                var td6 = $("<td>"+json[i].presidentName+"</td>");
                var td7 = $("<td>"+json[i].connect+"</td>");
                var td8 = $("<td>"+json[i].department+"</td>");
                var td9 = $("<td>"+json[i].merge+"</td>");
                $(tr).append(td1).append(td2)
                    .append(td3).append(td4)
                    .append(td5).append(td6)
                    .append(td7).append(td7).append(td9);
                $(tbody).append(tr);
            }
        }
    });


}
