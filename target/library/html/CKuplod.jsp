<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/3 0003
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>富文本编辑器</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/ckfinder/ckfinder.js"></script>
</head>
<body>
<form action="../showPage.action" method="post">
    <textarea rows="10" cols="80" id="editor" name="editor" class="ckeditor">
         请输入
    </textarea>
    <input type="submit" value="保存">
</form>

</body>
</html>
