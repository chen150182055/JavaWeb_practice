package com.gec.servlet;

import com.gec.dao.OrderDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

//删除订单
public class DeleteOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        String oid=request.getParameter("oid");  //获取名为oid的参数值
        System.out.println(oid);
        //创建一个OrderDao对象
        OrderDao orderDao = new OrderDao();
        //通过该对象调用deleteOrderItemById删除
        orderDao.deleteOrderItemByOid(oid);
        System.out.println(12);

        response.sendRedirect("OrderListServlet?currentPage=1");   //
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
