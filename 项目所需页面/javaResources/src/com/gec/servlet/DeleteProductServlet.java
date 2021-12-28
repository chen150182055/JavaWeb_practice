package com.gec.servlet;

import com.gec.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid=request.getParameter("pid");
        ProductService productService=new ProductService();
        productService.deleteProductByPid(pid);
        response.sendRedirect("ProductListServlet?admin=admin&currentPage=1");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
