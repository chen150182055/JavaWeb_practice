<%--
  Created by IntelliJ IDEA.
  User: chenxihon123
  Date: 2021/11/12
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<p>
<%request.setCharacterEncoding("utf-8"); %>

<p>您填写的内容是:</p>
<p>昵称,${param.name}</p>
<p>所在地:${param.city}</p>
<p>您所使用的编程语言
<c:forEach items="${paramValues.language }" var="lg">
    ${lg }
</c:forEach>
</p>
</body>
</html>
