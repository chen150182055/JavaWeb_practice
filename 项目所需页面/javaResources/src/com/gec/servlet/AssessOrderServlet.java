package com.gec.servlet;

import com.gec.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AssessOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid=request.getParameter("oid");
        String assess=request.getParameter("assess");
        OrderService orderService=new OrderService();
        orderService.assessOrder(oid,assess);
        orderService.updateOrderState(oid);
        response.sendRedirect("OrderListServlet?currentPage=1");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
