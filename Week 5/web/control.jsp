<%--
  Created by IntelliJ IDEA.
  User: niuho
  Date: 2021/10/8
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- 我们可以将这段代码单独的抽离出来 放到一个文件里
     接着使用指令include指令将这段代码导入
-->
<%
    String username =(String)session.getAttribute("username");
    if (username==null){
        //如果用户名为空
        response.sendRedirect("login.jsp");
    }%>
</body>
</html>
