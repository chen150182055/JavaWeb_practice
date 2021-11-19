<%--
  Created by IntelliJ IDEA.
  User: chenxihon123
  Date: 2021/11/12
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<form action="page02.jsp" method="post">
    <table>
        <tr>
            <td>昵称:</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>所在地:</td>
            <td><input type="text" name="city"></td>
        </tr>
        <tr>
            <td>您所使用的编程语言:</td>
            <td>
                <input type="checkbox" name="language" value="Java">Java
                <input type="checkbox" name="language" value="C">C
                <input type="checkbox" name="language" value="C++">C++
                <input type="checkbox" name="language" value="ASP">ASP
                <input type="checkbox" name="language" value="Python">Python
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="提交"></td>
        </tr>
    </table>
</form>
</body>
</html>
