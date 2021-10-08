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
<%@ include file="control.jsp"%>
<%
    //添加登录校验 用户没有登录 不给他访问登录成功页面
    //登录成功页面 显示用户名信息
    //重定向虽然不能携带请求参数 但是由于两次请求都是属于同一个会话
    //所以可以通过session来获取用户的信息
    out.print("欢迎你:");
    out.print(session.getAttribute("username"));
%>
<h5><a href="home.jsp">主页</a></h5>
<h5><a href="shopcar.jsp">购物车</a></h5>
<h6><a href="logout.jsp">退出</a>></h6>
</body>
</html>
