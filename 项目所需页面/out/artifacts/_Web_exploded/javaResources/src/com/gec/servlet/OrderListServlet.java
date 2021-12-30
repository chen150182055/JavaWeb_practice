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

//订单列表
public class OrderListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        String s = request.getParameter("currentPage");   //获取currentPage
        int currentPage = Integer.parseInt(s);  //将currentPage转换为int类型的数据
        OrderDao orderDao = new OrderDao();     //创建dao层操作数据库
        HttpSession session = request.getSession();  //获取session对象
        User user = (User) session.getAttribute("user");    //将user放入session作用域中
        //查看订单列表 如果用户没有登录的话就跳转到登录页面
        if (user == null) {
            response.sendRedirect("login.jsp");   //跳转到登录页面
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

            if (orderList.size() % n > 0) {
                totalPage = orderList.size() / n + 1;
            } else {
                totalPage = orderList.size() / n;
            }

            for (int i = (currentPage - 1) * n; i < currentPage * n && i < orderList.size(); i++) {
                orderList1.add(orderList.get(i));
            }

            request.setAttribute("currentPage", currentPage);   //将currentPage对象放入request作用域中
            request.setAttribute("totalPage", totalPage);       //将totalPage放入request作用域中
            request.setAttribute("orderList", orderList1);      //将orderList1放入request作用域中
            //实现页面转发 转发到.jsp页面 forward是把request请求和response请求都转发过去
            request.getRequestDispatcher("order_list.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
