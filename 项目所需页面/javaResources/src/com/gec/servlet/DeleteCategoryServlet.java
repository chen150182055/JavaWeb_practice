package com.gec.servlet;

import com.gec.dao.CategoryDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

//删除分类
public class DeleteCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid=request.getParameter("cid");
        CategoryDao categoryDao=new CategoryDao();  //调用dao层操作数据库
        categoryDao.deleteCategoryByCid(cid);   //执行删除
        response.sendRedirect("CategoryListServlet");  //返回新的分类页面
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
