function tab1(){
	$("#div1").show();
	$("#div2").show();
	$("#div3").hide();
    $("#div4").hide();
    $("#tab1").attr("class","active");
    $("#tab2").removeClass("active");
    $("#tab3").removeClass("active");
}

function tab2(){
	$("#div1").hide();
	$("#div2").hide();
	$("#div3").show();
    $("#div4").hide();
    $("#tab2").attr("class","active");
    $("#tab1").removeClass("active");
    $("#tab3").removeClass("active");

    //查询角色类别
    $.ajax({
        url:'list_role_class.do',
        type: 'POST',
        data: {},
        dataType: 'text',
        success: function(data){
            // alert(data);
            //添加到角色列表中
            list = $.parseJSON(data);
            var tbody = $("#tbody1");
            tbody.children().remove();
            for (var i=0; i<list.length; i++){
                var tr = $("<tr class='tr_role'></tr>");
                var td1 = $("<td>"+list[i].id+"</td>");
                var td2 = $("<td>"+list[i].name+"</td>");

                tr.append(td1).append(td2);
                tbody.append(tr);
            }
        }
    });
}

function tab3(){
	$("#div1").hide();
	$("#div2").hide();
	$("#div3").hide();
    $("#div4").show();
    $("#tab3").attr("class","active");
    $("#tab1").removeClass("active");
    $("#tab2").removeClass("active");
}

//关闭弹出窗口
function close(){
    $("#div5").hide();
}
function close1(){
    $("#div6").hide();
}
function close2(){
    $("#div7").hide();
}
function close3(){
    $("#div8").hide();
}
function close4(){
    $("#div9").hide();
}
function close5(){
    $("#div10").hide();
}
function close6(){
    $("#div11").hide();
}
function close7(){
    $("#div12").hide();
}

var currentUserName = null;
var currentUserId = null;

//权限列表被选中的响应操作
var selected = null;
function listSelected(){
    $(this).css({background: "#DBEAF9" });
    $(this).siblings().css({background: "" });
    selected = $(this);

    //查询被选中的权限的详细信息
    var em = selected.children().children();
    var span = em.children();
    var id = span.text();
    $.ajax({
        url:'order_one.do',
        type:'POST',
        data:{'userId':currentUserId,'userName':currentUserName,'orderId':id},
        dataType:'text',
        success:function (data) {
            // alert(data);
            var json = $.parseJSON(data);
            //在右侧输入框显示被选中的权限信息
            $("#example1").val(json.orderName);
            $("#example2").val(json.orderId);
            $("#example3").val(json.remark);
        }
    });
}

//添加权限时弹出窗口
function btn_new(){
    //查询系统所有权限
    if (currentUserId==null | currentUserName==null){
        close();
        $("#div8").attr('class','alert alert-danger');
        var test = $("#div8").children('p');
        test.html("<h4>请选定用户，再新增权限！<h4>");
        $("#div8").show();
        return;
    }

    $.ajax({
        url:'order_list.do',
        type:'POST',
        data:{},
        dataType:'text',
        success: function(data){
//            alert(data);
            var json = $.parseJSON(data);
            var power = $("#powerTable");
            power.children().remove();
            for(var i=0; i<json.length; i++){
                var tr = $("<tr class='active'></tr>");
                var td1 = $("<td>"+json[i].id+"</td>");
                var td2 = $("<td>"+json[i].name+"</td>");
                tr.append(td1).append(td2);
                power.append(tr);
            }
        }
    });
    $("#div5").show(300);
}

//为用户添加权限，权限被选中的操作
var power = null;
 function powerSelected(){
    if (power!=null){
        power.attr('class','active');
    }
    power = $(this);
    power.attr('class','success');
}

//选择用户时弹出窗口,向数据库查询用户列表并显示
var tr_selected1 = null;
function btn_edit(){
    //查询用户列表
    $.ajax({
        url:'user_list.do',
        type:'POST',
        data:"{}",
        dataType:'text',
        success:function (data) {
            //添加到窗体表格中
//        	alert(data);
        	var list = eval('(' + data + ')');
        	//添加查询的数据到窗口中
            if (list==null){
            	alert("查询失败，请重新查询");
            	return;
            }
            var table = $("#user");
            table.children().remove();
            for (var i=0; i<list.length; i++){
            	var tr = $("<tr class=\"tr\"></tr>");          //设置class属性，匹配动态绑定的函数
            	var td1 = $("<td>"+list[i].id+"</td>");
            	var td2 = $("<td>"+list[i].name+"</td>");
            	
            	tr.append(td1).append(td2);
            	table.append(tr);
            }
            //显示窗口
            $("#div6").show(300);
        }
    });

}

