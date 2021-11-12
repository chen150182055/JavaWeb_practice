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
<!-- 如果想要在JSTL标签中能实现if语句就 要使用 c:if 标签 或者 c:choose -->
<%-- 假设一个场景用户登录成功 将用户信息存放到session 作用域中，然后要判断用户是否登录了 --%>
<c:set var="username" value="admin" scope="session"></c:set>
<%--使用if标签来进行判断 test属性是用来编写条件表达式 没有c:else
 empty 是在EL表达式里 用来判断对象是否为空的 如果为空就放回true 否则返回false
 if(条件表达式){

 }
--%>
<c:if test="${empty username }">
    <%--     如果test里面的结果为true 就会运行这里面的代码为false就不执行 --%>
    用户没有登录
</c:if>

<c:if test="${! empty username }">
    <%--     如果test里面的结果为true就会执行这里的代码 --%>
    用户登录了 欢迎${username }
</c:if>
<br>
<h1>choose: 实现if - else - if 语句的功能</h1>
<c:choose>
    <%--  if --%>
    <c:when test="${empty username }">
        <%--         如果test里面的结果为true 就会执行这里的代码 为false就不执行 --%>
        用户没有登录
    </c:when>

    <%--    else if --%>

    <%--    <c:when test="${empty username}">   --%>
    <%--    用户没有登录   --%>
    <%--    </c:when>     --%>

    <%--    else--%>
    <c:otherwise>
        用户登录了 欢迎 ${username }
    </c:otherwise>

</c:choose>
</body>
</html>
