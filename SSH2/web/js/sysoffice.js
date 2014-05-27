function tab1() {
    $("#div1").show();
    $("#div2").show();
    $("#div3").hide();
    $("#jisuanqi").hide();
    $("#div4").hide();
    $("#tab1").attr("class", "active");
    $("#tab2").removeClass("active");

}

function tab2() {
    $("#div1").hide();
    $("#div2").hide();
    $("#div3").show();
    $("#xuanzhongshanchu").hide();
    $("#div4").hide();
    $("#tab2").attr("class", "active");
    $("#tab1").removeClass("active");
    $("#til_faxinxiang").hide();
    $("#til_lajixiang").hide();
    $("#til_shouxinxiang").hide();

}

var selected = null;
function listAction() {
    $(this).css({background: "#DBEAF9" });
    $(this).siblings().css({background: "" });
    selected = $(this);
}

function init() {
    //收件箱交互
    jh_shoujian();

    $("#div3").hide();
    $("#div4").hide();
    $("#xiexin").hide();
    $("#fasong").hide();
    $("#shanchu").hide();
    $("#til_faxinxiang").hide();
    $("#til_lajixiang").hide();
    $("#neirong").hide();
    $("#xuanzhongshanchu").show();
    $("#til_shouxinxiang").show();

}


//收件箱交互
function jh_shoujian() {
    $.ajax({
        url: 'file_receive.do',
        type: 'POST',
        data: {},
        dataType: 'text',
        success: function (data) {
            //alert(data);
            var _shouxin = $("#_shouxin");
            _shouxin.children().remove();
            var json = $.parseJSON(data);
            var weidu_no = 0;
            for (var i = 0; i < json.length; i++) {
                if (i % 2 == 1)
                    var tr = $("<tr class='active'></tr>");
                if (i % 2 == 0)
                    var tr = $("<tr ></tr>");
                var td1 = $("<td onclick='sj_duxin(this)'>" + json[i].haoma + "</td>");
                if (json[i].zhuangtai == 0) {
                    var td2 = $("<td onclick='sj_duxin(this)'><img src=\"img/weidu.jpg\" ></td>");
                    weidu_no++;
                }
                if (json[i].zhuangtai == 1)
                    var td2 = $("<td onclick='sj_duxin(this)'><img src=\"img/yidu.jpg\" ></td>");
                if (json[i].fujian == 1)
                    var td3 = $("<td onclick='sj_duxin(this)'><img src=\"img/fujian.jpg\" ></td>");
                if (json[i].fujian == 0)
                    var td3 = $("<td onclick='sj_duxin(this)'> </td>");
                var td4 = $("<td onclick='sj_duxin(this)'>" + json[i].fajianren + "</td>");
                var td5 = $("<td onclick='sj_duxin(this)'>" + json[i].zhuti + "</td>");
                var td6 = $("<td onclick='sj_duxin(this)'>" + json[i].fajianshijian + "</td>");
                var td7 = $("<td style='display: none'>" + json[i].neirong + "</td>");
                var td8 = $("<td><input type=\"checkbox\"  class=\"checkbox\" name=\"preDelCheck\" ></td>");

                tr.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td8).append(td7);
                _shouxin.append(tr);
            }
            var sp = $("#tag1");
            sp.html(weidu_no);

        }
    });
}

//发件箱交互
//var num = 1;
function jh_fajian() {
    $.ajax({
        url: 'file_send.do',
        type: 'POST',
        data: {},
        dataType: 'text',
        success: function (data) {
            //alert(data);
            var _shouxin = $("#_faxin");
            _shouxin.children().remove();
            var json = $.parseJSON(data);

            for (var i = 0; i < json.length; i++) {
                if (i % 2 == 1)
                    var tr = $("<tr class='active'></tr>");
                if (i % 2 == 0)
                    var tr = $("<tr ></tr>");
                var td1 = $("<td onclick='sj_duxin(this)'>" + json[i].haoma + "</td>");
                /*if (json[i].zhuangtai == 0) {
                    var td2 = $("<td onclick='sj_duxin(this)'><img src=\"img/weidu.jpg\" ></td>");

                }
                if (json[i].zhuangtai == 1)
                    var td2 = $("<td onclick='sj_duxin(this)'><img src=\"img/yidu.jpg\" ></td>");*/
                var td2 = $("<td onclick='sj_duxin(this)'><img src=\"img/yidu.jpg\" ></td>");
                if (json[i].fujian == 1)
                    var td3 = $("<td onclick='sj_duxin(this)'><img src=\"img/fujian.jpg\" ></td>");
                if (json[i].fujian == 0)
                    var td3 = $("<td onclick='sj_duxin(this)'> </td>");
                var td4 = $("<td onclick='sj_duxin(this)'>" + json[i].fajianren + "</td>");
                var td5 = $("<td onclick='sj_duxin(this)'>" + json[i].zhuti + "</td>");
                var td6 = $("<td onclick='sj_duxin(this)'>" + json[i].fajianshijian + "</td>");
                var td7 = $("<td style='display: none'><p>" + json[i].neirong + "</p></td>");
                var td8 = $("<td><input type=\"checkbox\"  class=\"checkbox\" name=\"preDelCheck\" ></td>");

                tr.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td8).append(td7);
                _shouxin.append(tr);
            }

        }
    });
}


