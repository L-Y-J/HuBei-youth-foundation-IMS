/**
 * Created by yongjie on 14-6-4.
 */

function Check(date){
    var result = date.match(/\d\d\d\d/);
    if (result==null)
        return false;
    return true;
}

function FindSchool(){
    var startTime = $("#startTime1").val();
    var endTime = $("#endTime1").val();

    if (!Check(startTime)){
        alert("请输入正确的起始时间");
        return;
    }

    if (!Check(endTime)){
        alert("请输入正确的终止时间");
        return;
    }

    $.ajax({
        url:'find_school_area.do',
        type:'POST',
        data:{'startTime':startTime,'endTime':endTime},
        dataType:'text',
        success: function(data){
//            alert(data);
            var json = $.parseJSON(data);
            var tbody1 = $("#tbody1");
            tbody1.children().remove();
            for (var i = 0; i < json.length; i++) {
                var tr = $("<tr></tr>");
                var td0 = $("<td>"+json[i].area+"</td>");
                var td1 = $("<td>"+json[i].num0+"</td>");
                var td2 = $("<td>"+json[i].num1+"</td>");
                var td3 = $("<td>"+json[i].num2+"</td>");
                var td4 = $("<td>"+json[i].num3+"</td>");
                var td5 = $("<td>"+json[i].num4+"</td>");
                tr.append(td0).append(td1).append(td2).append(td3).append(td4).append(td5);
                tbody1.append(tr);
            }
        }
    });
}

function FindSubsidizeSchool(){
    var startTime = $("#startTime2").val();
    var endTime = $("#endTime2").val();

    if (!Check(startTime)){
        alert("请输入正确的起始时间");
        return;
    }

    if (!Check(endTime)){
        alert("请输入正确的终止时间");
        return;
    }

    $.ajax({
        url:'find_school_area.do',
        type:'POST',
        data:{'startTime':startTime,'endTime':endTime},
        dataType:'text',
        success: function(data){
//            alert(data);
            var json = $.parseJSON(data);
            var tbody2 = $("#tbody2");
            tbody2.children().remove();
            for (var i = 0; i < json.length; i++) {
                var tr = $("<tr></tr>");
                var td0 = $("<td>"+json[i].area+"</td>");
                var td1 = $("<td>"+json[i].num0+"</td>");
                var td2 = $("<td>"+json[i].num1+"</td>");
                var td3 = $("<td>"+json[i].num2+"</td>");
                var td4 = $("<td>"+json[i].num3+"</td>");
                var td5 = $("<td>"+json[i].num4+"</td>");
                tr.append(td0).append(td1).append(td2).append(td3).append(td4).append(td5);
                tbody2.append(tr);
            }
        }
    });
}

























