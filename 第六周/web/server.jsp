<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: niuho
  Date: 2021/10/15
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    // 获取输入用户名文本框的值
    String username = request.getParameter("username");
    // 获取输入密码的密码框的值
    String password = request.getParameter("password");
    // 在这里进行用户名和密码匹配，在这里是将用户名和密码规定死了的。
    // 即用户名必须是"hello"，密码必须是"world"才能登录成功
    if (username.equals("hello") && password.equals("world")) {
        // 新建名为name的Cookie
        Cookie nameCookie = new Cookie("name", URLEncoder.encode(username, "utf-8"));
        // 新建名为password的Cookie
        Cookie passwordCookie = new Cookie("password", password);

        // 设置Cookie的使用路径
        nameCookie.setPath(request.getContextPath() + "/");
        passwordCookie.setPath(request.getContextPath() + "/");

        nameCookie.setMaxAge(5 * 60);
        passwordCookie.setMaxAge(5 * 60);

        // 输出到客户端
        response.addCookie(nameCookie);
        response.addCookie(passwordCookie);


        response.sendRedirect("success.jsp");

    } else {
        request.setAttribute("msg", "你的密码或者用户名错误");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
%>
</body>
</html>
