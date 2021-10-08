<%--
  Created by IntelliJ IDEA.
  User: niuho
  Date: 2021/10/8
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //添加登录校验 用户没有登录 不给他访问登录成功页面
    String username =(String)session.getAttribute("username");
    if (username==null){
        //如果用户名为空
        response.sendRedirect("login.jsp");
    }
    //登录成功页面
    //重定向虽然不能携带请求参数 但是由于两次请求都是属于同一个会话
    //所以可以通过session来获取用户的信息
    out.print("欢迎你:");
    out.print(session.getAttribute("username"));
%>
</body>
</html>
