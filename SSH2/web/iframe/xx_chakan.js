function title1(){
    $("#chakan_xuexiao").show();
    $("#chakan_shenqingshu").hide();
    $("#chakan_shenqingshuguihua").hide();
    $("#chakan_guihuashu").hide();
    $("#chakan_guihuashushenhe").hide();
    $("#chakan_yuebao").hide();
    $("#chakan_jungong").hide();
    $("#chakan_jungongshenhe").hide();

    $("#title1").attr("class","active");
    $("#title2").removeClass("active");
    $("#title3").removeClass("active");
    $("#title4").removeClass("active");
    $("#title5").removeClass("active");
    $("#title6").removeClass("active");
    $("#title7").removeClass("active");
    $("#title8").removeClass("active");
}

function title2(){
    $("#chakan_xuexiao").hide();
    $("#chakan_shenqingshu").show();
    $("#chakan_shenqingshushenhe").hide();
    $("#chakan_guihuashu").hide();
    $("#chakan_guihuashushenhe").hide();
    $("#chakan_yuebao").hide();
    $("#chakan_jungong").hide();
    $("#chakan_jungongshenhe").hide();

    $("#title1").removeClass("active");
    $("#title2").attr("class","active");
    $("#title3").removeClass("active");
    $("#title4").removeClass("active");
    $("#title5").removeClass("active");
    $("#title6").removeClass("active");
    $("#title7").removeClass("active");
    $("#title8").removeClass("active");

}


function title3(){
    FindArea($("#select1"),$("#select2"));

    $("#chakan_xuexiao").hide();
    $("#chakan_shenqingshu").hide();
    $("#chakan_shenqingshushenhe").show();
    $("#chakan_guihuashu").hide();
    $("#chakan_guihuashushenhe").hide();
    $("#chakan_yuebao").hide();
    $("#chakan_jungong").hide();
    $("#chakan_jungongshenhe").hide();

    $("#title1").removeClass("active");
    $("#title2").removeClass("active");
    $("#title3").attr("class","active");
    $("#title4").removeClass("active");
    $("#title5").removeClass("active");
    $("#title6").removeClass("active");
    $("#title7").removeClass("active");
    $("#title8").removeClass("active");
}

function title4(){
    $("#chakan_xuexiao").hide();
    $("#chakan_shenqingshu").hide();
    $("#chakan_shenqingshushenhe").hide();
    $("#chakan_guihuashu").show();
    $("#chakan_guihuashushenhe").hide();
    $("#chakan_yuebao").hide();
    $("#chakan_jungong").hide();
    $("#chakan_jungongshenhe").hide();

    $("#title1").removeClass("active");
    $("#title2").removeClass("active");
    $("#title3").removeClass("active");
    $("#title4").attr("class","active");
    $("#title5").removeClass("active");
    $("#title6").removeClass("active");
    $("#title7").removeClass("active");
    $("#title8").removeClass("active");
}


function title5(){
    FindArea($("#select3"),$("#select4"));

    $("#chakan_xuexiao").hide();
    $("#chakan_shenqingshu").hide();
    $("#chakan_shenqingshushenhe").hide();
    $("#chakan_guihuashu").hide();
    $("#chakan_guihuashushenhe").show();
    $("#chakan_yuebao").hide();
    $("#chakan_jungong").hide();
    $("#chakan_jungongshenhe").hide();

    $("#title1").removeClass("active");
    $("#title2").removeClass("active");
    $("#title3").removeClass("active");
    $("#title4").removeClass("active");
    $("#title5").attr("class","active");
    $("#title6").removeClass("active");
    $("#title7").removeClass("active");
    $("#title8").removeClass("active");
}


function title6(){
    $("#chakan_xuexiao").hide();
    $("#chakan_shenqingshu").hide();
    $("#chakan_shenqingshushenhe").hide();
    $("#chakan_guihuashu").hide();
    $("#chakan_guihuashushenhe").hide();
    $("#chakan_yuebao").show();
    $("#chakan_jungong").hide();
    $("#chakan_jungongshenhe").hide();

    $("#title1").removeClass("active");
    $("#title2").removeClass("active");
    $("#title3").removeClass("active");
    $("#title4").removeClass("active");
    $("#title5").removeClass("active");
    $("#title6").attr("class","active");
    $("#title7").removeClass("active");
    $("#title8").removeClass("active");
}


function title7(){
    $("#chakan_xuexiao").hide();
    $("#chakan_shenqingshu").hide();
    $("#chakan_shenqingshushenhe").hide();
    $("#chakan_guihuashu").hide();
    $("#chakan_guihuashushenhe").hide();
    $("#chakan_yuebao").hide();
    $("#chakan_jungong").show();
    $("#chakan_jungongshenhe").hide();

    $("#title1").removeClass("active");
    $("#title2").removeClass("active");
    $("#title3").removeClass("active");
    $("#title4").removeClass("active");
    $("#title5").removeClass("active");
    $("#title6").removeClass("active");
    $("#title7").attr("class","active");
    $("#title8").removeClass("active");
}



