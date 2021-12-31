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

//处理用户订单状态(admin)
public class ManageOrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        String admin=request.getParameter("admin");
        String oid=request.getParameter("oid");
        String s=request.getParameter("state");
        OrderService orderService=new OrderService();
        int state=Integer.parseInt(s);
        Order order=orderService.getOrderByOid(oid);
        request.setAttribute("order", order);

        /**
         * * state等于0时，用户需要去付款
         * * state等于1时，用户可以进行催单（以及付款但是商家却没有发货）
         * * state等于2时，确定收货
         * * state等于3时，用户收完货了才能进行订单的评价
         * * state等于4时，才能够查看对应的订单信息
         */
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
            //实现页面转发 转发到.jsp页面 forward是把request请求和response请求都转发过去
            request.getRequestDispatcher("assess.jsp").forward(request, response);

        }else{
            //实现页面转发 转发到.jsp页面 forward是把request请求和response请求都转发过去
            request.getRequestDispatcher("order.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
