package com.gec.servlet;

import com.gec.dao.OrderDao;
import com.gec.dao.OrderItemDao;
import com.gec.entity.Order;
import com.gec.entity.OrderItem;
import com.gec.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

//处理用户订单状态
public class ManageOrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String admin=request.getParameter("admin");
        String oid=request.getParameter("oid");
        String s=request.getParameter("state");
        OrderService orderService=new OrderService();
        int state=Integer.parseInt(s);
        Order order=orderService.getOrderByOid(oid);
        request.setAttribute("order", order);
        if(state==0){
            request.getRequestDispatcher("order_info.jsp").forward(request, response);
        }
        else if(state==1){
            if(admin!=null){
                orderService.updateOrderState(oid);
                response.sendRedirect("AllOrderListServlet?currentPage=1");
            }
            else{
                response.sendRedirect("OrderListServlet?currentPage=1");
            }

        }else if(state==2){
            orderService.updateOrderState(oid);
            response.sendRedirect("OrderListServlet?currentPage=1");
        }else if(state==3){
            request.getRequestDispatcher("assess.jsp").forward(request, response);

        }else{
            request.getRequestDispatcher("order.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
