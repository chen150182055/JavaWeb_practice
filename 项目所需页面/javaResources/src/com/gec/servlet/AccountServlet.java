package com.gec.servlet;

import com.gec.dao.OrderDao;
import com.gec.entity.Order;
import com.gec.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

//该类用来确认支付密码
public class AccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户输入的信息
        String oid = request.getParameter("oid");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        OrderDao orderDao = new OrderDao();

        //比较一下用户输入的支付密码
        System.out.println(user.getPassword());
        System.out.println(password);
        if (password.equals(user.getPassword())) {   //如果用户密码正确
            //修改一下订单的状态
            orderDao.updateOrderState(oid);
            response.sendRedirect("OrderListServlet?currentPage=1");
        } else {        //如果用户密码错误
            request.setAttribute("error", "密码不正确，请确定后重新输入");
            Order order = orderDao.getOrderByOid(oid);
            request.setAttribute("order", order);
            request.getRequestDispatcher("account.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
