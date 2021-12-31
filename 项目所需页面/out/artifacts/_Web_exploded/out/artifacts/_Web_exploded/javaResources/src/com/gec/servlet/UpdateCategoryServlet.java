package com.gec.servlet;

import com.gec.dao.CategoryDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

//更新商品分类
public class UpdateCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        String cid= request.getParameter("cid");        //获分类ID cid
        String cname=request.getParameter("cname");     //获取分类的名称 cname
        CategoryDao categoryDao=new CategoryDao();        //创建操作分类的dao层去操作数据库
        categoryDao.updateCategory(cid,cname);            //执行更新操作
        response.sendRedirect("CategoryListServlet");   //将页面重定向到分类清单页面
        System.out.print("UpdateCategoryServlet : cid = "+cid);      //这里打印一下cid
        System.out.print("UpdateCategoryServlet : cname = "+cname);    //这里打印一下cname
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
