package com.gec.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

//清除购物车(基本功能)
public class CleanCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * 业务逻辑：
         * 1.先获取request中的响应属性值
         * 2.创建对象封装值
         * 3.调用dao层操作底层数据库
         * 4.重定向或者转发
         */
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        //创建一个Session对象
        HttpSession session = request.getSession();
        //从session对象中删除名为cart的属性
        session.removeAttribute("cart");
        //重定向到cart.jsp
        response.sendRedirect("cart.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
