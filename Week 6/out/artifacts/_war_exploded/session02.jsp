<%--
  Created by IntelliJ IDEA.
  User: niuho
  Date: 2021/10/15
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>request:<%=request.getAttribute("req") %></h2>
<h2>application:<%=session.getAttribute("ses") %></h2>
</body>
</html>