//用户被确认，向数据库查询用户权限，加入权限列表
function confirmUser(){
    if (tr_selected1==null){
        alert("请选定用户");
        return;
    }
    //得到被选中的用户名称，id
    var temp = tr_selected1.children();
    var id  = temp[0].innerHTML;
    var name = temp[1].innerHTML;

    currentUserId = id;
    currentUserName = name;
    // alert(id+name);

    //向数据库查询权限
    $.ajax({
        url:'user_order.do',
        type:'POST',
        data: {'id':id,'name':name},
        dataType:'text',
        success:function (data) {
        	// alert(data);
            var list = eval('(' + data + ')');
            if (list==null){
                alert("查询失败，请重新查询");
            }
            //将权限添加到列表中
            $(".list-group-item").remove();
            for (var i=0; i<list.length; i++){
                id = list[i].id;
                name = list[i].name;
                listAppend(name+"&nbsp&nbsp&nbsp",id);
            }
            close1();
        }
    });
}

//保存新增的权限
function save(){
    var td = power.children('td');
    var powerId = td[0].innerHTML;
    var powerName = td[1].innerHTML;

    //添加到数据库
    $.ajax({
        url:'order_add.do',
        type:'POST',
        data: {'id':powerId,'userId':currentUserId},
        dataType:'text',
        success:function (data) {
            // alert(data);
            //添加到权限列表中
            var json = $.parseJSON(data);
            if (json.state=='success'){
                listAppend(powerName+"&nbsp&nbsp&nbsp",powerId);
                $("#div8").attr("class","alert alert-success");
                $("#div8").children('p').html('<h4>权限添加成功！<h4>');
                $("#div8").show();
                close();       //关闭div5,添加权限完成
            }
            else{
                $("#div8").attr('class','alert alert-danger');
                $("#div8").children('p').html('<h4>权限添加失败！<h4>');
                $("#div8").show();
                close();
            }
        }
    });
}

//删除权限的响应操作
function btn_del(){
    if (selected==null){
        alert("请选择需要删除的权限");
    }
    else{
        var em = selected.children().children();
        var span = em.children();
        var id = span.text();
        //同步删除数据库的权限
        $.ajax({
            url:'order_del.do',
            type:'POST',
            data: {'orderId':id, 'userId':currentUserId, 'userName':currentUserName},
            dataType:'text',
            success:function (data) {
                // alert(data);
                var json = $.parseJSON(data);
                if (json.state=='success'){
                    selected.remove();
                    $("#div8").attr("class","alert alert-success");
                    $("#div8").children('p').html('<h4>权限删除成功！</h4>');
                    $("#div8").show();
                }
                else{
                    $("#div8").attr('class','alert alert-danger');
                    $("#div8").children('p').html('<h4>权限删除失败！</h4>');
                    $("#div8").show();
                }
            }
        });
    }
}

//向权限列表添加权限的操作
function listAppend(em_value,span_value){
    var parent = $(".list-group");
    var div = $("<div class=\"list-group-item\"></div>");
    var h4 = $("<h4 class=\"list-group-item-heading\"></h4>");
    var em = $("<em class=\"glyphicon glyphicon-info-sign\">"+em_value+"</em>");
    var span = $("<span class=\"badge\">"+span_value+"</span>");

    em.append(span);
    h4.append(em);
    div.append(h4);
    parent.append(div);
}

