<%--
  Created by IntelliJ IDEA.
  User: niuho
  Date: 2021/10/8
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert title here</title>
</head>
<body>
<%
    //判断用户的账号密码是否输入正确 假设正确的账号密码是sa
    String username =request.getParameter("username");
    String password =request.getParameter("password");
    if (username.equals("sa")&&password.equals("sa")){
        //设置 系统的session的非活动时间
        //指的是用户一段时间内不操作 页面的话 就会自动退出
        session.setMaxInactiveInterval(10); //单位是s
        //登录成功 将用户个人的信息存到session中
        session.setAttribute("username",username);
        //重定向到新的模块的页面
        response.sendRedirect("success.jsp");
    }else {
        //登录失败就回去登录页面
        response.sendRedirect("login.jsp");
    }
%>
</body>
</html>
