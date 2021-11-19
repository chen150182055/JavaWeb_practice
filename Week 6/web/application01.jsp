<%--
  Created by IntelliJ IDEA.
  User: niuho
  Date: 2021/10/15
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  String ses = "session作用域";
  String app = "application作用域";
  //分别将他们存放到session和application作用域中
  session.setAttribute("ses",ses);
  application.setAttribute("app",app);
  response.sendRedirect("application02.jsp");
%>
</body>
</html>
