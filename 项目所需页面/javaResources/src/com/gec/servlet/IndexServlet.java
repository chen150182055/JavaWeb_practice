package com.gec.servlet;

import com.gec.dao.CategoryDao;
import com.gec.dao.ProductDao;
import com.gec.entity.Category;
import com.gec.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

//页面首页
public class IndexServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categoryList = categoryDao.getCategoryList();
        HttpSession session = request.getSession();	//获取session会话对象
        session.setAttribute("categoryList", categoryList);//将查询到的商品分类存入到session会话中

        ProductDao productDao = new ProductDao();
        List<Product> hotProductList = productDao.getHotProductList();

        List<Product> newProductList = productDao.getNewProductList();

        request.setAttribute("hotProductList", hotProductList);
        request.setAttribute("newProductList", newProductList);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}
