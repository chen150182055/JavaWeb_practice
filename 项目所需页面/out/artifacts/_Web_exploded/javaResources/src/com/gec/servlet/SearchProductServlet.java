package com.gec.servlet;

import com.gec.dao.ProductDao;
import com.gec.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search=request.getParameter("search");
        String s=request.getParameter("currentPage");
        System.out.println("search"+search+"currentPage" );
        int currentPage=Integer.parseInt(s);


        ProductDao productDao = new ProductDao();
        List<Product> productList=productDao.searchProduct("%"+search+"%");
        int n=12;

        int totalPage=0;
        if(productList.size()%n>0) {
            totalPage = productList.size()/n+1;
        }else {
            totalPage = productList.size()/n;
        }
        List<Product> productList1=new ArrayList<Product>();
        for(int i=(currentPage-1)*n;i<currentPage*n&&i<productList.size();i++){
            productList1.add(productList.get(i));
        }


        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("search", search);
        request.setAttribute("productList", productList1);
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
