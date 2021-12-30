package com.gec.servlet;

import com.gec.dao.CategoryDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

//删除分类(admin)
public class DeleteCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        String cid=request.getParameter("cid"); //获取request作用域中的cid
        CategoryDao categoryDao=new CategoryDao();  //调用dao层操作数据库
        categoryDao.deleteCategoryByCid(cid);   //执行删除
        response.sendRedirect("CategoryListServlet");  //返回新的分类页面
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