function title8(){
    FindArea($("#select5"),$("#select6"));

    $("#chakan_xuexiao").hide();
    $("#chakan_shenqingshu").hide();
    $("#chakan_shenqingshushenhe").hide();
    $("#chakan_guihuashu").hide();
    $("#chakan_guihuashushenhe").hide();
    $("#chakan_yuebao").hide();
    $("#chakan_jungong").hide();
    $("#chakan_jungongshenhe").show();

    $("#title1").removeClass("active");
    $("#title2").removeClass("active");
    $("#title3").removeClass("active");
    $("#title4").removeClass("active");
    $("#title5").removeClass("active");
    $("#title6").removeClass("active");
    $("#title7").removeClass("active");
    $("#title8").attr("class","active");
}

function FindArea(select1,select2){
    $.ajax({
        url:'find_area_qu.do',
        type:'POST',
        data:{},
        dataType:'text',
        success:function(data){
            select1.children().remove();
            var json = $.parseJSON(data);
            for (var i = 0; i < json.length; i++) {
                var option = $("<option>"+json[i].name+"</option>");
                select1.append(option);
            }
        }
    });

    $.ajax({
        url:'find_area_xian.do',
        type:'POST',
        data:{},
        dataType:'text',
        success:function(data){
            select2.children().remove();
            var json = $.parseJSON(data);
            for (var i = 0; i < json.length; i++) {
                var option = $("<option>"+json[i].name+"</option>");
                select2.append(option);
            }
        }
    });
}

function check(date){
    var result = date.match(/^\b\d\d\d\d$\b/);
    if (result==null)
        return false;
    return true;
}

function FindSchool(){
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var subsidizeName = $("#subsidizeName").val();
    var oldSchoolName = $("#oldSchoolName").val();
    var area = $("#area").val();

    if (startTime.length>0 && !check(startTime)){
        alert('请输入正确的开始时间');
        return;
    }

    if (endTime.length>0 && !check(endTime)){
        alert('请输入正确的结束时间');
        return;
    }

    $.ajax({
        url:'find_school_state.do',
        type:'POST',
        data:{'startTime':startTime,'endTime':endTime,
            'subsidizeName':subsidizeName,
            'oldSchoolName':oldSchoolName,
            'area':area},
        dataType:'text',
        success: function(data){
//            alert(data);
            var json = $.parseJSON(data);
            var tbody1 = $("#tbody1");
            tbody1.children().remove();
            for (var i = 0; i < json.length; i++) {
                var tr = $("<tr></tr>");
                var td1 = $("<td>"+json[i].subsidizeName+"</td>");
                var td2 = $("<td>"+json[i].area+"</td>");
                var td3 = $("<td>"+json[i].schoolName+"</td>");
                var td4 = $("<td>"+json[i].book1+"</td>");
                var td5 = $("<td>"+json[i].book2+"</td>");
                tr.append(td1).append(td2).append(td3).append(td4).append(td5);
                tbody1.append(tr);
            }
        }
    });
}

function FindSubsidize(){
    var start = $("#startTime1").val();
    var end = $("#endTime1").val();
    var subsidizeName = $("#subsidizeName1").val();

    if (start.length>0 && check(start)==false){
        alert("请输入正确的开始时间");
        return;
    }
    if (end.length>0 && check(end)==false){
        alert("请输入正确的结束时间");
        return;
    }

    $.ajax({
        url:'find_subsidize_state.do',
        type:'POST',
        data:{'start':start,'end':end,'subsidizeName':subsidizeName},
        dataType:'text',
        success: function(data){
//            alert(data);
            var json = $.parseJSON(data);
            var tbody2 = $("#tbody2");
            tbody2.children().remove();
            for (var i = 0; i < json.length; i++) {
                var tr = $("<tr></tr>");
                var td1 = $("<td>"+json[i].name+"</td>");
                var td2 = $("<td>"+json[i].place+"</td>");
                var td3 = $("<td>"+json[i].schoolName+"</td>");
                tr.append(td1).append(td2).append(td3);
                tbody2.append(tr);
            }
        }
    });
}


function FindPassBooks(){
    var qu = $("#select1").val();
    var xian = $("#select2").val();

    $.ajax({
        url:'find_through_books.do',
        type:'POST',
        data:{'qu':qu,'xian':xian},
        dataType:'text',
        success: function(data){
//            alert(data);
            var json = $.parseJSON(data);
            var tbody3 = $("#tbody3");
            tbody3.children().remove();
            for (var i = 0; i < json.length; i++) {
                var tr = $("<tr></tr>");
                var td1 = $("<td>"+json[i].id+"</td>");
                var td2 = $("<td>"+json[i].area+"</td>");
                var td3 = $("<td>"+json[i].date+"</td>");
                tr.append(td1).append(td2).append(td3);
                tbody3.append(tr);
            }
        }
    });
}

