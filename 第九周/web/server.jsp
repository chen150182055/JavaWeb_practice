<%--
  Created by IntelliJ IDEA.
  User: niuho
  Date: 2021/11/5
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
您填写的内容是:<br/>
    昵称:${param.username}<br/>
    所在城市:${param.city }<br/>
    使用的开发语言有: ${paramValues.choose[0]}
                    ${paramValues.choose[1]}
                    ${paramValues.choose[2]}
                    ${paramValues.choose[3]}<br/>

</html>
