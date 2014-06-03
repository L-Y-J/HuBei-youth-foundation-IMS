/**
 * Created by yongjie on 14-6-2.
 */

function check(time)
{
    var date = time;
    var result = date.match(/((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/);
    if(result==null)
    {
        return false;
    }
    return true;

}

var num = 0;
function allChecked(){
    if (num%2==0)
        $(".check").attr('checked','true');
    else
        $(".check").removeAttr('checked');
    num++;
}

//查询未资助的学生
function findStudent(){
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var a = true;
    var b = true;

    if (startTime!="")
        a = check(startTime);
    if (endTime!="")
        b = check(endTime);

    if (a!=true || b!=true){
        alert("请输入正确的时间格式");
        return;
    }

    var school = $("#school").val();
    var studentNumber = $("#studentNumber").val();
    var studentName = $("#studentName").val();

    $.ajax({
        url:'find_no_student.do',
        type:'POST',
        data:{'startTime':startTime,
            'endTime':endTime,
            'school':school,
            'studentNumber':studentNumber,
            'studentName':studentName},
        dataType:'text',
        success:function(data){
            var body = $("#tbody");
            body.children().remove();
            var json = $.parseJSON(data);
            for (var i=0; i<json.length; i++){
                var tr = $("<tr></tr>");
                var td1 = $("<td>"+json[i].id+"</td>");
                var td2 = $("<td>"+json[i].name+"</td>");
                var td3 = $("<td>"+json[i].school+"</td>");
                var td4 = $("<td>"+json[i].date+"</td>");
                var td5 = $("<td>"+json[i].connect+"</td>");
                var td6 = $("<td></td>");
                var td7 = $("<input type='checkbox' class='check' />");
                td6.append(td7);
                tr.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6);
                body.append(tr);
            }
        }
    });

}

//新增未资助的学生
var isAdd=false;
function addStudent(){
    if (isAdd==true)
        return;
    isAdd = true;
    var tbody = $("#tbody");
    var tr = $("<tr class='addTag'></tr>");
    var td1 = $("<td></td>");
    var td2 = $("<td></td>");
    var td3 = $("<td></td>");
    var td4 = $("<td></td>");
    var td5 = $("<td></td>");
    var td6 = $("<td></td>");
    var input1 = $("<input id='input1' type='text' style='width: 80px' disabled='true' />");
    var input2 = $("<input id='input2' type='text' style='width: 80px' />");
    var input3 = $("<input id='input3' type='text' style='width: 80px' />");
    var input4 = $("<input id='input4' type='text' style='width: 80px' />");
    var input5 = $("<input id='input5' type='text' style='width: 80px' />");
    var input6 = $("<input onclick='submitAdd()' type='button' value='确定' class='btn-default' />");
    td1.append(input1);
    td2.append(input2);
    td3.append(input3);
    td4.append(input4);
    td5.append(input5);
    td6.append(input6);
    $(tr).append(td1).append(td2).append(td3).append(td4).append(td5).append(td6);
    tbody.append(tr);
}


//提交新增的学生
function submitAdd(){
    isAdd = false;
    var studentName = $("#input2").val();
    var school = $("#input3").val();
    var studentDate = $("#input4").val();
    var connect = $("#input5").val();

    if (check(studentDate)==false){
        alert("请输入正确的时间格式");
        return;
    }
    if (studentName.length==0){
        alert("请输入学生名称");
        return;
    }
    if (school.length==0){
        alert("请输入学校");
        return;
    }

    $(".addTag").remove();

    $.ajax({
        url:'add_no_student.do',
        type:'POST',
        data:{'studentName':studentName,
            'school':school,
            'studentDate':studentDate,
            'connect':connect},
        dataType:'text',
        success:function(data){
            var json = $.parseJSON(data);
            if (json.state=="failed")
                alert("学生添加失败");
        }
    });
}

//获取选中的学生id
function deleteStudent(){
    var list = [];
    $(".check:checked").each(function(){
        var number = $(this).parent().parent().children(0).html();
        list.push(number);
        $(this).parent().parent().remove();
    });
    if (list.length>0)
        submitDelete(list);
}


//提交删除的学生id
function submitDelete(list){
    $.ajax({
        url:'delete_student.do',
        type:'POST',
        data:{'studentList':list.join("|")},
        dataType:'text'
    });
}


//选中编辑的行
var td;
function editStudent(){
    if ($(".check:checked").length>1){
        alert("请选择其中一个学生进行编辑");
        return;
    }

    $(".check:checked").each(function(){
        td = $(this).parent().parent().children();
    });

    var i=0;
    $(td).each(function(){
        if (i!=0 && i<td.length-1){
            var input = $("<input type='text' style='width: 100px' />");
            input.val($(this).html());
            $(this).html(input);
        }

        if (i==td.length-1){
            var input = $("<input onclick='UpdateStudent()' type='button' style='width: 60px' value='更改' class='btn-default' />");
            $(this).html(input);
        }

        i++;
    });
}

//更改学生信息
function UpdateStudent(){
    var id,name,school,date,connect;

    var i=0;
    $(td).each(function(){
        switch (i){
            case 0: id = $(this).html();break;
            case 1: name = $(this).children().val();$(this).html(name);break;
            case 2: school = $(this).children().val();$(this).html(school);break;
            case 3: date = $(this).children().val();$(this).html(date);break;
            case 4: connect = $(this).children().val();$(this).html(connect);break;
            case 5: $(this).html($("<input type='checkbox' class='check' />"));
                break;
        }
        i++;
    });

    date = date.substring(0,10);
    if (check(date)==false){
        alert("请输入正确的时间格式");
        return;
    }

    $.ajax({
        url:'update_no_student.do',
        type:'POST',
        data:{'id':id,'name':name,'school':school,'date':date,'connect':connect},
        dataType:'text'
    });
}


