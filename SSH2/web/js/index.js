function CheckSeesion(){
    $.ajax({
        url: 'check_session.do',
        type: 'POST',
        data: {},
        dataType: 'text',
        success: function(data){
//            alert(data);
            var json = $.parseJSON(data);
            if (json.state=="success"){
                $("#btnLogin").html(json.name);
            }
            else {
                $("#btnLogin").html("登陆/注册");
            }
        }
    });
}


function SubmitLogin(){
    $(".spinner").show();
	var inputName = $("#inputName").val();
	var inputPassword = $("#inputPassword").val();
	if (inputName=="" | inputPassword=="")
		return;
	
	$.ajax({
		url: 'login_submit.do',
		type: 'POST',
		data: {'userName':inputName,'password':inputPassword},
		dataType: 'text',
		success: function(data){
//			alert(data);
			var json = $.parseJSON(data);
			if (json.state=='success'){
				$("#btnLogin").html($("#inputName").val());
				$("#btnLogin").unbind('click');
				$("#divLogin").hide();
                $(".spinner").hide();
            }
			else{
				$("#divAlert").attr('class','alert alert-danger');
                $("#divAlert").children('p').html("<h4>登陆失败，请检查登陆信息是否正确</h4>");
                $("#divAlert").show();
                $(".spinner").hide();
			}
		}
	});
	
}

function QuiteLogin(){
    $.ajax({
        url: 'clear_session.do',
        type: 'POST',
        data: {},
        dataType: 'text',
        success: function(data){
            var json = $.parseJSON(data);
            if (json.state=="success"){
                $("#btnLogin").html("登陆/注册");
            }
        }
    });
}

function init(){
	$("#divLogin").css(
        {   position: "absolute",
            top: "100px",left: "500px",right: "400px",bottom: "250px"
            //backgroundColor: "#F8F8F8"
        }).hide();

    $("#divAlert").css(
        {   position: "absolute",
            top: "100px",left: "500px",right: "400px",bottom: "400px"
        }).hide();

    $(".spinner").hide();
    
    //打开登陆窗口
    $("#btnLogin").live('click',function(){
    	$("#inputName").val("");
    	$("#inputPassword").val("");
    	$("#divLogin").show(100);
    });
    //关闭登陆窗口
    $("#closeLogin").click(function(){
    	$("#divLogin").hide();
    });
    //提交登陆请求
    $("#submitLogin").click(SubmitLogin);
    //退出登陆
    $("#quiteLogin").click(QuiteLogin);
    //关闭弹出窗口
    $("#closeAlert").click(function(){
        $("#divAlert").hide();
    });
    //检查session,确认是否登陆
    CheckSeesion();

}