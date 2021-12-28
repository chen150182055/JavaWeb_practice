package com.gec.servlet;

import com.gec.dao.CategoryDao;
import com.gec.entity.Category;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

//添加
public class AddCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cname=request.getParameter("cname");
        Category category=new Category();
        category.setCid(UUID.randomUUID().toString());
        category.setCname(cname);
        CategoryDao categoryDao=new CategoryDao();
        categoryDao.addCategory(category);
        response.sendRedirect("CategoryListServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
