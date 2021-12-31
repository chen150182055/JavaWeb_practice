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
        /**
         * 业务逻辑：
         * 1.先获取request中的响应属性值
         * 2.创建对象封装值
         * 3.调用dao层操作底层数据库
         * 4.重定向或者转发
         */
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        String s = request.getParameter("currentPage");   //获取request作用域中属性名为currentPage的值
        int currentPage = Integer.parseInt(s);              //将currPage转换为int类型
        OrderService orderService = new OrderService();     //调用service以便间接调用dao层
        List<Order> orderList = orderService.getAllOrderList();   //将所有订单查出并将其存入一个List
        List<Order> orderList1 = new ArrayList<Order>();        //
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
