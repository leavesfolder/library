/**
 * Created by liuys 这里面主要封装一些公用的方法 on 2018/5/30 0030.
 */
//获取html页面URL上传递的参数
var LocString=String(window.document.location.href);
function GetQueryString(){
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}

//退出登录功能在其他地方也会调用，所以放这里
function toLoginOut(){
    layer.confirm("确认退出登录！",{
        btn:["确认","取消"]
    },function (flag) {
        if (flag){
            $.ajax({
                url:"/library/autoEntry/User/loginOut.do",
                dataType:"json",
                type:"POST",
                success:function (data) {
                    if (data.status){
                        layer.closeAll('dialog');
                        window.open("welcome.html");
                    }
                }
            })
        }

    })
}

/**必填项验证*/
function invalidMess(id,type){
    $(id).attr("placeholder",type+"不能为空!").addClass("invalid");
}
