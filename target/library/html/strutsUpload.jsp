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
<form action="../upload.action" method="post" enctype="multipart/form-data">
    <input name="upload" type="file"><br/>
    <input name="upload" type="file"><br/>
    <input name="upload" type="file"><br/>
    <input type="submit" value="提交">${result}
</form>

<a href="download.action?filename=kkx.png">卡卡西帅照</a>
</body>
</html>
