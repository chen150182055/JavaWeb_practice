package com.gec.servlet;

import com.gec.dao.ProductDao;
import com.gec.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ProductListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDao = new ProductDao();
        String cid = request.getParameter("cid");
        int size = 12; //将商品信息分成每页12条记录
        int count = (int)productDao.getCount(cid);//求出商品共有多少条记录
        int totalPage = 0; 	//计算总页数

        if(count%size>0) {//判断一共需要分多少页
            totalPage = count/size +1;
        }else {
            totalPage = count/size;
        }

        //处理页码信息
        String s = request.getParameter("currentPage");//获取页码信息
        int currentPage = 1;
        if(s!=null) {
            currentPage=Integer.parseInt(s);
            if(currentPage<=0) {
                currentPage = 1;
            }
            if(currentPage>=totalPage) {
                currentPage = totalPage;
            }
        }

        List<Product> list = null;
        //如果商品信息为空的话 就直接查询  所有商品列表信息
        if(cid==null) {
            //limit （（页面-1）*每页显示数目  就是开始显示的记录行数）
            list = productDao.getProduct((currentPage-1)*size, size);
        }else {//有选择商品分类的情况下
            request.setAttribute("cid", cid);
            list = productDao.getProductByCid((currentPage-1)*size, size, cid);
        }


        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("productList",  list);
        request.getRequestDispatcher("product_list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
