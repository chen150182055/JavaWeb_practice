package com.gec.servlet;

import com.gec.entity.Cart;
import com.gec.entity.CartItem;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

//修改购物车的商品购买数量
public class UpdateBuyNumServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pid = request.getParameter("pid");   //获取产品的id pid
        String s = request.getParameter("buyNum");  //获取产品的购买数量
        int buyNum = Integer.parseInt(s);   //将购买数量转换成int类型的数据
        HttpSession session = request.getSession(); //获取session
        Cart cart = (Cart) session.getAttribute("cart"); //创建一个购物车的session
        Map<String, CartItem> cartItems = cart.getCartItems();  //创建一个Map用来存储CartItem的关系
        cartItems.get(pid).setBuyNum(buyNum);       //将键为pid的值修改为buyNum
        response.sendRedirect("cart.jsp");  //页面重定向到购物车页面
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