//角色类别被选中的响应操作
var tr_selected = null;
function tbody1Selected(){
    $(this).attr("class","danger");
    if (tr_selected != null){
        tr_selected.attr("class","tr_role");
    }
    tr_selected = $(this);
    
    //查询这一角色类别中的角色
    var role_class=tr_selected.children();
    var classId = role_class[0].innerHTML;
    var className = role_class[1].innerHTML;
    $.ajax({
        url: 'class_roles.do',
        type: 'POST',
        data: {'classId':classId,'className':className},
        dataType: 'text',
        success:function(data){
//            alert(data);
            var list = $.parseJSON(data);
            var parent = $("#tbody2");
            parent.children().remove();
            for (var i=0; i<list.length; i++){
                var tr = $("<tr class=\"role\"></tr>");
                var td_id = $("<td>"+list[i].id+"</td>");
                var td_name = $("<td>"+list[i].name+"</td>");

                tr.append(td_id).append(td_name);
                parent.append(tr);
            }
        }
    });
}

function btn_new1(){
    $("#div7").show(300);
}

//保存新增的角色类别
function save1(){
    var className = $("#input3").val();
    if (className==null | className==""){
    	$("#div8").attr('class','alert alert-danger');
    	$("#div8").children('p').html("<h4>角色类别名称不能为空！<h4>");
    	$("#div8").show();
    	close2();
    	return;
    }
    close2();
    $.ajax({
        url: 'add_class.do',
        type: 'POST',
        data: {'className':className},
        dataType: 'text',
        success: function(data){
            // alert(data);
            var json = $.parseJSON(data);
            if (json.state=='success'){
                //添加新增类别到列表中
                var tbody1 = $("#tbody1");
                var tr = $("<tr class='tr_role'></tr>");
                var td1 = $("<td>"+json.id+"</td>");
                var td2 = $("<td>"+className+"</td>");
                tr.append(td1).append(td2);
                tbody1.append(tr);
                $("#div8").attr('class','alert alert-success');
                $("#div8").children('p').html("<h4>类别添加成功！<h4>");
                $("#div8").show();
            }
            else{
                //错误提示
                $("#div8").attr('class','alert alert-danger');
                $("#div8").children('p').html("<h4>新增类别失败！<h4>");
                $("#div8").show();
            }
        }
    });
}

//角色类别授权时，显示权限列表
function orderlist(){
	if (tr_selected==null){
		$("#div8").attr('class','alert alert-danger');
		$("#div8").children('p').html("请先选择角色类别，再进行授权！");
		$("#div8").show();
		return;
	}
	
	$("#order_confirm").unbind('click',RoleOrderChange);
	$("#order_confirm").click(classOrderChange);
	
	var td = tr_selected.children('td');
	var roleClassId = td.html();
	//查询所有权限，添加到权限列表中
	$.ajax({
		url: 'select_orders.do',
		type: 'POST',
		data: {'roleClassId':roleClassId},
		dataType: 'text',
		success: function(data){
//			alert(data);
			json = $.parseJSON(data);
			var tbody = $("#tbody3");
			tbody.children().remove();
			for (var i=0; i<json.length; i++){
				var tr = $("<tr class='class_order'></tr>");
				var td1 = $("<td>"+json[i].orderId+"</td>");
				var td2 = $("<td>"+json[i].orderName+"</td>");
				if (json[i].own=='1'){
					orderList = [];
					orderList.push(json[i].orderId);
					var td3 = $("<td></td>");
					var span = $('<span class="glyphicon glyphicon-ok"></span>');
					td3.append(span);
					tr.append(td1).append(td2).append(td3);
					tbody.append(tr);
				}
				else{
					tr.append(td1).append(td2);
					tbody.append(tr);
				}
			}
		}
	});
	$("#div9").show();
}

//角色类别授权，权限被选中
var orderList = [];
function classOrderSelected(){
	var tr = $(this);
	var span = tr.find('span');
	if (span.length==0){
		orderList.push(tr.children().html());
		var td = $("<td></td>");
		span = $('<span class="glyphicon glyphicon-ok"></span>');
		td.append(span);
		tr.append(td);
	}
	else{
		var name = tr.children().html();
		var index = $.inArray(name,orderList);
		orderList.splice(index,1);
		tr.find('span').parent('td').remove();
	}
}

//提交对角色类别权限的改变
function classOrderChange(){
	close4();
	var classId = tr_selected.children('td').html();
	
	//提交orderList保存的权限ID
	$.ajax({
		url: 'class_order_change.do',
		type: 'POST',
		data: {'orderList':orderList.join("|"),'classId':classId},
		dataType: 'text',
		success: function(data){
//			alert(data);
			var json = $.parseJSON(data);
			if (json.state=="success"){
				$("#div8").attr('class','alert alert-success');
				$("#div8").children('p').html("<h4>权限更改成功！</h4>");
				$("#div8").show();
			}
			else{
				$("#div8").attr('class','alert alert-danger');
				$("#div8").children('p').html("<h4>权限更改失败！</h4>");
				$("#div8").show();
			}
		}
	});
}

