package com.gec.servlet;

import com.gec.entity.User;
import com.gec.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
            req.setAttribute("error","no");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}
