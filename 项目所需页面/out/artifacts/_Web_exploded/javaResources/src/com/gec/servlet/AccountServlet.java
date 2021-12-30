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
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        String oid = request.getParameter("oid");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();    //获取当前request对象的session
        User user = (User) session.getAttribute("user");   //从当前session中返回名为user的属性对象

        OrderDao orderDao = new OrderDao();  //调用dao层以操作数据库

        //比较一下用户输入的支付密码
        System.out.println(user.getPassword());
        System.out.println(password);
        if (password.equals(user.getPassword())) {   //如果用户密码正确
            //修改一下订单的状态
            orderDao.updateOrderState(oid);   //操作数据库更新订单状态
            response.sendRedirect("OrderListServlet?currentPage=1");    //重定向到新的订单列表 初始化页面是第一页
        } else {        //如果用户密码错误
            request.setAttribute("error", "密码不正确，请确定后重新输入");  //设置属性名为error的值为 .......
            Order order = orderDao.getOrderByOid(oid);
            request.setAttribute("order", order);   //request的域属性 前面的order是属性名 后面的order是属性值
            //实现页面转发 转发到account.jsp页面 forward是把request请求和response请求都转发过去
            request.getRequestDispatcher("account.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
