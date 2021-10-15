<%--
  Created by IntelliJ IDEA.
  User: niuho
  Date: 2021/10/15
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String req = "request作用域参数";
    String ses = "session作用域参数";
    //分别将这两个参数存放到request作用域和session作用域中
    request.setAttribute("req",req);
    session.setAttribute("session",ses);
    //将页面重定向到session02.jsp
    response.sendRedirect("session02.jsp");
%>
</body>
</html>
