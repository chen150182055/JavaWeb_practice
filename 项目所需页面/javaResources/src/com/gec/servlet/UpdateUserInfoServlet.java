package com.gec.servlet;

import com.gec.entity.User;
import com.gec.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

//更新用户信息
public class UpdateUserInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String uid = request.getParameter("uid");     //获取uid
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        //从session中获取user
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        user.setAddress(address);
        user.setName(name);
        user.setTelephone(telephone);
        user.setSex(sex);
        user.setEmail(email);
        user.setBirthday(birthday);
        user.setPassword(password);

        UserService userService = new UserService();   //调用业务逻辑层代码去实现访问dao层
        userService.updateUserInfo(user);              //实现在数据库中修改
        session.setAttribute("user", user);         //将新的user放入session中
        response.sendRedirect("user_info.jsp");     //重新定向到user_info.jsp
    }
}
