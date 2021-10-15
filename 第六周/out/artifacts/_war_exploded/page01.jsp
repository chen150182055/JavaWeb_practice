<%--
  Created by IntelliJ IDEA.
  User: niuho
  Date: 2021/10/15
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //在这个页面中我们先定义一个变量
    String name="page";
    // 然后将这个变量放到pageContext页面作用域中
    pageContext.setAttribute("name",name);
%>
<h2>pageOne:<%=pageContext.getAttribute("name") %></h2>
<%--我们可以调用pageContext里面的include方法引入另外一个页面的显示内容--%>
<%
    //这样子我们就可以在一个页面中显示不同的页面
    pageContext.include("page02.jsp");
    //pageContext.include跟< %@ include 指令是不一样的
    //pageContext.include导入引入页面的显示结果
    //< %@include引入页面代码 相当于把代码直接拼接到后面去
%>
<%--下面会显示pageTwo：page因为他直接将代码引入过来了--%>
<%@include file="page02.jsp"%>
</body>
</html>
