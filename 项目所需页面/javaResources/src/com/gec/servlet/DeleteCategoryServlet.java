package com.gec.servlet;

import com.gec.dao.CategoryDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DeleteCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid=request.getParameter("cid");
        CategoryDao categoryDao=new CategoryDao();
        categoryDao.deleteCategoryByCid(cid);
        response.sendRedirect("CategoryListServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
