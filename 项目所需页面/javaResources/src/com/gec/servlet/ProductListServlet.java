package com.gec.servlet;

import com.gec.dao.ProductDao;
import com.gec.entity.Category;
import com.gec.entity.Product;
import com.gec.service.CategoryService;
import com.gec.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String admin=request.getParameter("admin");
        String s = request.getParameter("currentPage");
        int currentPage=Integer.parseInt(s);
        String cid = request.getParameter("cid");
        ProductService productService=new ProductService();
        List<Product> productList=null;
        if(cid==null){
            productList=productService.getProductList();
        }
        else{
            productList=productService.getProductListByCid(cid);
            request.setAttribute("cid", cid);
        }


        List<Product> productList1=new ArrayList<Product>();
        int n=12;
        for(int i=(currentPage-1)*n;i<currentPage*n&&i<productList.size();i++){
            productList1.add(productList.get(i));
        }
        int totalPage=(productList.size()-1)/n+1;
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("productList",  productList1);

        if(admin!=null){
            CategoryService categoryService=new CategoryService();
            List<Category> categoryList = categoryService.getCategoryList();
            request.getSession().setAttribute("category_list", categoryList);
            request.getRequestDispatcher("admin/product/list.jsp").forward(request, response);

        }
        else{
            request.getRequestDispatcher("product_list.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
