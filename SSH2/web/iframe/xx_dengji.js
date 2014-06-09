/**
 * Created by yongjie on 14-6-7.
 */

function dengji_queding(){
    $("#SubsidizeName").val($("#select1").val());
    $("#schoolArea").val($("#select2").val()+$("#select3").val());
    $("#subsidizeType").val("国外");
    $("#name").val($("#select1").val());
    $("#dengji_1").hide();
    $("#dengji_2").show();
}

function dengji_fanhui(){
    $("#dengji_2").hide();
    $("#dengji_1").show();
}

function FindSchoolInfo(){
    $.ajax({
        url:'find_subsidize.do',
        type:'POST',
        data:{},
        dataType:'text',
        success: function(data){
//            alert(data);
            var select1 = $("#select1");
            select1.children().remove();
            var json = $.parseJSON(data);
            for (var i = 0; i < json.length; i++) {
                var option = $("<option>"+json[i].name+"</option>");
                select1.append(option);
            }
        }
    });

    $.ajax({
        url:'find_area_qu.do',
        type:'POST',
        data:{},
        dataType:'text',
        success: function(data){
//            alert(data);
            var select2 = $("#select2");
            select2.children().remove();
            var json = $.parseJSON(data);
            for (var i = 0; i < json.length; i++) {
                var option = $("<option>"+json[i].name+"</option>");
                select2.append(option);
            }
        }
    });

    $.ajax({
        url:'find_area_xian.do',
        type:'POST',
        data:{},
        dataType:'text',
        success: function(data){
//            alert(data);
            var select3 = $("#select3");
            select3.children().remove();
            var json = $.parseJSON(data);
            for (var i = 0; i < json.length; i++) {
                var option = $("<option>"+json[i].name+"</option>");
                select3.append(option);
            }

        }
    });
}

function SaveLoginSchool(){
    var writeN = $("#writeName").val();
    var writeJ = $("#writeJob").val();
    var writeP = $("#writePhone").val();
    var writeF = $("#writeFax").val();
    var writeM = $("#writeMail").val();
    var writeC = $("#writeCellPhone").val();

    var SubsidizeN = $("#SubsidizeName").val();
    var SubsidizeD = $("#SubsidizeDate").val();
    var schoolN = $("#schoolName").val();
    var subsidizeT = $("#subsidizeType").val();
    var schoolComplete = $("#schoolCompleteDate").val();
    var schoolA = $("#schoolArea").val();
    var presidentN = $("#presidentName").val();
    var schoolM = $("#schoolMail").val();
    var schoolC = $("#schoolCellPhone").val();
    var schoolP = $("#schoolPhone").val();
    var schoolR = $("#schoolRemrak").val();
    var schoolI = $("#schoolIsMerge").val();
    var schoolQ = $("#select2").val();
    var schoolX = $("#select3").val();

    $.ajax({
        url:'save_writer_school.do',
        type:'POST',
        data:{'writeName':writeN,'writeJob':writeJ,'writePhone':writeP,'writeFax':writeF,
            'writeMail':writeM,'writeCellPhone':writeC,'SubsidizeName':SubsidizeN,
            'SubsidizeDate':SubsidizeD,'schoolName':schoolN,'subsidizeType':subsidizeT,
            'schoolCompleteDate':schoolComplete,'schoolArea':schoolA,'presidentName':presidentN,
            'schoolMail':schoolM,'schoolCellPhone':schoolC,'schoolPhone':schoolP,
            'schoolRemrak':schoolR,'schoolIsMerge':schoolI,'schoolQu':schoolQ,'schoolXian':schoolX},
        dataType:'text',
        success: function(data){
            alert(data);
        }
    });
}

function Init(){
    FindSchoolInfo();
}