//删除箱交互
function jh_shanchu() {
    $.ajax({
        url: 'file_delete_find.do',
        type: 'POST',
        data: {},
        dataType: 'text',
        success: function (data) {
            //alert(data);
            var _shouxin = $("#_shanchu");
            _shouxin.children().remove();
            var json = $.parseJSON(data);
            for (var i = 0; i < json.length; i++) {
                if (i % 2 == 1)
                    var tr = $("<tr class='active'></tr>");
                if (i % 2 == 0)
                    var tr = $("<tr ></tr>");
                var td1 = $("<td onclick='sj_duxin(this)'>" + json[i].haoma + "</td>");
               /* if (json[i].zhuangtai == 0) {
                    var td2 = $("<td onclick='sj_duxin(this)'><img src=\"img/weidu.jpg\" ></td>");

                }
                if (json[i].zhuangtai == 1)
                    var td2 = $("<td onclick='sj_duxin(this)'><img src=\"img/yidu.jpg\" ></td>");*/
                var td2 = $("<td onclick='sj_duxin(this)'><img src=\"img/yidu.jpg\" ></td>");
                if (json[i].fujian == 1)
                    var td3 = $("<td onclick='sj_duxin(this)'><img src=\"img/fujian.jpg\" ></td>");
                if (json[i].fujian == 0)
                    var td3 = $("<td onclick='sj_duxin(this)'> </td>");
                var td4 = $("<td onclick='sj_duxin(this)'>" + json[i].fajianren + "</td>");
                var td5 = $("<td onclick='sj_duxin(this)'>" + json[i].zhuti + "</td>");
                var td6 = $("<td onclick='sj_duxin(this)'>" + json[i].fajianshijian + "</td>");
                var td7 = $("<td style='display: none'>" + json[i].neirong + "</td>");
                var td8 = $("<td><input type=\"checkbox\"  class=\"checkbox\" name=\"preDelCheck\" ></td>");

                tr.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td8).append(td7);
                _shouxin.append(tr);
            }
            var sp = $("#tag2");
            sp.html(json.length);
        }
    });
}

function xiexin() {

    $("#shouxin").hide();
    $("#fasong").hide();
    $("#shanchu").hide();
    $("#xiexin").show();
    $("#xuanzhongshanchu").hide();
    $("#til_faxinxiang").hide();
    $("#til_lajixiang").hide();
    $("#til_shouxinxiang").hide();
    $("#neirong").hide();

}


function shouxin() {
    jh_shoujian();
    $("#fasong").hide();
    $("#shanchu").hide();
    $("#xiexin").hide();
    $("#til_faxinxiang").hide();
    $("#til_lajixiang").hide();
    $("#shouxin").show();
    $("#xuanzhongshanchu").show();
    $("#til_shouxinxiang").show();
    $("#neirong").hide();
}


function fasong() {
    jh_fajian();
    $("#fasong").show();
    $("#til_faxinxiang").show();
    $("#shanchu").hide();
    $("#xiexin").hide();
    $("#shouxin").hide();
    $("#xuanzhongshanchu").show();
    $("#til_shouxinxiang").hide();
    $("#til_lajixiang").hide();
    $("#neirong").hide();
}

function shanchu() {

    $("#fasong").hide();
    $("#shanchu").show();
    $("#xiexin").hide();
    $("#shouxin").hide();
    $("#xuanzhongshanchu").show();
    $("#til_faxinxiang").hide();
    $("#til_shouxinxiang").hide();
    $("#til_lajixiang").show();
    $("#neirong").hide();
    jh_shanchu();
}


//全选或者取消
function selectAllDels() {
    var allCheckBoxs = document.getElementsByName("preDelCheck");
    var desc = document.getElementById("allChecked");
    var selectOrUnselect = false;
    for (var i = 0; i < allCheckBoxs.length; i++) {
        if (allCheckBoxs[i].checked) {
            selectOrUnselect = true;
            break;
        }
    }
    if (selectOrUnselect) {
        _allUnchecked(allCheckBoxs);
    } else {
        _allchecked(allCheckBoxs);
    }
}
function _allchecked(allCheckBoxs) {
    for (var i = 0; i < allCheckBoxs.length; i++) {
        allCheckBoxs[i].checked = true;
    }
}
function _allUnchecked(allCheckBoxs) {
    for (var i = 0; i < allCheckBoxs.length; i++) {
        allCheckBoxs[i].checked = false;
    }
}


