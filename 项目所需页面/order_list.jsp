<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>会员登录</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
    <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="css/style.css" type="text/css"/>

    <style>
        body {
            margin-top: 20px;
            margin: 0 auto;
        }

        .carousel-inner .item img {
            width: 100%;
            height: 300px;
        }
    </style>
</head>

<body>


<!-- 引入header.jsp -->
<jsp:include page="/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div style="margin: 0 auto; margin-top: 10px; width: 950px;text-align:center;">
            <strong>我的订单</strong>
            <table class="table table-bordered">
                <c:forEach items="${orderList}" var="order"> <!-- 将所有订单输出出来 -->
                    <tbody>
                    <tr class="success">  <!-- 表格第一行打印订单号 背景为绿色 -->
                        <th colspan="5">订单编号:${order.oid}</th>
                    </tr>
                    <tr class="warning">    <!-- 表格第二行打印订单的说明 背景为淡黄色 -->
                        <th>图片</th>
                        <th>商品</th>
                        <th>价格</th>
                        <th>数量</th>
                        <th>小计</th>
                    </tr>
                    <c:forEach items="${order.orderItems}" var="orderItem"> <!-- 输出单个订单项的具体 -->
                        <tr class="active">   <!-- 表格第三行打印订单相应的具体信息 背景为灰色 -->
                            <td width="60" width="40%">   <!-- 订单图片显示 -->
                                <input type="hidden" name="id" value="22">
                                <img src="${orderItem.product.pimage}" width="70" height="60">
                            </td>
                            <td width="30%">   <!-- 商品名显示 -->
                                <a target="_blank">
                                        ${orderItem.product.pname}
                                </a>
                            </td>
                            <td width="15%">   <!-- 商品价格 -->
                                ￥${orderItem.product.shop_price}
                            </td>
                            <td width="15%">    <!-- 购买数量 -->
                                    ${orderItem.count}
                            </td>
                            <td width="15%">    <!-- 小计 -->
                                <span class="subtotal">￥${orderItem.subtotal}</span>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>    <!-- 订单第四行 输出相关的订单状态信息 -->
                        <td>合计:&nbsp;&nbsp;<font style="color:red">￥${order.total}</font></td>
                        <td>${order.orderTime}</td>
                        <td>操作</td>
                        <td>
                            <a href="ManageOrderServlet?state=${order.state}&oid=${order.oid}">
                                <c:if test="${order.state==0}">去付款</c:if>   <!-- 如果订单状态为0 -->
                                <c:if test="${order.state==1}">催单</c:if>    <!-- 如果订单状态为1 -->
                                <c:if test="${order.state==2}">确定收货</c:if>  <!-- 如果订单状态为2 -->
                                <c:if test="${order.state==3}">去评价</c:if>   <!-- 如果订单状态为3 -->
                                <c:if test="${order.state==4}">查看订单详情</c:if><!-- 如果订单状态为4 -->
                            </a></td>
                        <td><c:if test="${order.state==4}">交易成功</c:if></td> <!-- 如果订单状态为4 -->
                    </tr>
                    </tbody>
                </c:forEach>
            </table>
        </div>
    </div>
    <!--分页 -->
    <div style="width: 380px; margin: 0 auto; margin-top: 50px;">
        <ul class="pagination" style="text-align: center; margin-top: 10px;">
            <c:if test="${currentPage==1}"> <!-- 如果订单只有1页 -->
                <li class="disabled">
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>     <!-- -->
                    </a>
                </li>
            </c:if>
            <c:if test="${currentPage!=1}"> <!-- 如果订单页不止1页 -->
                <li>
                    <a href="OrderListServlet?currentPage=${currentPage-1}" aria-label="Previous">    <!-- 订单列表 -->
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>


            <c:forEach begin="1" end="${totalPage}" var="pageNum">  <!--  -->
                <c:if test="${currentPage==pageNum}"> <!-- 如果当前页面为是最后一页 -->
                    <li class="active">
                        <a href="OrderListServlet?currentPage=${pageNum}">    <!--  -->
                                ${pageNum }
                        </a>
                    </li>
                </c:if>
                <c:if test="${currentPage!=pageNum }"><!-- 如果当前页面不是最后一页 -->
                    <li>
                        <a href="OrderListServlet?currentPage=${pageNum}">  <!--  -->
                                ${pageNum}
                        </a>
                    </li>
                </c:if>
            </c:forEach>

            <c:if test="${currentPage!=totalPage}"> <!--  -->
                <li>
                    <a href="OrderListServlet?currentPage=${currentPage+1}" aria-label="Next">    <!-- -->
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>


            <c:if test="${currentPage==totalPage}"> <!--  -->
                <li class="disabled">
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span> <!--  -->
                    </a>
                </li>
            </c:if>
        </ul>
    </div>
    <!-- 分页结束 -->
</div>

<!-- 引入footer.jsp -->
<jsp:include page="/footer.jsp"></jsp:include>

</body>

</html>