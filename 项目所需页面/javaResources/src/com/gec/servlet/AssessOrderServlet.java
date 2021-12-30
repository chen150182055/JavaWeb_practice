package com.gec.servlet;

import com.gec.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

//评价订单
public class AssessOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid=request.getParameter("oid");   //获取订单号
        String assess=request.getParameter("assess");  //获取评价
        OrderService orderService=new OrderService();   //调用service层以便操作dao
        orderService.assessOrder(oid,assess);   //调用assessOrder创建oid的评价
        orderService.updateOrderState(oid);     //更新一下订单状态
        response.sendRedirect("OrderListServlet?currentPage=1");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
