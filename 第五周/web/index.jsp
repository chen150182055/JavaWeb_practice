<%--
  Created by IntelliJ IDEA.
  User: niuho
  Date: 2021/10/8
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>获取session编号</title>
  </head>
  <body>
    <%
        out.print("获取sessionId: "+ session.getId());
    %>
  </body>
</html>
