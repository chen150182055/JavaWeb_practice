package com.gec.servlet;

import com.gec.entity.Product;
import com.gec.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.UUID;


//添加产品(admin)
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        String pname = request.getParameter("pname");  //获取产品名
        String s = request.getParameter("is_hot");     //获取是否是热门商品
        int is_hot = Integer.parseInt(s);                 //将字符串转换成int类型
        String s1 = request.getParameter("market_price");   //获取市场价格
        double market_price = Double.parseDouble(s1);     //将市场价格转化成double
        String s2 = request.getParameter("shop_price");//获取商店价格
        double shop_price = Double.parseDouble(s2);       //转化为double
        String cid = request.getParameter("cid");      //获取属性名为cid的值
        //这里还差一行获取图片的
        String pdesc = request.getParameter("pdesc");  //获取属性名为pdesc的值

        Product product = new Product();                 //创建Product对象用来封装具体信息
        product.setPid(UUID.randomUUID().toString());  //将pid封装进product
        product.setPname(pname);        //将pname封装进product
        product.setIs_hot(is_hot);      //将is_hot封装进product
        product.setMarket_price(market_price);  //将market_price封装进product
        product.setShop_price(shop_price);      //将shop_price 封装进product
        product.setCid(cid);            //将cid封装进product
        product.setPimage("products/1/nopic.jpg");  //将照片封装进product
        product.setPdesc(pdesc);                      //将pdesc封装进product
        ProductService productService = new ProductService();   //调用service层以便间接调用dao层操作数据库
        productService.addProduct(product);                   //调用service具体方法实现操作
        response.sendRedirect("ProductListServlet?admin=admin&currentPage=1");   //重定向 后面的页面是新的request和response
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
