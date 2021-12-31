<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html public "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 管理员登录首页 -->
<head>
    <title>网上商城管理中心</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="${pageContext.request.contextPath }/css/general.css" rel="stylesheet" type="text/css"/>   <!-- 引入本地项目CSS -->
    <link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" type="text/css"/>    <!-- 引入本地项目CSS -->
    <style type="text/css">  /* 内嵌css */
        body {
            color: white;
        }
    </style>
</head>
<body style="background: #278296">
<center></center>
<form method="post" action="${pageContext.request.contextPath }/admin/home.jsp" target="_parent" name='theForm'
      onsubmit="return validate()">      <!-- 表单处理为home.jsp -->
    <table cellspacing="0" cellpadding="0" style="margin-top: 100px" align="center">
        <tr>
            <td style="padding-left: 50px">
                <table>
                    <tr>
                        <td>管理员姓名：</td>   <!-- 管理员姓名输入框 -->
                        <td><input type="text" name="username"/></td>
                    </tr>
                    <tr>
                        <td>管理员密码：</td>   <!-- 管理员密码输入框 -->
                        <td><input type="password" name="password"/></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><input type="submit" value="进入管理中心" class="button"/></td>    <!-- 提交按钮 -->
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
<script language="JavaScript">   //表单处理js
    document.forms['theForm'].elements['username'].focus();
    /**
     * 检查表单输入的内容
     */
    function validate() {
        var validator = new Validator('theForm');
        validator.required('username', user_name_empty);   //当出现时，标志着一个元素不能在没有值的情况下提交。
        validator.required('password', password_empty);
        if (document.forms['theForm'].elements['captcha']) {
            validator.required('captcha', captcha_empty);
        }
        return validator.passed();
    }
</script>
</body>