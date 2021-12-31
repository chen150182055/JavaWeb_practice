package com.gec.servlet;

import com.gec.entity.Product;
import com.gec.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

//编辑产品信息(admin)
public class EditProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 业务逻辑：
         * 1.先获取request中的响应属性值
         * 2.创建对象封装值
         * 3.调用dao层操作底层数据库
         * 4.重定向或者转发
         */
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        String pid=request.getParameter("pid");
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
        product.setPid(pid);
        product.setPname(pname);
        product.setIs_hot(is_hot);
        product.setMarket_price(market_price);
        product.setShop_price(shop_price);
        product.setCid(cid);
        product.setPdesc(pdesc);
        ProductService productService=new ProductService();
        productService.updateProduct(product);
        response.sendRedirect("ProductListServlet?admin=admin&currentPage=1");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
