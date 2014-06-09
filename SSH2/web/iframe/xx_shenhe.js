/**
 * Created by yongjie on 14-6-8.
 */

function show1(){
    $("#shenhe_shenqing").show();
    $("#shenhe_guihua").hide();
    $("#shenhe_jungong").hide();

    $("#one").attr('class','active');
    $("#two").removeClass('active');
    $("#three").removeClass('active');
}

function show2(){

    $("#shenhe_shenqing").hide();
    $("#shenhe_guihua").show();
    $("#shenhe_jungong").hide();

    $("#two").attr('class','active');
    $("#one").removeClass('active');
    $("#three").removeClass('active');
}

function show3(){

    $("#shenhe_shenqing").hide();
    $("#shenhe_guihua").hide();
    $("#shenhe_jungong").show();

    $("#three").attr('class','active');
    $("#one").removeClass('active');
    $("#two").removeClass('active');
}

function FindArea(){
    $.ajax({
        url:'find_area_xian.do',
        type:'POST',
        data:{},
        dataType:'text',
        success: function(data){
            var select2 = $("#select2");
            select2.children().remove();
            var select4 = $("#select4");
            select4.children().remove();
            var select6 = $("#select6");
            select6.children().remove();
            var json = $.parseJSON(data);
            for (var i = 0; i < json.length; i++) {
                var option1 = $("<option>"+json[i].name+"</option>");
                var option2 = $("<option>"+json[i].name+"</option>");
                var option3 = $("<option>"+json[i].name+"</option>");
                select2.append(option1);
                select4.append(option2);
                select6.append(option3);
            }
        }
    });

    $.ajax({
        url:'find_area_qu.do',
        type:'POST',
        data:{},
        dataType:'text',
        success: function(data){
            var select1 = $("#select1");
            select1.children().remove();
            var select3 = $("#select3");
            select3.children().remove();
            var select5 = $("#select5");
            select5.children().remove();
            var json = $.parseJSON(data);
            for (var i = 0; i < json.length; i++) {
                var option1 = $("<option>"+json[i].name+"</option>");
                var option2 = $("<option>"+json[i].name+"</option>");
                var option3 = $("<option>"+json[i].name+"</option>");
                select1.append(option1);
                select3.append(option2);
                select5.append(option3);
            }
        }
    });
}

function chaxun_shenqinshu(){

    var qu = $("#select1").val();
    var xian = $("#select2").val();

    $.ajax({
        url:'find_req_books.do',
        type:'POST',
        data:{'qu':qu,'xian':xian},
        dataType:'text',
        success: function(data){
            var tbody1 = $("#tbody1");
            tbody1.children().remove();
            var json = $.parseJSON(data);
            for (var i = 0; i < json.length; i++) {
                var tr = $("<tr></tr>");
                var td1 = $("<td>"+json[i].id+"</td>");
                var td2 = $("<td>"+json[i].area+"</td>");
                var td3 = $("<td>"+json[i].date+"</td>");
                tr.append(td1).append(td2).append(td3);
                tbody1.append(tr);
            }
        }
    });
}

function chaxun_guihuashu(){
    var qu = $("#select3").val();
    var xian = $("#select4").val();
    $.ajax({
        url:'find_req_books.do',
        type:'POST',
        data:{'qu':qu,'xian':xian},
        dataType:'text',
        success: function(data){
            var tbody2 = $("#tbody2");
            tbody2.children().remove();
            var json = $.parseJSON(data);
            for (var i = 0; i < json.length; i++) {
                var tr = $("<tr></tr>");
                var td1 = $("<td>"+json[i].id+"</td>");
                var td2 = $("<td>"+json[i].area+"</td>");
                var td3 = $("<td>"+json[i].date+"</td>");
                tr.append(td1).append(td2).append(td3);
                tbody2.append(tr);
            }
        }
    });
}

function chaxun_jungong(){
    var qu = $("#select5").val();
    var xian = $("#select6").val();
    $.ajax({
        url:'find_req_books.do',
        type:'POST',
        data:{'qu':qu,'xian':xian},
        dataType:'text',
        success: function(data){
            var tbody3 = $("#tbody3");
            tbody3.children().remove();
            var json = $.parseJSON(data);
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


function Init(){
    show1();
    FindArea();
}
