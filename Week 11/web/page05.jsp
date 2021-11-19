<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.Product" %><%--
  Created by IntelliJ IDEA.
  User: chenxihon123
  Date: 2021/11/12
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //forEach 标签是用来动态展示 从数据库中获取到的数据 到网页上面
    //假设这里的数据是从数据库中获取的产品数据
//    ArrayList list = new ArrayList();
//    list.add(new Product("联想笔记本","美国",8999));
//    list.add(new Product("联想笔记本","美国",8999));
//    list.add(new Product("联想笔记本","美国",8999));
//    list.add(new Product("联想笔记本","美国",8999));
//    list.add(new Product("联想笔记本","美国",8999));
//    list.add(new Product("联想笔记本","美国",8999));
//    list.add(new Product("联想笔记本","美国",8999));
//    list.add(new Product("联想笔记本","美国",8999));
//    list.add(new Product("联想笔记本","美国",8999));
//    list.add(new Product("联想笔记本","美国",8999));
//    list.add(new Product("联想笔记本","美国",8999));
//    list.add(new Product("联想笔记本","美国",8999));
//    list.add(new Product("联想笔记本","美国",8999));
//    list.add(new Product("联想笔记本","美国",8999));

%>
<table border="1" width="400px" cellspacing="0">
    <tr>
        <th>名字</th>
        <th>产地</th>
        <th>价格</th>
    </tr>
    <!-- -->
    <c:forEach items="${products }" var="product">
        <tr>
            <td>${product.name }</td>
            <td>${product.location }</td>
            <td>${product.price }</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
