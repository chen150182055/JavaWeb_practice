package com.gec.servlet;

import com.gec.dao.ProductDao;
import com.gec.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

//获取商品页信息
public class ProductInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先获取商品的编号
        String pid = request.getParameter("pid");
        ProductDao productDao = new ProductDao();  //创建dao操作数据库
        Product product = productDao.getProductByPid(pid);  //用商品id去数据库中查出响应的产品并存储1在product对象中
        request.setAttribute("product", product);  //将product放入request作用域中
        request.getRequestDispatcher("product_info.jsp").forward(request, response); //页面重定向到product_info.jsp
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
