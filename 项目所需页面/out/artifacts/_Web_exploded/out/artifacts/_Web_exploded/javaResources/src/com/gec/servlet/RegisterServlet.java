package com.gec.servlet;

import com.gec.dao.UserDao;
import com.gec.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置一下这个编码格式
        request.setCharacterEncoding("UTF-8");
        // 获取表单提交过来的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String address = request.getParameter("address");
        System.out.println(address);
        // 判断一下这个账号是否存在
        UserDao userDao = new UserDao();
        User user = null;
        user = userDao.getUserByUsername(username);
        if (user != null) {
            request.setAttribute("msg", "<script>alert('账号已经存在已经存在，重新注册...')</script>");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            // 账号没有被注册过的情况
            user = new User();
            //设置用户相关的信息
            user.setUid(UUID.randomUUID().toString());
            user.setUsername(username);
            user.setPassword(password);
            user.setName(name);
            user.setEmail(email);
            user.setTelephone(telephone);
            user.setSex(sex);
            user.setBirthday(birthday);
            user.setAddress(address);
            // 调用添加数据到数据库的方法
            int result = userDao.addUser(user);
            if (result <= 0) {
                request.setAttribute("msg", "<script>alter('注册失败请重新填写注册信息...')</script>");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
