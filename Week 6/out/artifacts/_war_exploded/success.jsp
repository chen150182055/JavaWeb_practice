<%--
  Created by IntelliJ IDEA.
  User: niuho
  Date: 2021/10/15
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    out.print("欢迎你!!");
%>
<%= session.getAttribute("name")%>
</body>
</html>
