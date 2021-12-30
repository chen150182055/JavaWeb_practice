package com.gec.servlet;

import com.gec.dao.CategoryDao;
import com.gec.entity.Category;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

//编辑分类(admin)
public class EditCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        String cid=request.getParameter("cid");
        CategoryDao categoryDao=new CategoryDao();
        Category category=categoryDao.getCategoryByCid(cid);
        request.setAttribute("category", category);
        //实现页面转发 转发到.jsp页面 forward是把request请求和response请求都转发过去
        request.getRequestDispatcher("admin/category/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
