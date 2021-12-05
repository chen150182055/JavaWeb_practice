package com.gec.servlet;

import com.gec.entity.User;
import com.gec.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户账号密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //对账号密码进行判断
        User user = userService.login(username,password);

        if (user!=null)
        {
            //正确就是登录成功 查询结果不为空 就是账号密码正确
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            resp.sendRedirect("index.jsp");
        }else {
            //错误登录失败
            req.setAttribute("error","no");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}
