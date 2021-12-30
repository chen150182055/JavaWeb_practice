package com.gec.servlet;

import com.gec.entity.Order;
import com.gec.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//获取所有订单业务逻辑
public class AllOrderListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        String s = request.getParameter("currentPage");
        int currentPage = Integer.parseInt(s);
        OrderService orderService = new OrderService();
        List<Order> orderList = orderService.getAllOrderList();
        List<Order> orderList1 = new ArrayList<Order>();
        int n = 5;
        int totalPage = (orderList.size() - 1) / n + 1;
        for (int i = (currentPage - 1) * n; i < currentPage * n && i < orderList.size(); i++) {
            orderList1.add(orderList.get(i));
        }
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("orderList", orderList1);
        //实现页面转发 转发到admin/order/list.jsp页面 forward是把request请求和response请求都转发过去
        request.getRequestDispatcher("admin/order/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
