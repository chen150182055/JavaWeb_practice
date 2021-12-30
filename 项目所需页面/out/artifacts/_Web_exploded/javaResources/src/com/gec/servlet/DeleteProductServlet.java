package com.gec.servlet;

import com.gec.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

//删除产品
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid=request.getParameter("pid");  //获取产cid
        ProductService productService=new ProductService(); //调用service层间接调用dao层操作数据库
        productService.deleteProductByPid(pid); //执行删除
        response.sendRedirect("ProductListServlet?admin=admin&currentPage=1");  //页面从定向并返回admin=admin&currentPage=1
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
