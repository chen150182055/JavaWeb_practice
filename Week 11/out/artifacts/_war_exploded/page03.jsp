<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chenxihon123
  Date: 2021/11/12
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- 测试使用JSTL里面的set 标签 -->
<%
    //jstl里面的set标签实际上就相当于setAttribute方法的
    //如果我想要在request作用域中存取 名字叫做tom的username变量
    //request.setAttribute("username","tom")
%>
<!-- 我们可以使用JSTL标签来达到同样的效果 -->
<c:set var="username" value="tom" scope="request"></c:set>
${username }
<!-- JSTL标签的作用就是减少java代码在jsp页面的编写量 -->
<br>
<c:out value="username"></c:out><br>
<c:out value="${username }"></c:out><br>
${password }<!-- 当变量为空的时候 el表达式没有东西显示 如果我们需要给个默认值 -->
<!-- out标签就是可在变量为空的情况下 给他一个默认值 -->
<c:out value="${password }" default="密码为空"></c:out>
<!-- 如果想要移除request作用域中的username变量 调用removeAttribute -->
<%

%>
<c:remove var="username"/>
<c:out value="${username }" default="username为空"></c:out>
</body>
</html>
