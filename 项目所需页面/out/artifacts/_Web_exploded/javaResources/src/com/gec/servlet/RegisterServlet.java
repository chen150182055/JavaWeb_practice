package com.gec.servlet;

import com.gec.dao.UserDao;
import com.gec.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

//实现注册 (基本功能)
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        // 设置一下这个编码格式
        request.setCharacterEncoding("UTF-8");
        // 获取表单提交过来的数据
        String username = request.getParameter("username");  //获取request中属性名为username的值
        String password = request.getParameter("password"); //获取request中属性名为password的值
        String name = request.getParameter("name");         //获取request中属性名为name的值
        String email = request.getParameter("email");       //获取request中属性名为email的值
        String telephone = request.getParameter("telephone");//获取request中属性名为telephone的值
        String sex = request.getParameter("sex");           //获取request中属性名为sex的值
        String birthday = request.getParameter("birthday"); //获取request中属性名为birthday的值
        String address = request.getParameter("address");   //获取request中属性名为address的值
        System.out.println(address);
        // 判断一下这个账号是否存在
        UserDao userDao = new UserDao();   //调用dao层操作数据库
        User user = null;                  //创建user用来封装信息
        user = userDao.getUserByUsername(username);  //通过username获取User信息
        if (user != null) {                //如果用户信息不为空
            request.setAttribute("msg", "<script>alert('账号已经存在已经存在，重新注册...')</script>");
            //实现页面转发 转发到.jsp页面 forward是把request请求和response请求都转发过去
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            // 账号没有被注册过的情况
            user = new User();
            //设置用户相关的信息
            user.setUid(UUID.randomUUID().toString());    //生成一个随机的用户id封装进user中
            user.setUsername(username);                   //将username封装进user
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
                //实现页面转发 转发到.jsp页面 forward是把request请求和response请求都转发过去
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
