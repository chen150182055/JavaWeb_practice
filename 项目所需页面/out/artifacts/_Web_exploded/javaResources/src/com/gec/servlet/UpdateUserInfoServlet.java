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
        String password = request.getParameter("password");  //获取用户输入密码
        String name = request.getParameter("name");     //获取用户名字
        String sex = request.getParameter("sex");       //获取用户性别
        String birthday = request.getParameter("birthday"); //获取用户生日
        String address = request.getParameter("address");   //获取用户地址
        String email = request.getParameter("email");       //获取用户邮箱
        String telephone = request.getParameter("telephone");   //获取用户手机号
        //从session中获取user
        HttpSession session = request.getSession();
        //将用户的相关信息封装进user对象
        User user = (User) session.getAttribute("user");
        user.setAddress(address);  //封装用户的地址
        user.setName(name);     //封装用户姓名
        user.setTelephone(telephone);   //封装用户手机
        user.setSex(sex);   //封装用户性别
        user.setEmail(email);  //封装用户手机
        user.setBirthday(birthday); //封装用户生日
        user.setPassword(password); //封装用户密码

        UserService userService = new UserService();   //调用业务逻辑层代码去实现访问dao层
        userService.updateUserInfo(user);              //实现在数据库中修改
        session.setAttribute("user", user);         //将新的user放入session中
        response.sendRedirect("user_info.jsp");     //重新定向到user_info.jsp
    }
}