function FindPlanState(){
    var start = $("#startTime2").val();
    var end = $("#endTime2").val();
    var subsidizeName = $("#subsidizeName2").val();

    if (start.length>0 && check(start)==false){
        alert("请输入正确的开始时间");
        return;
    }
    if (end.length>0 && check(end)==false){
        alert("请输入正确的结束时间");
        return;
    }

    $.ajax({
        url:'find_subsidize_state.do',
        type:'POST',
        data:{'start':start,'end':end,'subsidizeName':subsidizeName},
        dataType:'text',
        success: function(data){
//            alert(data);
            var json = $.parseJSON(data);
            var tbody4 = $("#tbody4");
            tbody4.children().remove();
            for (var i = 0; i < json.length; i++) {
                var tr = $("<tr></tr>");
                var td1 = $("<td>"+json[i].name+"</td>");
                var td2 = $("<td>"+json[i].place+"</td>");
                var td3 = $("<td>"+json[i].schoolName+"</td>");
                tr.append(td1).append(td2).append(td3);
                tbody4.append(tr);
            }
        }
    });
}

function FindPassBooks1(){
    var qu = $("#select3").val();
    var xian = $("#select4").val();

    $.ajax({
        url:'find_through_books.do',
        type:'POST',
        data:{'qu':qu,'xian':xian},
        dataType:'text',
        success: function(data){
//            alert(data);
            var json = $.parseJSON(data);
            var tbody5 = $("#tbody5");
            tbody5.children().remove();
            for (var i = 0; i < json.length; i++) {
                var tr = $("<tr></tr>");
                var td1 = $("<td>"+json[i].id+"</td>");
                var td2 = $("<td>"+json[i].area+"</td>");
                var td3 = $("<td>"+json[i].date+"</td>");
                tr.append(td1).append(td2).append(td3);
                tbody5.append(tr);
            }
        }
    });
}

function FindProccessBooks(){
    var start = $("#startTime3").val();
    var end = $("#endTime3").val();
    var subsidizeName = $("#subsidizeName3").val();

    if (start.length>0 && check(start)==false){
        alert("请输入正确的开始时间");
        return;
    }
    if (end.length>0 && check(end)==false){
        alert("请输入正确的结束时间");
        return;
    }

    $.ajax({
        url:'find_subsidize_state.do',
        type:'POST',
        data:{'start':start,'end':end,'subsidizeName':subsidizeName},
        dataType:'text',
        success: function(data){
//            alert(data);
            var json = $.parseJSON(data);
            var tbody6 = $("#tbody6");
            tbody6.children().remove();
            for (var i = 0; i < json.length; i++) {
                var tr = $("<tr></tr>");
                var td1 = $("<td>"+json[i].name+"</td>");
                var td2 = $("<td>"+json[i].place+"</td>");
                var td3 = $("<td>"+json[i].schoolName+"</td>");
                tr.append(td1).append(td2).append(td3);
                tbody6.append(tr);
            }
        }
    });
}

function FindCompleteBooks(){
    var start = $("#startTime4").val();
    var end = $("#endTime4").val();
    var subsidizeName = $("#subsidizeName4").val();

    if (start.length>0 && check(start)==false){
        alert("请输入正确的开始时间");
        return;
    }
    if (end.length>0 && check(end)==false){
        alert("请输入正确的结束时间");
        return;
    }

    $.ajax({
        url:'find_subsidize_state.do',
        type:'POST',
        data:{'start':start,'end':end,'subsidizeName':subsidizeName},
        dataType:'text',
        success: function(data){
//            alert(data);
            var json = $.parseJSON(data);
            var tbody7 = $("#tbody7");
            tbody7.children().remove();
            for (var i = 0; i < json.length; i++) {
                var tr = $("<tr></tr>");
                var td1 = $("<td>"+json[i].name+"</td>");
                var td2 = $("<td>"+json[i].place+"</td>");
                var td3 = $("<td>"+json[i].schoolName+"</td>");
                tr.append(td1).append(td2).append(td3);
                tbody7.append(tr);
            }
        }
    });
}

function FindCompletePass(){
    var qu = $("#select5").val();
    var xian = $("#select6").val();

    $.ajax({
        url:'find_through_books.do',
        type:'POST',
        data:{'qu':qu,'xian':xian},
        dataType:'text',
        success: function(data){
//            alert(data);
            var json = $.parseJSON(data);
            var tbody8 = $("#tbody8");
            tbody8.children().remove();
            for (var i = 0; i < json.length; i++) {
                var tr = $("<tr></tr>");
                var td1 = $("<td>"+json[i].id+"</td>");
                var td2 = $("<td>"+json[i].area+"</td>");
                var td3 = $("<td>"+json[i].date+"</td>");
                tr.append(td1).append(td2).append(td3);
                tbody8.append(tr);
            }
        }
    });
}

function Init(){
    title2();
    title1();
}