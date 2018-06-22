<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/11 0011
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!--书城主页-->
    <title>library</title>
    <link href="../css/home.css" rel="stylesheet">
    <link href="../css/home/hotSales.css" rel="stylesheet">
</head>
<body>
<!--引入公用的头文件-->
<%@ include file="../common/head.html"%>
<div>
    <div class="searchbox">
        <input type="text" class="search">
        <button class="btn-lg btn-danger btn-DIY" >搜索</button>
    </div>
    <div class="MallBody">
        <div class="category">
            <div class="categoryTitle">
                <span>全部商品分类</span>
            </div>
            <br/>
            <ul>
                <li>
                    <a href="#">武侠小说</a>
                </li>
                <li>
                    <a href="#">电子书</a>
                </li>
                <li>
                    <a href="#">网络文学</a>
                </li>
                <li>
                    <a href="#">优惠卷</a>
                </li>
                <li>
                    <a href="#">近期活动</a>
                </li>
                <li>
                    <a href="#">推介贴吧</a>
                </li>
                <li>
                    <a href="#">线下联盟</a>
                </li>
            </ul>
            <div class="categoryDetail">

            </div>

        </div>
        <div class="item">
            <ul>
                <li>
                   <a href="#">图书</a>
                </li>
                <li>
                    <a href="#">电子书</a>
                </li>
                <li>
                    <a href="#">网络文学</a>
                </li>
                <li>
                    <a href="#">优惠卷</a>
                </li>
                <li>
                    <a href="#">近期活动</a>
                </li>
                <li>
                    <a href="#">推介贴吧</a>
                </li>
                <li>
                    <a href="#">线下联盟</a>
                </li>
            </ul>
        </div>
        <div class="hotSales">
            <div class="containerHotSales">
                <div id="ipresenter">
                    <div class="step" data-x="0" data-y="0" data-thumbnail="../img/hotSales/10.jpg">
                    </div>

                    <div class="step" data-x="1500" data-y="0" data-rotatex="180" data-easing="easeInOutQuint" data-pausetime="15000" >
                    </div>

                    <div class="step" data-x="3000" data-y="0" data-rotatey="180" data-pausetime="2000" >
                    </div>

                    <div class="step" data-x="4500" data-y="0" data-rotatex="180" >
                    </div>

                    <div class="step" data-x="6000" data-y="0" data-rotate="180" >
                    </div>

                    <div class="step" data-x="7500" data-y="0" data-rotatey="180" >
                    </div>

                    <div class="step" data-x="9000" data-y="0" >
                    </div>

                    <div class="step" data-x="10500" data-y="0" data-rotate="80" >
                    </div>

                    <div class="step" data-x="12000" data-y="0" data-rotatex="180" >
                    </div>

                    <div class="step" data-x="13500" data-y="0" data-rotatey="180" data-scale="0.6" >
                    </div>
                </div>
            </div>
        </div>
        <div class="goodsDIV" id="secKill">
            <div class="goodsDIVTitle">秒殺精品</div>
            <div class="goods VIPgood">
                <img src="../img/secKill/7.jpeg" class="xwcms">
            </div>
        </div>
        <div class="goodsDIV txdp" id="txdp">
            <div class="goodsDIVTitle">脱销单品</div>
        </div>
        <div class="goodsDIV" id="scrm">
            <div class="goodsDIVTitle">商城熱賣</div>
            <div class="goods VIPgood">
                <img src="../img/scrm/9.jpeg">
            </div>
        </div>
    </div>
    <div class="yqlj">
        <span>
            <h1>友情链接</h1>
        </span>
        <div class="yldb"></div>
    </div>

</div>


