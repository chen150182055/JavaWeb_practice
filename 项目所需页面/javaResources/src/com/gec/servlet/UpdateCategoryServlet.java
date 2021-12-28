package com.gec.servlet;

import com.gec.dao.CategoryDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UpdateCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid= request.getParameter("cid");
        String cname=request.getParameter("cname");
        CategoryDao categoryDao=new CategoryDao();
        categoryDao.updateCategory(cid,cname);
        response.sendRedirect("CategoryListServlet");


        System.out.print(cid);
        System.out.print(cname);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
