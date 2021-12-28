<%@ page language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        body {
            SCROLLBAR-ARROW-COLOR: #ffffff;
            SCROLLBAR-BASE-COLOR: #dee3f7;
        }
    </style>
</head>

<frameset rows="103,*,43" frameborder=0 border="0" framespacing="0">
    <frame src="${pageContext.request.contextPath}/admin/top.jsp" name="topFrame" scrolling="NO" noresize>    <!-- 引入顶部导航 -->
    <frameset cols="159,*" frameborder="0" border="0" framespacing="0">
        <frame src="${pageContext.request.contextPath}/admin/left.jsp" name="leftFrame" noresize scrolling="YES">   <!-- 引入左部菜单 -->
        <frame src="${pageContext.request.contextPath}/admin/welcome.jsp" name="mainFrame">   <!-- 引入欢迎页面 -->
    </frameset>
    <frame src="${pageContext.request.contextPath}/admin/bottom.jsp" name="bottomFrame" scrolling="NO" noresize>   <!-- 引入邮箱等 -->
</frameset>
</html>
