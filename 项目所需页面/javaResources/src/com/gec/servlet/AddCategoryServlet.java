package com.gec.servlet;

import com.gec.dao.CategoryDao;
import com.gec.entity.Category;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

//添加商品分类
public class AddCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cname=request.getParameter("cname");  //获取分类名称
        Category category=new Category();       //创建Category对象
        category.setCid(UUID.randomUUID().toString());      //设置一个随机分类id
        category.setCname(cname);       //设置分类名
        CategoryDao categoryDao=new CategoryDao();      //调用dao层
        categoryDao.addCategory(category);      //操作数据库完成添加分类操作
        response.sendRedirect("CategoryListServlet");  //重定向到CategoryListServlet
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