//删除选中的角色类别
function delRoleClass(){
	if (tr_selected==null){
		$("#div8").attr('class','alert alert-danger');
		$("#div8").children('p').html("<h4>请指定需要删除的角色类别！</h4>");
		$("#div8").show();
		return;
	}
	
	var roleClassId = tr_selected.children().html();
	$.ajax({
		url:'del_class.do',
		type: 'POST',
		data: {'roleClassId':roleClassId},
		dataType: 'text',
		success: function(data){
//			alert(data);
			var json = $.parseJSON(data);
			if (json.state=="success"){
				//删除列表中的角色类别
				tr_selected.remove();
				tr_selected = null;
				$("#div8").attr('class','alert alert-success');
				$("#div8").children('p').html("<h4>角色类别删除成功！</h4>");
				$("#div8").show();
			}
			else{
				$("#div8").attr('class','alert alert-danger');
				$("#div8").children('p').html("<h4>角色类别删除失败！</h4>");
				$("#div8").show();
			}
		}
	});
}

function btn_new2(){
	$("#div10").show();
}

//具体角色被选中的响应函数
var role_selected = null;
function tbody2Selected(){
    $(this).attr('class','success');
    if (role_selected!=null){
        role_selected.attr('class','role');
    }
    role_selected = $(this);
}

//保存新增的系统角色
function save2(){
	close5();
	if (tr_selected==null){
		$("#div8").attr('class','alert alert-danger');
		$("#div8").children('p').html("<h4>请先在左侧指定角色类别！<h4>");
    	$("#div8").show();
    	return;
	}
	
	var roleName = $("#input4").val();
	if (roleName==null | roleName==""){
    	$("#div8").attr('class','alert alert-danger');
    	$("#div8").children('p').html("<h4>角色名称不能为空！<h4>");
    	$("#div8").show();
    	return;
    }
    var role_class=tr_selected.children();
    var classId = role_class[0].innerHTML;
    
    $.ajax({
    	url: 'save_sys_role.do',
    	type: 'POST',
    	data: {'roleName':roleName,'classId':classId},
    	dataType: 'text',
    	success: function(data){
//    		alert(data);
    		var json = $.parseJSON(data);
    		if (json.state=="success"){
    			var tbody2 = $("#tbody2");
    			var tr = $("<tr class='role'></tr>");
    			var td1 = $("<td>"+json.roleId+"</td>");
    			var td2 = $("<td>"+roleName+"</td>");
    			tr.append(td1).append(td2);
    			tbody2.append(tr);
    			
    			$("#div8").attr('class','alert alert-success');
		    	$("#div8").children('p').html("<h4>系统角色添加成功！<h4>");
		    	$("#div8").show();
    		}
    		else{
    			$("#div8").attr('class','alert alert-danger');
		    	$("#div8").children('p').html("<h4>系统角色添加失败！<h4>");
		    	$("#div8").show();
    		}
    	}
    });
	
}

//移动系统角色
function moveRole(){
	if (role_selected==null){
		$("#div8").attr('class','alert alert-danger');
    	$("#div8").children('p').html("<h4>请先指定系统角色！<h4>");
    	$("#div8").show();
    	return;
	}
	$.ajax({
        url:'list_role_class.do',
        type: 'POST',
        data: {},
        dataType: 'text',
        success: function(data){
//        	alert(data);
        	var json = $.parseJSON(data);
        	var tbody4 = $("#tbody4");
        	tbody4.children().remove();
        	for (var i=0; i<json.length; i++){
        		var tr = $("<tr class='classlist'></tr>");
        		var td1 = $("<td>"+json[i].id+"</td>");
        		var td2 = $("<td>"+json[i].name+"</td>");
        		tr.append(td1).append(td2);
        		tbody4.append(tr);
        	}
        }
	});
	$("#div11").show();
}

