package com.gec.servlet;

import com.gec.dao.UserDao;
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
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userService.login(username,password);

        if (user!=null)
        {
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            resp.sendRedirect("index.jsp");
        }else {
            req.setAttribute("error","密码错误");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        User user = userDao.getUserByUsername(username);

        if(user!=null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("IndexServlet");
        }else {

            request.setAttribute("error", "账号或密码输入错误，请重新确认...");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
