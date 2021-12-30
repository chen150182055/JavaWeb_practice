package com.gec.servlet;

import com.gec.entity.Category;
import com.gec.entity.Product;
import com.gec.service.CategoryService;
import com.gec.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//获取商品列表
public class ProductListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String admin = request.getParameter("admin");   //创建一个字符串用来存放admin
        String s = request.getParameter("currentPage"); //创建一个字符串用来存放currentPage（商品第几页）
        int currentPage = Integer.parseInt(s);    //将currentPage转换成int类型的数据
        System.out.println("ProductListServlet: currentPage = "+currentPage);
        String cid = request.getParameter("cid");   //获取cid
        ProductService productService = new ProductService();     //创建service层对象以便间接调用dao层操作数据库
        List<Product> productList = null;     //创建一个List用来存放Product的对象
        if (cid == null) {   //如果取出的cid为空
            productList = productService.getProductList();   //在数据库中取出产品列表
        } else {       //如果取出的cid不为空
            productList = productService.getProductListByCid(cid);   //则用cid在数据库中查找出产品列表
            request.setAttribute("cid", cid);       //同时将cid对象保存在request作用域中
        }


        List<Product> productList1 = new ArrayList<Product>();    //再创建一个Product类型的ArrayList对象
        int n = 12;    //每一页有12个产品

        //初值为(currentPage - 1) * n（该页和该页之前的所有页的商品总数） ，终值为全部商品的数量 ，步长为1
        for (int i = (currentPage - 1) * n; i < currentPage * n && i < productList.size(); i++) {
            productList1.add(productList.get(i));   //
        }
        int totalPage = (productList.size() - 1) / n + 1;     //
        request.setAttribute("currentPage", currentPage);  //
        request.setAttribute("totalPage", totalPage);      //
        request.setAttribute("productList", productList1); //

        if (admin != null) {           //如果获取到admin不为空 即是管理员
            CategoryService categoryService = new CategoryService();   //创建一个CategoryService对象用来间接调用dao层
            List<Category> categoryList = categoryService.getCategoryList();    //将数据库查到的商品放入List中
            request.getSession().setAttribute("category_list", categoryList);//将categoryList这个对象保存在request作用域中
            request.getRequestDispatcher("admin/product/list.jsp").forward(request, response);  //重定向到list.jsp

        } else {   //如果是普通用户
            request.getRequestDispatcher("product_list.jsp").forward(request, response);        //重定向到product_list.jsp中

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
