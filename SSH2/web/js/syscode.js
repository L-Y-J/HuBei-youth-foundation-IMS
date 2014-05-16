/**
 * Created by yongjie on 14-5-9.
 */

function close(){
    $("#divAddDepartment").hide();
}

//查询各级部门，添加到左侧表格中
var num = null;
function SelectLevalOne(){
    num = $(this).attr('class').split(" ");

    $.ajax({
        url: 'department_one.do',
        type: 'POST',
        data: {'number':num[1]},
        dataType: 'text',
        success: function(data){
//            alert(data);
            var tbody1 = $("#tbody1");
            tbody1.children().remove();
            var json = $.parseJSON(data);
            for (var i=0; i<json.length; i++){
                var tr = $("<tr class='active'></tr>");
                var td1 = $("<td>"+json[i].id+"</td>");
                var td2 = $("<td>"+json[i].name+"</td>");
                tr.append(td1).append(td2);
                tbody1.append(tr);
            }
        }
    });
}

var department = null;
function Tbody1Selected(){
    if (department!=null){
        department.attr('class','active');
    }
    department = $(this);
    department.attr('class','success');

    var departmentId = department.children().html();

    $.ajax({
        url: 'department_job.do',
        type: 'POST',
        data: {'departmentId':departmentId},
        dataType: 'text',
        success: function(data){
//            alert(data);
            var tbody2 = $("#tbody2");
            var json = $.parseJSON(data);
            for (var i=0; i<json.length; i++){
                var tr = $("<tr class='info'></tr>");
                var td1 = $("<td>"+json[i].id+"</td>");
                var td2 = $("<td>"+json[i].name+"</td>");
                tr.append(td1).append(td2);
                tbody2.append(tr);
            }
        }
    });
}

var job = null;
function Tbody2Selected(){
    if (job!=null){
        job.attr('class','info');
    }
    job = $(this);
    job.attr('class','danger');

}

//添加部门
function AddDepartment(){
    if (num==null){     //没有选择部门级别
        $("#divAlert").attr('class','alert alert-danger');
        $("#divAlert").children('p').html("<h4>请先选择部门级别</h4>");
        $("#divAlert").show();
        return;
    }

    $("#divAddDepartment").show(150);
//    var departmentName = $("#departmentName").val();
//    alert(departmentName);
}


function Init(){
    $("#divAddDepartment").css(
        {   position: "absolute",
            top: "100px",left: "300px",right: "400px",bottom: "200px",
            zIndex: "1000",
            backgroundColor: "#F8F8F8"
        }).hide();
    $("#divAlert").css(
        {   position: "absolute",
            top: "100px",left: "500px",right: "400px",bottom: "400px"
        }).hide();

    $("#close1").click(close);
    $("#close2").click(close);
    $("#closeAlert").click(function(){
        $("#divAlert").hide();
    });

    //查询省级部门
    $(".leval").click(SelectLevalOne);
    //部门被选中的操作
    $(".active").live('click',Tbody1Selected);
    //岗位被选中的操作
    $(".info").live('click',Tbody2Selected);
    //添加部门
    $("#addDepartment").click(AddDepartment);
}