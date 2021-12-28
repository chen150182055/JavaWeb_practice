package com.gec.servlet;

import com.gec.entity.Product;
import com.gec.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

//添加产品
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pname=request.getParameter("pname");
        String s=request.getParameter("is_hot");
        int is_hot=Integer.parseInt(s);
        String s1=request.getParameter("market_price");
        double market_price=Double.parseDouble(s1);
        String s2=request.getParameter("shop_price");
        double shop_price=Double.parseDouble(s2);
        String cid=request.getParameter("cid");
        String pdesc=request.getParameter("pdesc");

        Product product=new Product();
        product.setPid(UUID.randomUUID().toString());
        product.setPname(pname);
        product.setIs_hot(is_hot);
        product.setMarket_price(market_price);
        product.setShop_price(shop_price);
        product.setCid(cid);
        product.setPimage("products/1/cs40009.png");
        product.setPdesc(pdesc);
        ProductService productService=new ProductService();
        productService.addProduct(product);
        response.sendRedirect("ProductListServlet?admin=admin&currentPage=1");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
