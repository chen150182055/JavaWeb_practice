package com.gec.servlet;

import com.gec.dao.OrderDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

//删除订单
public class DeleteOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid=request.getParameter("oid");  //获取名为oid的参数值
        //创建一个OrderDao对象
        OrderDao orderDao = new OrderDao();
        //通过该对象调用deleteOrderItemById删除
        orderDao.deleteOrderItemByOid(oid);

        response.sendRedirect("OrderListServlet?currentPage=1");   //
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