<script type="text/javascript">
$(function () {
    $(".categoryDetail").hide();
    $(".categoryDetail").empty();
    $(".category li").hover(function (e) {
        var val = e.currentTarget.innerText;
        $(".categoryDetail").append("<span>"+val+"详情<span/>")
        $(".categoryDetail").show();
        $(".hotSales").hide();
    },function () {
        $(".categoryDetail").hide();
        $(".hotSales").show();
        $(".categoryDetail").empty();
    })
    $(".good-btn").hide();
    $(".goods img").hover(function (e) {
        $(".good-btn").show();
    },function () {
        $(".good-btn").hide();
    })
    $('#ipresenter').iPresenter({
        animSpeed: 2000,
        timer: '360Bar',
        timerDiameter: 60,
        timerStroke: 5,
        timerPadding: 5,
        timerColor: '#000',
        timerBg: '#FFF',
        timerOpacity: 0.4,
        directionNav: false,
        controlNav: true
    });
    //此处写方法去查询库中的数据加载热销品数据
    var hotDes = "我是一只鸟<br/>"+
                 "我也就应用嘶哑的喉咙歌唱<br/>"+
                 "这被暴风雨所打击的土地<br/>"+
                 "这永远汹涌着我们的悲愤的河流<br/>"+
                 "这无止息地吹刮着的激怒的风<br/>"+
                 "和那来自林间的无比温柔的黎明<br/>"+
                 "——然后我死了<br/>"+
                 "连羽毛也腐烂在土地里面<br/>"+
                 "为什么我的眼里常含泪水<br/>"+
                 "因为我对这土地爱得深沉<br/>";
    $('#ipresenter div').each(function(i){
        if(i>0&&i<11){
            $(this).attr("data-thumbnail","../img/hotSales/"+i+".jpg");
            $(this).css("display","block");
            $(this).append("<div class='bookPhoto' style='float: left;width: 210px;'><img src='../img/hotSales/"+i+".jpg' /></div><div class='bookDes' style='float: left;width:230px;margin-left: 15px;'><span>"+hotDes+"</span></div>");
        }
    });
    $(".ipresenter-items img").each(function (i) {
        i=i+1;
        $(this).attr("src","../img/hotSales/"+i+".jpg");
    })
    $("#preloader").hide();
    createSecKillGood();
    createTxdp();
    createScrm();

})


var str = ["人若真能转世","世间若真有轮回","那麽我的爱","我们前世以前是什麽","你若曾是江南采莲的女子","我必是你皓腕下错过的那朵","是暮色吗昏昏？","是夜色吗沉沉？","是曙色吗耿耿","是怎样的天色呢？"];
var rmspstr = ["凡是遥远的地方","不是诱惑于美丽","对我们都有一种诱惑","就是诱惑于传说","即使远方的风景","并不尽如人意"];
var xds = "我叮咛你的<br/>"+
    "你说 不会遗忘<br/>"+
    "你告诉我的?<br/>"+
    "我也 全都珍藏<br/>"+
    "对于我们来说<br/>"+
    "记忆是飘不落的日子<br/>"+
    "——永远不会发黄<br/>";
function createSecKillGood() {
    var secGood = "";
    for (var i = 1;i<=6;i++){
        secGood+='<div class="goods">' +
                        '<div class="bookbox">'+
                            '<div class="box-img">'+
                              '<img src="../img/secKill/'+i+'.jpg" alt="">'+
                            '</div>'+
                            '<div class="box-content">'+
                                '<h4 class="title">Schadenfreude</h4>'+
                                '<p class="description">'+xds+'</p><br/><br/><br/>'+
                                '<button class="btn-lg btn-danger">详情</button>&nbsp&nbsp&nbsp&nbsp&nbsp'+
                                '<button class="btn-lg btn-success">购买</button>'+
                            '</div>'+
                        '</div>'+
                     '<h3>'+str[i-1]+'</h3><h3 style="color:red;">￥1'+i+'.5'+i+'</h3>' +
                '</div>';
    }
    $("#secKill").append(secGood);
}

function createScrm() {
    var secGood = "";
    for (var i = 1;i<=6;i++){
        secGood+='<div class="goods">' +
            '<div class="bookbox">'+
            '<div class="box-img">'+
            '<img src="../img/scrm/'+i+'.jpg" alt="">'+
            '</div>'+
            '<div class="box-content">'+
            '<h4 class="title">Schadenfreude</h4>'+
            '<p class="description">'+xds+'</p><br/><br/><br/>'+
            '<button class="btn-lg btn-danger">详情</button>&nbsp&nbsp&nbsp&nbsp&nbsp'+
            '<button class="btn-lg btn-success">购买</button>'+
            '</div>'+
            '</div>'+
            '<h3>'+rmspstr[i-1]+'</h3><h3 style="color:red;">￥1'+i+'.5'+i+'</h3>' +
            '</div>';
    }
    $("#scrm").append(secGood);
}

function createTxdp() {
    var secGood = "";
    for (var i = 8;i<=10;i++){
        secGood+='<div class="goods">'+
                            '<div class="bookbox">'+
                                '<div class="box-img">'+
                                   '<img src="../img/secKill/'+i+'.jpg" alt="">'+
                                '</div>'+
                                '<div class="box-content">'+
                                    '<h4 class="title">Schadenfreude</h4>'+
                                    '<p class="description">'+xds+'</p><br/><br/><br/>'+
                                    '<button class="btn-lg btn-danger">详情</button>&nbsp&nbsp&nbsp&nbsp&nbsp'+
                                    '<button class="btn-lg btn-success">购买</button>'+
                                '</div>'+
                            '</div>'+
                      '<h3>'+str[i-1]+'</h3>' +
                      '<h3 style="color:red;">￥1'+i+'.5'+i+'</h3>' +
                  '</div>';
    }
    $("#txdp").append(secGood);
}

</script>
</body>
</html>
