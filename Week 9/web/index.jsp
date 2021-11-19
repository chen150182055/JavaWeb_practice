<%--
  Created by IntelliJ IDEA.
  com.gec.entity.User: niuho
  Date: 2021/11/5
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<!-- 导入User的包 -->
<%@ page import="com.gec.entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL表达式的作用</title>
</head>
<body>
<%
    //EL表达式的作用 取出数据 直接显示在JSP页面中
    pageContext.setAttribute("namw", "Tommmm");
    //先将一个数据 存放到request作用域中
    request.setAttribute("name", "Timi");
    //创建User对象 并将他存放到session作用域中
    User user01 = new User("admin","admin");
    session.setAttribute("user01",user01);
%>
<!-- 使用EL表达式将name变量数据 显示在页面中 -->
<!--  按照page -> request -> session -> application的作用域顺序依次查找
        找到即返回 最终找不到返回NULL-->
<h2>${name }</h2>
<h2>${pageScope.name }</h2>
<!-- 如果我一定要取request作用域中寻找数据的话
        requestScope 表示request 作用域的意思-->

<h2>${requestScope.name }</h2>
<!-- 使用EL表达式来访问对象中的属性值(数据) -->
<!-- 访问session中user01的对象的username值 -->
<%=((User)session.getAttribute("user01")).getUsername()%>
<!-- 使用EL表达式 因为四大作用域里面只有一个user01 所以可以省略作用域名 -->
<!-- EL表达式获取对象中的数据 就是采用get访问方法 -->

${user01.username}
<!-- 用下面的方法也是OK的 -->
${user01["username"]}
</body>
</html>
