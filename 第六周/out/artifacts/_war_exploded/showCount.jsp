<%--
  Created by IntelliJ IDEA.
  User: niuho
  Date: 2021/10/15
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>统计用户访问数</title>
</head>
<body>
<%
    //要有一个变量，可以给所有用户都共享，就是存放在application对象中
    //想要统计用户的访问数的话 要创建一个共享变量count
    //当用户访问页面的时候我们去application中获取count
    Integer count=(Integer) application.getAttribute("count");
    //因为用户如果是第一个访问当前网站的用户的话 这个count是空值
    if(count==null){
        //用户第一次访问的话 因为application中count对象为null 给他赋值
        count=1;
    }else {
        //如果count对象不为空 那么就是直接修改他的变量值就可以了
        count = count +1;
    }
    //将修改了以后的count值更行到application中
    application.setAttribute("count",count);
    //向页面输出内容
    out.print("你是第"+count+"个访问当前页面的用户");
%>
</body>
</html>
