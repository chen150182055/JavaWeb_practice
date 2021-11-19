<%--
  Created by IntelliJ IDEA.
  User: niuho
  Date: 2021/10/8
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //用户退出登录的逻辑代码
    //如何判断用户是否有登录 检查session中是否有用户的信息
    //如果我们销毁了session信息 那么用户就没有登录了
    //销毁用户的session信息
    session.invalidate();
    //然后用户退出后就回到登录页面
    response.sendRedirect("login.jsp");
%>
</body>
</html>
