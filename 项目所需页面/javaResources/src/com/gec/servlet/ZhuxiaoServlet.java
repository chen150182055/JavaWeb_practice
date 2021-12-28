package com.gec.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

//用户注销业务逻辑
public class ZhuxiaoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().removeAttribute("user");    //从当前session中删除指定名称的属性对象
        response.sendRedirect("login.jsp");         //重定向到login.jsp
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