//指定角色移动到哪一个类别
var movetoclass_selected = null;
function moveToClassSelected(){
	var tr = $(this);
	tr.attr('class','danger');
	if (movetoclass_selected!=null){
		movetoclass_selected.attr('class','classlist');
	}
	movetoclass_selected = tr;
}

//确认移动
function ConfirmMove(){
	if (movetoclass_selected==null){
		return;
	}
	
	close6();
	var oldClassId = tr_selected.children().html();
	var newClassId = movetoclass_selected.children().html();
	var roleId = role_selected.children().html();
	
	$.ajax({
		url: 'move_role.do',
		type: 'POST',
		data: {'oldClassId':oldClassId,'newClassId':newClassId,'roleId':roleId},
		dataType: 'text',
		success: function(data){
//			alert(data);
			var json = $.parseJSON(data);
			if (json.state=="success"){
				role_selected.remove();
				role_selected = null;
				$("#div8").attr('class','alert alert-success');
				$("#div8").children('p').html("<h4>角色移动成功！</h4>");
				$("#div8").show();
			}
			else{
				$("#div8").attr('class','alert alert-danger');
				$("#div8").children('p').html("<h4>角色移动失败！</h4>");
				$("#div8").show();
			}
		}
	});
}

//删除选中的系统角色
function DelRole(){
	if (role_selected==null){
		$("#div8").attr('class','alert alert-danger');
		$("#div8").children('p').html("<h4>请先指定系统角色！</h4>");
		$("#div8").show();
		return;
	}
	
	var classId = tr_selected.children().html();
	var roleId = role_selected.children().html();
	
	$.ajax({
		url: 'del_role.do',
		type: 'POST',
		data: {'classId':classId,'roleId':roleId},
		dataType: 'text',
		success: function(data){
//			alert(data);
			var json = $.parseJSON(data);
			if (json.state=="success"){
				role_selected.remove();
				role_selected = null;
				$("#div8").attr('class','alert alert-success');
				$("#div8").children('p').html("<h4>角色删除成功！</h4>");
				$("#div8").show();
			}
			else{
				$("#div8").attr('class','alert alert-danger');
				$("#div8").children('p').html("<h4>角色删除失败！</h4>");
				$("#div8").show();
			}
		}
	});
}

//系统角色授权，显示权限列表
function orderlist1(){
	if (role_selected==null){
		$("#div8").attr('class','alert alert-danger');
		$("#div8").children('p').html("请先选择系统角色，再进行授权！");
		$("#div8").show();
		return;
	}
	
	$("#order_confirm").unbind('click',classOrderChange);
	$("#order_confirm").click(RoleOrderChange);
	
	var td = role_selected.children('td');
	var roleId = td.html();
	//查询所有权限，添加到权限列表中
	$.ajax({
		url: 'select_role_orders.do',
		type: 'POST',
		data: {'roleId':roleId},
		dataType: 'text',
		success: function(data){
//			alert(data);
			json = $.parseJSON(data);
			var tbody = $("#tbody3");
			tbody.children().remove();
            orderList = [];
			for (var i=0; i<json.length; i++){
				var tr = $("<tr class='class_order'></tr>");
				var td1 = $("<td>"+json[i].orderId+"</td>");
				var td2 = $("<td>"+json[i].orderName+"</td>");
				if (json[i].own=='1'){
					orderList.push(json[i].orderId);
					var td3 = $("<td></td>");
					var span = $('<span class="glyphicon glyphicon-ok"></span>');
					td3.append(span);
					tr.append(td1).append(td2).append(td3);
					tbody.append(tr);
				}
				else{
					tr.append(td1).append(td2);
					tbody.append(tr);
				}
			}
		}
	});
	$("#div9").show();
}

//角色成员权限更改提交
function RoleOrderChange(){
	close4();
	var roleId = role_selected.children('td').html();

    var li = orderList.join("|");
	//提交orderList保存的权限ID
	$.ajax({
		url: 'role_order_change.do',
		type: 'POST',
		data: {'orderList':li,'roleId':roleId},
		dataType: 'text',
		success: function(data){
//			alert(data);
			var json = $.parseJSON(data);
			if (json.state=="success"){
				$("#div8").attr('class','alert alert-success');
				$("#div8").children('p').html("<h4>权限更改成功！</h4>");
				$("#div8").show();
			}
			else{
				$("#div8").attr('class','alert alert-danger');
				$("#div8").children('p').html("<h4>权限更改失败！</h4>");
				$("#div8").show();
			}
		}
	});
}