/////////////////////////////shanchu

function selectAllDels1() {
    var allCheckBoxs = document.getElementsByName("preDelCheck1");
    var desc = document.getElementById("allChecked1");
    var selectOrUnselect = false;
    for (var i = 0; i < allCheckBoxs.length; i++) {
        if (allCheckBoxs[i].checked) {
            selectOrUnselect = true;
            break;
        }
    }
    if (selectOrUnselect) {
        _allUnchecked(allCheckBoxs);
    } else {
        _allchecked(allCheckBoxs);
    }
}


///////////////////////////////////fasong

function selectAllDels2() {
    var allCheckBoxs = document.getElementsByName("preDelCheck2");
    var desc = document.getElementById("allChecked2");
    var selectOrUnselect = false;
    for (var i = 0; i < allCheckBoxs.length; i++) {
        if (allCheckBoxs[i].checked) {
            selectOrUnselect = true;
            break;
        }
    }
    if (selectOrUnselect) {
        _allUnchecked(allCheckBoxs);
    } else {
        _allchecked(allCheckBoxs);
    }
}


//////////////////////////////////----------------------------------------------------------


function delxuanzhong() {

    var receive = [];
    var send = [];
    var rubbish = [];
    var tab = document.getElementById("shouxin");
    for (var i = tab.rows.length - 1; i > 0; i--) {
        if (tab.rows[i].cells[6].getElementsByTagName('input')[0].checked) {
            var num = tab.rows[i].cells[0].innerHTML;
            receive.push(num);
            tab.deleteRow(i);
        }
    }
    var tab = document.getElementById("fasong");
    for (var i = tab.rows.length - 1; i > 0; i--) {
        if (tab.rows[i].cells[6].getElementsByTagName('input')[0].checked) {
            var num = tab.rows[i].cells[0].innerHTML;
            send.push(num);
            tab.deleteRow(i);
        }
    }
    var tab = document.getElementById("shanchu");
    for (var i = tab.rows.length - 1; i > 0; i--) {
        if (tab.rows[i].cells[6].getElementsByTagName('input')[0].checked) {
            var num = tab.rows[i].cells[0].innerHTML;
            rubbish.push(num);
            tab.deleteRow(i);
        }
    }

    //删除收件箱
    if (receive.length>0){
        $.ajax({
            url:'delete_receive.do',
            type:'POST',
            data:{'deleteReceiveId':receive.join("|")},
            dataType:'text'
        });
    }

    //删除发件箱
    if (send.length>0){
        $.ajax({
            url:'delete_send.do',
            type:'POST',
            data:{'deleteSendId':sned.join("|")},
            dataType:'text'
        });
    }

    //删除垃圾箱
    if (rubbish.length>0){
        $.ajax({
            url:'delete_rubbish.do',
            type:'POST',
            data:{'deleteRubbishId':rubbish.join("|")},
            dataType:'text'
        });
    }
}


function date() {
    $('.datepicker').datepicker();
}


//读信
function neirong() {

    var sp = $("#tag1").html("");


    sp = $("#nr_neirong");
    sp.innerHTML = "";
    $("#shouxin").hide();
    $("#fasong").hide();
    $("#shanchu").hide();
    $("#xiexin").hide();
    $("#xuanzhongshanchu").hide();
    $("#til_faxinxiang").hide();
    $("#til_lajixiang").hide();
    $("#til_shouxinxiang").hide();
    $("#neirong").show();

}

function sj_duxin(temp) {
    sp = $("#nr_neirong");

    sp.empty();

    neirong();
    var p = temp.parentNode;
    var nr = p.cells[7].innerHTML;
    sp.append(nr);

    var fileId = p.cells[0].innerHTML;
    $.ajax({
        url:'file_read.do',
        type:'POST',
        data:{'fileId':fileId},
        dataType:'text',
        success:function(data){}
    });



    t_zhuti = $("#nr_zhuti");
    t_fajianren = $("#nr_fajianren");
    t_shoujianren = $("#nr_shoujianren");
    t_shijian = $("#nr_shijian");

    t_zhuti.empty();
    t_fajianren.empty();
    t_shoujianren.empty();
    t_shijian.empty();


    t_zhuti.append("主题:" + p.cells[4].innerHTML);
    t_fajianren.append("发件人:" + p.cells[3].innerHTML);
    t_shoujianren.append("收件人:" + p.cells[3].innerHTML);
    t_shijian.append("时 &nbsp; 间:" + p.cells[5].innerHTML);


}


function CurentTime() {
    var now = new Date();

    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日

    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分

    var clock = year + "-";

    if (month < 10)
        clock += "0";

    clock += month + "-";

    if (day < 10)
        clock += "0";

    clock += day + " ";

    if (hh < 10)
        clock += "0";

    clock += hh + ":";
    if (mm < 10) clock += '0';
    clock += mm;
    return(clock);
}






