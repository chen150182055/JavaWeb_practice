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
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        CategoryDao CategoryDao = new CategoryDao();
        List<Category> categoryList;
        categoryList = CategoryDao.getCategoryList();     //调用dao层访问数据库
        request.setAttribute("CategoryList", categoryList);  //
        //实现页面转发 转发到.jsp页面 forward是把request请求和response请求都转发过去
        request.getRequestDispatcher("/admin/category/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