//选择用户级别，添加到列表中
var userLeval = "";
function SelectUser(){
	var user = $(this);
	if (user.attr('id')=='user1'){
        userLeval = "省级用户";
	}
	if (user.attr('id')=='user2'){
        userLeval = "市级用户";
	}
	if (user.attr('id')=='user3'){
        userLeval = "县级用户";
	}
	
	$.ajax({
		url: 'select_user.do',
		type: 'POST',
		data: {'userLeval':userLeval},
		dataType: 'text',
		success: function(data){
//			alert(data);
			var json = $.parseJSON(data);
			var tbody5 = $("#tbody5");
			tbody5.children().remove();
			user_selected = null;
			for (var i=0; i<json.length; i++){
				var tr = $("<tr class='tr_user'></tr>");
				var td1 = $("<td>"+json[i].id+"</td>");
				var td2 = $("<td>"+json[i].name+"</td>");
				tr.append(td1).append(td2);
				tbody5.append(tr);
			}
		}
	});
}

//每一个用户被选中时，查询用户的详细信息，添加到右边
var user_selected = null;
function UserSelected(){
	$(this).attr('class','success');
	if (user_selected!=null){
		user_selected.attr('class','tr_user');
	}
	user_selected = $(this);
	
	var userId = user_selected.children().html();
	
	$.ajax({
		url: 'user_info.do',
		type: 'POST',
		data: {'userId':userId},
		dataType: 'text',
		success: function(data){
//			alert(data);
			var json = $.parseJSON(data);
			$("#username").val(json.userName);
			$("#name").val(json.name);
			$("#innerid").val(json.innerId);
			$("#password").val(json.passWord);
		}
	});
}

//显示或隐藏密码
function ShowPassWord(){
	var span = $(this).find('span');
	var input = $(this).next('input').get(0);
	if (span.attr('class')=='glyphicon glyphicon-eye-open'){
		span.attr('class','glyphicon glyphicon-eye-close');
		input.type='password';
	}
	else{
		span.attr('class','glyphicon glyphicon-eye-open');
		input.type='text';
	}
}

//删除用户
function DelUser(){
	if (user_selected==null){
		$("#div8").attr('class','alert alert-danger');
		$("#div8").find('p').html("<h4>请先选择用户！</h4>");
		$("#div8").show();
		return;
	}
	
	var userId = user_selected.find('td').html();
	$.ajax({
		url: 'del_user.do',
		type: 'POST',
		data: {'userId':userId},
		dataType: 'text',
		success: function(data){
			var json = $.parseJSON(data);
			if (json.state=='success'){
				user_selected.remove();
				user_selected = null;
				$("#username").val("");
				$("#name").val("");
				$("#innerid").val("");
				$("#password").val("");
				$("#confirmpassword").val("");
				$("#div8").attr('class','alert alert-success');
				$("#div8").find('p').html('<h4>用户删除成功！</h4>');
				$("#div8").show();
			}
			else{
				$("#div8").attr('class','alert alert-danger');
				$("#div8").find('p').html('<h4>用户删除失败！</h4>');
				$("#div8").show();
			}
		}
	});
	
}

//新增用户
function NewUser(){
	if(userLeval==""){
		$("#div8").attr('class','alert alert-danger');
		$("#div8").find('p').html("<h4>请先选择用户级别！</h4>");
		$("#div8").show();
		return;
	}
	$("#username").val("");
	$("#name").val("");
	$("#innerid").val("");
	$("#password").val("");
	$("#confirmpassword").val("");
	$("#username").focus();
}

