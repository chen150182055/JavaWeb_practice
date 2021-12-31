package com.gec.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

//用户注销业务逻辑
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        //先获取session作用域中的user信息,在清除掉即可
        request.getSession().removeAttribute("user");    //从当前session中删除名为user的属性对象
        response.sendRedirect("login.jsp");         //重定向到login.jsp
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
