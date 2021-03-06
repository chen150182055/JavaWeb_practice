<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: niuho
  Date: 2021/10/15
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
</head>
<body>
<%
    //要实现这个5分钟以内不输入账号密码 直接登录的方案的话 就需要在进行登录之前对cookie的内容进行判断 看看里面有没有用户的信息
    //用户名
    String user="";
    //登录密码
    String pass="";
    //获取的是请求里面所有的cookie组成的数组   (获取cookie信息)
    Cookie[] cookies =request.getCookies();
    //如果cookie数组不为空对其遍历(因为第一次打开cookie是空的 所以我们要对cookie进行空值判断)
    if (cookies!=null && cookies.length >0){
        //当cookie中有内容的时候 所以我们需要对cookie看看有没有用户的信息
        //循环遍历Cookie
        for (int i=0;i < cookies.length;i++){
            //获取Cookie对象
            Cookie cookie = cookies[i];
            //将创建的cookie名与获取的cookie数组中已经存在的cookie名进行比较
            if ("name".equals(cookie.getName())){
                //"name"是在另一个jsp文件中创建的cookie名
                //获取名字叫做“name”的cookie的值
                user = URLDecoder.decode(cookie.getValue(), "utf-8");
            }
            //将创建的cookie名与获取的cookie数组中已经存在的cookie名进行比较
            if("password".equals(cookie.getName())){
                //"password"是在另一个jsp文件中创建的cookie名
                //获取名字叫做"password"的cookie值
                pass=cookie.getValue();
            }
        }
    }

%>

<form action="server.jsp" method="post">
    <%--<%=user%>指的是将获取的cookie值放进输入框内--%>
    <table>
        <tr>
            <td>账号:</td>
            <td><input type="text" name="username" value="<%=user%>"/></td  >
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" name="password" value="<%=pass%>"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="提交"></td>
            <td><input type="reset" value="重置"></td>
        </tr>
    </table>
</form>
</body>
</html>