//新增或修改用户后提交
function UserSubmit(){
	var userName = $("#username").val();
	var name = $("#name").val();
	var innerId = $("#innerid").val();
	var password = $("#password").val();
	var confirmPassword = $("#confirmpassword").val();
	if (userName=="" | name=="" | innerId=="" | password==""){
		return;
	}
	
	if (password!=confirmPassword){
		$("#div8").attr('class','alert alert-danger');
		$("#div8").find('p').html('<h4>确认的密码与原密码不一致喔，请仔细检查！</h4>');
		$("#div8").show();
		return;
	}
	
	var userId;
	if (user_selected==null){
		userId = -1;
	}
	else{
		userId = user_selected.find('td').html();
	}
	
	$.ajax({
		url: 'update_user.do',
		type: 'POST',
		data: {'userId':userId,'userName':userName,'name':name,'innerId':innerId,'password':password},
		dataType: 'text',
		success: function(data){
//			alert(data);
			var json = $.parseJSON(data);
			if (json.state=='success'){
				$("#div8").attr('class','alert alert-success');
				$("#div8").find('p').html('<h4>提交成功！</h4>');
				$("#div8").show();
			}
			else{
				$("#div8").attr('class','alert alert-danger');
				$("#div8").find('p').html('<h4>提交失败！</h4>');
				$("#div8").show();
			}
		}
	});
}

//用户授权
function UserOrder(){
	if (user_selected==null){
		$("#div8").attr('class','alert alert-danger');
		$("#div8").find('p').html('<h4>先选定用户才能授权啦！</h4>');
		$("#div8").show();
		return;
	}
	var userId = user_selected.find('td').html();
	$.ajax({
		url: 'user_order_list.do',
		type: 'POST',
		data: {'userId':userId},
		dataType: 'text',
		success: function(data){
//			alert(data);
			var json = $.parseJSON(data);
			var tbody6 = $("#tbody6");
			tbody6.children().remove();
			for (var i=0; i<json.length; i++){
				var tr = $("<tr class='user_order'></tr>");
				var td1 = $("<td>"+json[i].id+"</td>");
				var td2 = $("<td>"+json[i].name+"</td>");
				if (json[i].own=='1'){
					userOrderList = [];
					userOrderList.push(json[i].id);
					var td3 = $("<td></td>");
					var span = $('<span class="glyphicon glyphicon-ok"></span>');
					td3.append(span);
					tr.append(td1).append(td2).append(td3);
					tbody6.append(tr);
				}
				else{
					tr.append(td1).append(td2);
					tbody6.append(tr);
				}
			}
		}
	});
	$("#div12").show();	
}

//用户权限列表被点击时响应
userOrderList = [];
function UserOrderSelected(){
	var tr = $(this);
	var span = tr.find('span');
	if (span.length==0){
		userOrderList.push(tr.children().html());
		var td = $("<td></td>");
		span = $('<span class="glyphicon glyphicon-ok"></span>');
		td.append(span);
		tr.append(td);
	}
	else{
		var name = tr.children().html();
		var index = $.inArray(name,userOrderList);
		userOrderList.splice(index,1);
		tr.find('span').parent('td').remove();
	}
}

//提交用户权限的更改
function UserOrderChange(){
	close7();
	var userId = user_selected.find('td').html();
	
	//提交userOrderList保存的权限ID
	$.ajax({
		url: 'user_order_change.do',
		type: 'POST',
		data: {'userOrderList':userOrderList.join("|"),'userId':userId},
		dataType: 'text',
		success: function(data){
//			alert(data);
			var json = $.parseJSON(data);
			if (json.state=="success"){
				$("#div8").attr('class','alert alert-success');
				$("#div8").children('p').html("<h4>权限更改成功！</h4>");
				$("#div8").show();
			}
			else{
				$("#div8").attr('class','alert alert-danger');
				$("#div8").children('p').html("<h4>权限更改失败！</h4>");
				$("#div8").show();
			}
		}
	});
}

