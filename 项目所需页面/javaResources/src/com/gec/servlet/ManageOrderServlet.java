package com.gec.servlet;

import com.gec.dao.OrderDao;
import com.gec.dao.OrderItemDao;
import com.gec.entity.Order;
import com.gec.entity.OrderItem;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

//处理用户订单状态
public class ManageOrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oid = request.getParameter("oid");
        String s = request.getParameter("state");
        OrderDao orderDao = new OrderDao();
        int state = Integer.parseInt(s);
        //获取该订单的状态信息
        Order order = orderDao.getOrderByOid(oid);
        request.setAttribute("order", order);

        if (state == 0) {//订单状态为0就是还没有付款跳转到付款页面
            OrderItemDao orderItemDao = new OrderItemDao();
            List<OrderItem> orderItemByOid = orderItemDao.getOrderItemByOid(oid);
            order.setOrderItems(orderItemByOid);
            request.getRequestDispatcher("account.jsp").forward(request, response);
        } else if (state == 1) {
            //订单状态码为1进行催单操作  重新返回这个页面即可
            response.sendRedirect("OrderListServlet?currentPage=1");
        } else if (state == 2) {
            //订单状态码为2的话就是确定收货
            //修改一下订单状态 用户点击了确认收货修改一下状态码
            orderDao.updateOrderState(oid);//用户确定收货修改订单信息
            response.sendRedirect("OrderListServlet?currentPage=1");

        } else if (state == 3) {
            //如果状态码等于3的话就是 用户已经确认收货了 进入评价页面
            request.getRequestDispatcher("assess.jsp").forward(request, response);
            //其它情况的化一律进入订单页面
        } else {
            request.getRequestDispatcher("order.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
