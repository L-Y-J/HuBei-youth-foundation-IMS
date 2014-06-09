function tab1(){
	$("#div1").show();
	$("#_div1").hide();
    $("#tab1").attr("class","active");
    $("#tab2").removeClass("active");
    xs_yizizhu();
}

function tab2(){
	$("#div1").hide();
	$("#_div1").show();
	$("#tab2").attr("class","active");
    $("#tab1").removeClass("active");
    xx_chaxun();

}

function xs_yizizhu(){$("#div2").html("<iframe src='iframe/xs_yizizhu.html' frameborder=\"0\" border=0 width=\"100%\" height=\"500px\"></iframe>")}
function xs_daizizhu(){$("#div2").html("<iframe src='iframe/xs_daizizhu.html' frameborder=\"0\" border=0 width=\"100%\" height=\"500px\"></iframe>")}

function xx_dengji(){
	$("#div2").html("<iframe src='iframe/xx_dengji.html' frameborder=\"0\" border=0 width=\"100%\" height=\"500px\"></iframe>")
}

function xx_chaxun(){$("#div2").html("<iframe src='iframe/xx_chaxun.html' frameborder=\"0\" border=0 width=\"100%\" height=\"500px\"></iframe>")}
function xx_tongji(){$("#div2").html("<iframe src='iframe/xx_tongji.html' frameborder=\"0\" border=0 width=\"100%\" height=\"500px\"></iframe>")}
function xx_shenhe(){$("#div2").html("<iframe src='iframe/xx_shenhe.html' frameborder=\"0\" border=0 width=\"100%\" height=\"500px\"></iframe>")}
function xx_chakan(){$("#div2").html("<iframe src='iframe/xx_chakan.html' frameborder=\"0\" border=0 width=\"100%\" height=\"500px\"></iframe>")}
function xx_shenqingshu(){$("#div2").html("<iframe src='xx_shenqingshu.html' frameborder=\"0\" border=0 width=\"500px\" height=\"100%\"></iframe>")}
function xx_guihuashu(){$("#div2").html("<iframe src='xx_guihuashu.html' frameborder=\"0\" border=0 width=\"500px\" height=\"100%\"></iframe>")}
function xx_jungong(){$("#div2").html("<iframe src='xx_jungong.html' frameborder=\"0\" border=0 width=\"100%\" height=\"500px\"></iframe>")}
function xx_yuebao(){$("#div2").html("<iframe src='xx_yuebao.html' frameborder=\"0\" border=0 width=\"100%\" height=\"500px\"></iframe>")}
function xx_fuyan(){$("#div2").html("<iframe src='xx_fuyan.html' frameborder=\"0\" border=0 width=\"100%\" height=\"500px\"></iframe>")}

function init(){
    $("#div1").show();
    $("#_div1").hide();
    xs_yizizhu();
}





