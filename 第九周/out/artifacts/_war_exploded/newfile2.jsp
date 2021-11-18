<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: niuho
  Date: 2021/11/5
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    ArrayList list = new ArrayList();
    //在集合数组中 添加一组元素进来
    list.add(0, "Jammy");
    list.add(1, "Tomm");
    list.add(2, "zhangsan");
    //
    application.setAttribute("names",list);
    //
    HashMap map = new HashMap();
    map.put("one", "1");
    map.put("key", "value");
    session.setAttribute("map", map);

%>
<!-- 使用EL表达式来获取list中的数据 -->
<h2>${names }</h2>
<h2>${names[0] },${names[1] },${names[2] }</h2>
<h2></h2>
<h3>${map.key},${map.one}</h3>
<h3></h3>
${1 == 2},${1 > 2}, ${empty password },${!empty password }
</body>
</html>
