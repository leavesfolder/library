<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/1 0001
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>
<form action="smartUpload.do" method="post" enctype="multipart/form-data">
    <input name="myfile1" type="file"><br/>
    <input name="myfile2" type="file"><br/>
    <input name="myfile3" type="file"><br/>
    <input type="submit" value="提交">${result}
</form>

下载<a href="smartDownload.do?filename=kkx.png">卡卡西帅照</a>


<form action="plSmartDownload.do" method="post">
    <input type="checkbox" name="filename" value="kkx.png">卡卡西
    <input type="checkbox" name="filename" value="xiaoyin.png">小樱
    <input type="checkbox" name="filename" value="chutian.png">雏田
    <input type="submit" value="下载">
</form>
</body>
</html>
