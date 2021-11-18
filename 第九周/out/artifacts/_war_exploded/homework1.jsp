<%--
  Created by IntelliJ IDEA.
  User: niuho
  Date: 2021/11/5
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homework1</title>
</head>
<body>
<form action="server.jsp" method="post" charset="UTF-8">
    <table>
        <tr>
            <td>昵称:</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>所在城市:</td>
            <td><input type="text" name="city"></td>
        </tr>
        <tr>
            <td>您使用的开发语言:</td>
            <td><label><input type="checkbox" name="choose" value="java">java</label></td>
            <td><label><input type="checkbox" name="choose" value="C">C</label></td>
            <td><label><input type="checkbox" name="choose" value="C++">C++</label></td>
            <td><label><input type="checkbox" name="choose" value="PHP">PHP</label></td>
            <td><label><input type="checkbox" name="choose" value="ASP">ASP</label></td>
        </tr>
        <tr>
            <td><input type="submit" value="提交"></td>
        </tr>
    </table>
</form>
</body>
</html>
