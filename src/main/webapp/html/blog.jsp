<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/3 0003
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*,com.library.user.model.User" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" scope="request"/>
<%
   User user=(User) session.getAttribute("user");
   String yhid = user.getId();
   String yhxm = user.getUsername();
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的随笔</title>
    <link href="../css/common/icon.css" rel="stylesheet">
    <link href="../css/common/layer.css" rel="stylesheet">
    <link href="../css/common/jquery.alert.css" rel="stylesheet">
    <link href="../css/common/icon.css" rel="stylesheet">
    <link href="../css/main.css" rel="stylesheet">
    <link href="../css/blog.css" rel="stylesheet">
    <script type="text/javascript" src="../js/common/jQuery/jquery.min.js"></script>
    <script type="text/javascript" src="../js/common/jQuery/jquery.form.js"></script>
    <script type="text/javascript" src="../js/common/layer.js"></script>
    <script type="text/javascript" src="../js/common/common_js.js"></script>
    <script type="text/javascript" src="../js/common/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="../js/common/jQuery/jquery.alert.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/ckfinder/ckfinder.js"></script>
</head>
<body>
    <div class="head">
       <div class="head_logo"><img src="../img/blog.png"></div>
        <div class="header-profile">
            <div class="profile-nav">
                <span class="profile-username"></span>
                <a  class="dropdown-toggle" data-toggle="dropdown">
                    <span class="fa fa-angle-down"></span>
                </a>
                <ul class="dropdown-menu animated flipInX pull-right" role="menu">
                    <li><a href="#"><i class="fa fa-user"></i> 用户中心</a></li>
                    <li class="divider"></li>
                    <li><a id="logout" onclick="toLoginOut();" href="#" ><i class="fa fa-sign-out"></i> 退出登录</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="blogBody all">
        <div class="blogBody ml">
            <div class="pc_top_first">
                <h3>我的日记</h3>
                <button type="button" onclick="toAddNote();" class="btn btn-default btn-xs btn_plus" id='add_notebook'><i class="fa fa-plus" title="添加日志"></i></button>
                <div id="myNote"></div>
            </div>
        </div>
        <div class="blogBody nr">
            <div class="showNote"></div>
            <form class="editNoteForm" name = "UserNoteForm" action="" enctype="multipart/form-data">
                <div style="display: none;">
                    <input type="text" name="yhid" value="<%=yhid%>">
                    <input type="text" name="yhxm" value="<%=yhxm%>">
                    <input type="text" id="noteId" name="id" value="">
                </div>

                <div class="row" >
                    <div class="col-xs-8">
                        <input type="text" class="form-control" id="input_note_title" name="title" placeholder='笔记标题...'>
                    </div>
                </div>
                <div class="row">
                    <textarea rows="10" cols="80" id="body" name="body" class="ckeditor">

                    </textarea>
                </div>
            </form>
            <input class="save" type="submit" onclick="toSaveUserNote();" value="保存">
            <input class="update" type="submit" onclick="toUpdateUserNote();" value="更新">

        </div>
    </div>
<script type="text/javascript">
    //为了获取body，在提交之前需要调取这个方法
    function CKupdate() {
        for (instance in CKEDITOR.instances)
            CKEDITOR.instances[instance].updateElement();
    }
    function toSaveUserNote() {
        CKupdate();
        $.ajax({
            url:"/library/autoEntry/UserNote/insertUserNote.do",
            dataType:"json",
            type:"POST",
            data:$("form[name='UserNoteForm']").serialize(),
            success:function (data) {
                jAlert("保存成功！","提示");
                queryAllNote("<%=yhid%>");//更新列表
            }
        });
    }
    //增加日志
    function toAddNote() {
        $(".showNote").html("").hide();
        $(".editNoteForm").show();
        $("#input_note_title").val("");
        $("#noteId").val("");
        CKEDITOR.instances.body.setData("");
        $(".save").show();
        $(".update").hide();
    }
    //展示日志
    function toShowNote(id) {
        $(".save").hide();
        $(".update").hide();
        $.ajax({
            url: "/library/autoEntry/UserNote/queryUserNote.do",
            dataType: "json",
            type: "POST",
            data: {id: id},
            success: function (data) {
                var noteBoyd= data.data[0].body;
                console.log(data);
                $(".editNoteForm").hide();
                $(".showNote").html("").append(noteBoyd).show();
            }
        });
    }
    //编辑日志
    function toEditNote(id) {
        $(".showNote").html("").hide();
        $(".editNoteForm").show();
        $(".save").hide();
        $(".update").show();
        //编辑日志首先需要回填日志
        $.ajax({
            url: "/library/autoEntry/UserNote/queryUserNote.do",
            dataType: "json",
            type: "POST",
            data: {id: id},
            success: function (data) {
                if(data.status){
                    var thisNote = data.data;
                    console.log(thisNote);
                    $("#input_note_title").val(thisNote[0].title);
                    $("#noteId").val(thisNote[0].id);
                    CKEDITOR.instances.body.setData(thisNote[0].body);
                }
            }
        });

    }
    //更新日志
    function toUpdateUserNote() {
        console.log("进入更新");
        CKupdate();
        $.ajax({
            url:"/library/autoEntry/UserNote/updateUserNote.do",
            dataType:"json",
            type:"POST",
            data:$("form[name='UserNoteForm']").serialize(),
            success:function (data) {
                console.log(data);
                if(data.status){
                    jAlert("更新成功！","提示");
                    queryAllNote("<%=yhid%>");//更新列表
                }
            }

        });
    }
    
    //删除日志
    function toDelNote(id) {
        layer.confirm('确认删除此日志？', {
            btn: ['确定','取消'] //按钮
        },function(flag){
            if(flag){
                $.ajax({
                    url:"/library/autoEntry/UserNote/updateUserNote.do",
                    dataType:"json",
                    type:"POST",
                    data:{id:id,scbz:1},
                    success:function (data) {
                        if(data.status){
                            layer.closeAll('dialog');
                            jAlert("删除成功！","提示");
                            queryAllNote("<%=yhid%>");//更新列表
                        }
                    }
                });
            }

        });

    }
    //日志列表
    function queryAllNote(yhid) {
        $.ajax({
            url:"/library/autoEntry/UserNote/queryUserNote.do",
            dataType:"json",
            type:"POST",
            data:{yhid:yhid},
            success:function (data) {
                var mydata = data.data;
                $("#myNote").html("");
                $("#myNote").append("<ul class='contacts-list'>");
                var myNoteStr = "";
                for(var i = 0;i<mydata.length;i++){
                    myNoteStr+='<li class="online"><span onclick="toShowNote(\''+mydata[i].id+'\')"">'+mydata[i].title+'</span><i class="fa fa-pencil" title="编辑" onclick="toEditNote(\''+mydata[i].id+'\')""></i>'+'<i class="fa fa-trash-o" title="删除" onclick="toDelNote(\''+mydata[i].id+'\')""></i>'+'</li>';
                }
                $("#myNote").append(myNoteStr).append("<ul>");
            }
        });
    }

    $(function () {
        queryAllNote("<%=yhid%>");
        toAddNote();
    })
</script>
</body>
</html>