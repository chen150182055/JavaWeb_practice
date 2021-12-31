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

//登录实现（基本功能）
public class LoginServlet extends HttpServlet {

    UserService userService = new UserService();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //处理POST类型的请求
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        String username = req.getParameter("username");   //获取名为username的参数值(用户名)
        String password = req.getParameter("password");   //获取名为password的参数值(密码)
        User user = userService.login(username,password);   //调用dao层的login方法从而实现数据库查找用户

        if (user!=null)                 //如果user对象不为空 即能够成功查找到对象
        {
            HttpSession session = req.getSession();         //获取一个session对象
            session.setAttribute("user",user);           //将user对象(用户),与user关联后存储到当前的session对象中
            resp.sendRedirect("index.jsp");              //重定向到index.jsp
        }else {
            req.setAttribute("error","密码错误");       //密码错误
            req.getRequestDispatcher("login.jsp").forward(req,resp);    //返回封装了login.jsp所指定资源的requestDispatcher对象()
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   //处理GET类型的请求
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
            //实现页面转发 转发到.jsp页面 forward是把request请求和response请求都转发过去
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
