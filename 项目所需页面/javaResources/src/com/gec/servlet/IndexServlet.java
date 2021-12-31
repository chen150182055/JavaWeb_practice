package com.gec.servlet;

import com.gec.dao.CategoryDao;
import com.gec.dao.ProductDao;
import com.gec.entity.Category;
import com.gec.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

//主页 热门商品和最新商品 （基本功能）
public class IndexServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categoryList = categoryDao.getCategoryList();
        HttpSession session = request.getSession();	//获取session会话对象
        session.setAttribute("categoryList", categoryList);//将查询到的商品分类存入到session会话中

        ProductDao productDao = new ProductDao();
        List<Product> hotProductList = productDao.getHotProductList();

        List<Product> newProductList = productDao.getNewProductList();

        request.setAttribute("hotProductList", hotProductList);
        request.setAttribute("newProductList", newProductList);
        //实现页面转发 转发到.jsp页面 forward是把request请求和response请求都转发过去
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}
