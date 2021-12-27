package com.gec.servlet;

import com.gec.dao.OrderDao;
import com.gec.dao.OrderItemDao;
import com.gec.entity.Order;
import com.gec.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String s = request.getParameter("currentPage");
        int currentPage = Integer.parseInt(s);
        OrderDao orderDao = new OrderDao();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //查看订单列表 如果用户没有登录的话就跳转到登录页面
        if (user == null) {
            response.sendRedirect("login.jsp");
        } else {
            //取出数据库订单表中的订单信息
            List<Order> orderList = orderDao.getOrderListByUid(user.getUid());
            //查询出每个订单中的订单商品信息
            for (Order order : orderList) {
                order.setOrderItems(new OrderItemDao().getOrderItemByOid(order.getOid()));
            }

            List<Order> orderList1 = new ArrayList<Order>();
            int n = 5;
            int totalPage = 0;

            if(orderList.size()%n>0){
                totalPage = orderList.size()/n+1;
            }else{
                totalPage = orderList.size()/n;
            }

            for (int i = (currentPage - 1) * n; i < currentPage * n && i < orderList.size(); i++) {
                orderList1.add(orderList.get(i));
            }

            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("orderList", orderList1);
            request.getRequestDispatcher("order_list.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