//页面初始化函数
function init(){
    $("#div3").hide();
    $("#div4").hide();

    //设置弹出窗口的样式
    $("#div5").css(
    {   position: "absolute",
        top: "100px",left: "300px",right: "400px",bottom: "200px",
        zIndex: "1000",
        backgroundColor: "#F8F8F8"
    }).hide();
    $("#div6").css(
        {   position: "absolute",
            top: "100px",left: "300px",right: "400px",bottom: "200px",
            zIndex: "1000",
            backgroundColor: "#F8F8F8"
        }).hide();
    $("#div7").css(
        {   position: "absolute",
            top: "100px",left: "300px",right: "400px",bottom: "200px",
            zIndex: "1000",
            backgroundColor: "#F8F8F8"
        }).hide();
    $("#div8").css(
        {   position: "absolute",
            top: "100px",left: "500px",right: "400px",bottom: "400px"
        }).hide();
    $("#div9").css(
        {   position: "absolute",
            top: "100px",left: "300px",right: "400px",bottom: "200px",
            zIndex: "1000",
            backgroundColor: "#F8F8F8"
        }).hide();
    $("#div10").css(
        {   position: "absolute",
            top: "100px",left: "300px",right: "400px",bottom: "200px",
            zIndex: "1000",
            backgroundColor: "#F8F8F8"
        }).hide();
    $("#div11").css(
        {   position: "absolute",
            top: "100px",left: "300px",right: "400px",bottom: "200px",
            zIndex: "1000",
            backgroundColor: "#F8F8F8"
        }).hide();
	$("#div12").css(
        {   position: "absolute",
            top: "100px",left: "300px",right: "400px",bottom: "200px",
            zIndex: "1000",
            backgroundColor: "#F8F8F8"
        }).hide();
        
    //关闭各个弹出窗口
    $("#close1").click(close);
    $("#close2").click(close);
    $("#close3").click(close1);
    $("#close4").click(close1);
    $("#close5").click(close2);
    $("#close6").click(close2);
    $("#close7").click(close3);
    $("#close8").click(close4);
	$("#close9").click(close4);
	$("#close10").click(close5);
	$("#close11").click(close5);
	$("#close12").click(close6);
	$("#close13").click(close6);
	$("#close14").click(close7);
	$("#close15").click(close7);
    
    //权限列表被点击时响应
    $(".list-group-item").live('click',listSelected);
    //添加权限时弹出窗口
    $("#newbtn").click(btn_new);
    //点击选择权限
    $(".active").live('click',powerSelected);
    //删除权限的响应函数
    $("#delbtn").click(btn_del);
    //选择用户时弹出窗口
    $("#editbtn").click(btn_edit);
    //用户被确定的响应函数
    $("#select").click(confirmUser);
    //保存权限的响应函数
    $("#save").click(save);
    //动态绑定，选择用户时改变颜色
    var tr_class = $(".tr");
    tr_class.live("click",function(){
        $(this).attr("class","danger");
        if (tr_selected1 != null){
            tr_selected1.attr("class","tr");
        }
        tr_selected1 = $(this);
    });

    
    //角色类别被点击时响应
    $(".tr_role").live('click',tbody1Selected);
    //新增角色类别时弹出窗口
    $("#newbtn1").click(btn_new1);
    //确认保存新增角色类别
    $("#confirm").click(save1);
    //角色类别授权时显示窗口
    $("#orderbtn1").click(orderlist);
    //授权时权限被选中的tr
    $(".class_order").live('click',classOrderSelected);
    //确认更改角色类别权限
    $("#order_confirm").click(classOrderChange);
    //删除角色类别的权限
    $("#delbtn1").click(delRoleClass);
    
    //具体角色被点击时响应
    $(".role").live('click',tbody2Selected);
    //新增系统角色时弹出窗口
    $("#newbtn2").click(btn_new2);
    //保存新增的系统角色
    $("#new_role").click(save2);
    //移动系统角色
    $("#movebtn2").click(moveRole);
    //移动角色时，提供选择的类别列表被选中
    $(".classlist").live('click',moveToClassSelected);
    //确认移动角色
    $("#move").click(ConfirmMove);
    //删除系统角色
    $("#delbtn2").click(DelRole);
    //具体角色授权
    $("#orderbtn2").click(orderlist1);
    
    //选择省级用户,市级用户，县级用户
    $("#user1").click(SelectUser);
    $("#user2").click(SelectUser);
    $("#user3").click(SelectUser);
    //动态绑定用户列表的每一项响应
    $(".tr_user").live('click',UserSelected);
    //显示或隐藏密码
    $(".btn-xs").click(ShowPassWord);
    //新增用户
    $("#newuser").click(NewUser);
    //新增或修改用户后提交
    $("#submit").click(UserSubmit);
    //删除用户
    $("#deluser").click(DelUser);
    //用户授权
    $("#userorder").click(UserOrder);
    //用户的权限列表被点击时响应
    $(".user_order").live('click',UserOrderSelected);
    $("#updateuserorder").click(UserOrderChange);
}






























