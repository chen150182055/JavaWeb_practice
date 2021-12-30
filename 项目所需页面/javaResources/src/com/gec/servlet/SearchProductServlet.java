package com.gec.servlet;

import com.gec.dao.ProductDao;
import com.gec.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//查询商品
public class SearchProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        request.setCharacterEncoding("UTF-8");
        String search = request.getParameter("search");  //获取表单中的search
        String s = request.getParameter("currentPage");  //获取表单中currentPage
        System.out.println("SearchProductServlet: search = " + search + " currentPage = " + s);
        int currentPage = Integer.parseInt(s);   //将表单的currentPage转换为int类型


        ProductDao productDao = new ProductDao();   //调用dao层以实现操作数据库
        //因为查询出来的结果可能不止一个 所以用一个List存储
        List<Product> productList = productDao.searchProduct("%" + search + "%");   //调用dao层方法   %为通配符以实现模糊搜索
        int n = 12;  //一页有12个商品

        int totalPage = 0;   //先定义一个总页面
        if (productList.size() % n > 0) {    //如果商品的数量是12的整数倍还多一点 也就是商品不能被12整除
            totalPage = productList.size() / n + 1;   //将页面的数量加1
        } else {
            totalPage = productList.size() / n;   //否则页面的数量就是整除后的结果
        }
        List<Product> productList1 = new ArrayList<Product>();    //创建一个ArrayList对象
        for (int i = (currentPage - 1) * n; i < currentPage * n && i < productList.size(); i++) {   //遍历刚刚查询到的List
            productList1.add(productList.get(i));    //将productList中的对象依次放入productList1中(Product类型)
        }


        request.setAttribute("currentPage", currentPage);   //设置当前页面
        request.setAttribute("totalPage", totalPage);       //设置总页面
        request.setAttribute("search", search);
        request.setAttribute("productList", productList1);  //将查询到的List中的商品存入productList
        //实现页面转发 转发到.jsp页面 forward是把request请求和response请求都转发过去
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
