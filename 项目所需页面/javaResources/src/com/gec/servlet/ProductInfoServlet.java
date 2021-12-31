package com.gec.servlet;

import com.gec.dao.ProductDao;
import com.gec.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

//获取商品页信息（基本功能）
public class ProductInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        //先获取商品的编号
        String pid = request.getParameter("pid");
        ProductDao productDao = new ProductDao();  //创建dao操作数据库
        Product product = productDao.getProductByPid(pid);  //用商品id去数据库中查出响应的产品并存储1在product对象中
        request.setAttribute("product", product);  //将product放入request作用域中
        //实现页面转发 转发到.jsp页面 forward是把request请求和response请求都转发过去
        request.getRequestDispatcher("product_info.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
