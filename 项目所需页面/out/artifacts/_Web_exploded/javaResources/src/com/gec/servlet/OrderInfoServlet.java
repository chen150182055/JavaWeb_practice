package com.gec.servlet;

import com.gec.dao.OrderDao;
import com.gec.dao.OrderItemDao;
import com.gec.entity.Order;
import com.gec.entity.User;
import com.gec.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//订单信息
public class OrderInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        String oid=request.getParameter("oid");
        OrderService orderService=new OrderService();
        Order order = orderService.getOrderByOid(oid);
        request.setAttribute("order", order);
        //实现页面转发 转发到.jsp页面 forward是把request请求和response请求都转发过去
        request.getRequestDispatcher("admin/order/info.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
