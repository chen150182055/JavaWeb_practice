package com.gec.servlet;

import com.gec.dao.CategoryDao;
import com.gec.entity.Category;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

//分类清单(admin)
public class CategoryListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDao CategoryDao = new CategoryDao();
        List<Category> categoryList;
        categoryList = CategoryDao.getCategoryList();
        request.setAttribute("CategoryList", categoryList);
        request.getRequestDispatcher("/admin/category/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
