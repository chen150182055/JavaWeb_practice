package com.gec.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {
    @Override
    //get 请求指的是浏览器向服务器向服务器发起的默认请求 doGet()方法就是来处理浏览器的get请求的方法
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //返回HTML内容的话 我们需要设置response返回的内容的类型
        resp.setContentType("text/html;charset = UTF-8");
        //我们尝试在Servlet中输出HTML内容
        //out内置对象 在jsp中用来输出HTML内容 Servlet里面没有帮我们创建内置对象 如果我们需要用的话 要先去自己创建
        //获取向页面输出HTML代码的对象
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<tittle>使用Servlet来生成HTML代码</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("使用Servlet来生成HTML代码");
        out.println("</body>");
        out.println("/html");
    }

    //处理浏览器发出的post请求
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
