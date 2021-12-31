<%@ page language="java" pageEncoding="UTF-8" %>
<html>
<!-- 管理员主界面的头部栏目 -->
<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css">
        body {
            margin: 0px;
            background-color: #ffffff
        }

        body {
            font-size: 12px;
            color: #000000
        }

        td {
            font-size: 12px;
            color: #000000
        }

        th {
            font-size: 12px;
            color: #000000
        }
    </style>
    <link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css">
</head>
<body>

<table width="100%" height="70%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td>
            <img width="100%" src="${pageContext.request.contextPath}/images/好日子.jpg">
        </td>

        <td width="100%" background="${pageContext.request.contextPath}/images/好日子.jpg">
        </td>
    </tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td height="30" valign="bottom" background="${pageContext.request.contextPath}/images/好日子.jpg">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="85%" align="left">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                    <td width="15%">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="16" background="${pageContext.request.contextPath}/images/好日子.jpg">
                                    <img src="${pageContext.request.contextPath}/images/好日子.jpg" width="6" height="18">
                                </td>
                                <td width="155" valign="bottom" background="${pageContext.request.contextPath}/images/用户名.jpg">
                                    用户名：
                                    <font color="blue">zhangsan</font>
                                </td>
                                <td width="10" align="right" background="${pageContext.request.contextPath}/images/好日子.jpg">
                                    <img src="${pageContext.request.contextPath}/images/好日子.jpg" width="6" height="18">
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td align="right" width="5%">
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</HTML>
