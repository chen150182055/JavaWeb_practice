package com.gec.servlet;

import com.gec.dao.ProductDao;
import com.gec.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ProductInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先获取商品的编号
        String pid = request.getParameter("pid");
        ProductDao productDao = new ProductDao();
        Product product = productDao.getProductByPid(pid);
        request.setAttribute("product", product);
        request.getRequestDispatcher("product_info.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
