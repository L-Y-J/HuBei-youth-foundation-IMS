/**
 * Created by yongjie on 14-5-9.
 */

function close(){
    $("#divAddDepartment").hide();
}

function close2(){
    $("#divAddJob").hide();
}

//查询各级部门，添加到左侧表格中
var num = null;
function SelectLevalOne(){
    num = $(this).attr('class').split(" ");
    var data = null;
    if (num[1]=="one"){
        data="省级部门";
    }
    if (num[1]=="two"){
        data="市级部门";
    }
    if (num[1]=="three"){
        data="县级部门";
    }

    $.ajax({
        url: 'department_one.do',
        type: 'POST',
        data: {'data':data},
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
            tbody2.children().remove();
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

}

//提交数据，保存新增部门
function SaveDepartment(){
    $("#divAddDepartment").hide();

    var departmentName = $("#departmentName").val();
//    alert(departmentName);
    var data = null;
    if (num[1]=="one"){
        data="省级部门";
    }
    if (num[1]=="two"){
        data="市级部门";
    }
    if (num[1]=="three"){
        data="县级部门";
    }

    $.ajax({
        url: 'add_department.do',
        type: 'POST',
        data:{'departName':departmentName,'data':data},
        dataType:'text',
        success:function(data){
//            alert(data);
            var json = $.parseJSON(data);
            if (json[0].state=="success"){
                $("#divAlert").attr('class','alert alert-success');
                $("#divAlert").children('p').html("<h4>部门添加成功</h4>");
                $("#divAlert").show();
            }
            else{
                $("#divAlert").attr('class','alert alert-danger');
                $("#divAlert").children('p').html("<h4>部门添加失败</h4>");
                $("#divAlert").show();
            }
        }
    });

}

function EditDepartment(){
    if (department==null){
        $("#divAlert").attr('class','alert alert-danger');
        $("#divAlert").children('p').html("<h4>请先选择部门</h4>");
        $("#divAlert").show();
    }
}

function DeleteDepartment(){
    if (department==null){
        $("#divAlert").attr('class','alert alert-danger');
        $("#divAlert").children('p').html("<h4>请先选择部门</h4>");
        $("#divAlert").show();
    }

    var departmentId = department.children().html();
//    alert(departmentId);

    $.ajax({
        url:'del_department.do',
        type:'POST',
        data:{'departmentId':departmentId},
        dataType:'text',
        success: function(data){
//            alert(data);
            var json = $.parseJSON(data);
            if (json.state=="success"){
                $("#divAlert").attr('class','alert alert-success');
                $("#divAlert").children('p').html("<h4>部门删除成功</h4>");
                $("#divAlert").show();
                department.remove();
                department = null;

            }
            else{
                $("#divAlert").attr('class','alert alert-danger');
                $("#divAlert").children('p').html("<h4>部门删除失败</h4>");
                $("#divAlert").show();
            }
        }
    });


}

function AddJob(){
    if (department==null){
        $("#divAlert").attr('class','alert alert-danger');
        $("#divAlert").children('p').html("<h4>请选择部门，再进行添加</h4>");
        $("#divAlert").show();
        return;
    }

    $("#divAddJob").show();
}

function SaveJob(){
    var jobName = $("#jobName").val();
    if (jobName==null | jobName==""){
        $("#divAlert").attr('class','alert alert-danger');
        $("#divAlert").children('p').html("<h4>请填写岗位名称</h4>");
        $("#divAlert").show();
        return;
    }

    $("#divAddJob").hide();
    var departmentId = department.children().html();

    $.ajax({
        url:'add_job.do',
        type:'POST',
        data:{'departmentId':departmentId,'jobName':jobName},
        dataType:'text',
        success: function(data){
//            alert(data);
            var json = $.parseJSON(data);
            if (json.state=="success"){
                $("#divAlert").attr('class','alert alert-success');
                $("#divAlert").children('p').html("<h4>岗位添加成功</h4>");
                $("#divAlert").show();
            }
            else{
                $("#divAlert").attr('class','alert alert-danger');
                $("#divAlert").children('p').html("<h4>岗位添加失败</h4>");
                $("#divAlert").show();
            }
        }
    });
}


//删除岗位
function DeleteJob(){
    if (job==null){
        $("#divAlert").attr('class','alert alert-danger');
        $("#divAlert").children('p').html("<h4>请选中需要删除的岗位</h4>");
        $("#divAlert").show();
        return;
    }

    var departmentId = department.children().html();
    var jobId = job.children().html();

    $.ajax({
        url:'del_job.do',
        type:'POST',
        data:{'departmentId':departmentId,'jobId':jobId},
        dataType:'text',
        success:function(data){
//            alert(data);
            var json = $.parseJSON(data);
            if(json.state=="success"){
                $("#divAlert").attr('class','alert alert-success');
                $("#divAlert").children('p').html("<h4>岗位删除成功</h4>");
                $("#divAlert").show();
                job.remove();
                job = null;
            }
            else{
                $("#divAlert").attr('class','alert alert-danger');
                $("#divAlert").children('p').html("<h4>岗位删除失败</h4>");
                $("#divAlert").show();
            }
        }
    });

}


function Init(){
    $("#divAddDepartment").css(
        {   position: "absolute",
            top: "100px",left: "300px",right: "400px",bottom: "200px",
            zIndex: "1000",
            backgroundColor: "#F8F8F8"
        }).hide();
    $("#divAddJob").css(
        {   position: "absolute",
            top: "100px",left: "300px",right: "400px",bottom: "200px",
            zIndex: "1000",
            backgroundColor: "#F8F8F8"
        }).hide();

    $("#divAlert").css(
        {   position: "absolute",
            top: "100px",left: "500px",right: "400px",bottom: "400px",
            zIndex:1000
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
    //提交数据，保存添加的部门
    $("#save").click(SaveDepartment);
    //编辑选中的部门
    $("#editDepartment").click(EditDepartment);
    //删除选中的部门
    $("#delDepartment").click(DeleteDepartment);

    //添加岗位时弹窗
    $("#addJob").click(AddJob);
    $("#close3").click(close2);
    $("#close4").click(close2);
    //提交数据，保存新增的岗位
    $("#savejob").click(SaveJob);
    //删除岗位的响应函数
    $("#delJob").click(DeleteJob);

}









