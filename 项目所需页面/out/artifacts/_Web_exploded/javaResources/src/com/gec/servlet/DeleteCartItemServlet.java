package com.gec.servlet;

import com.gec.entity.Cart;
import com.gec.entity.CartItem;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

//删除购物车项
public class DeleteCartItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        //
        String pid = request.getParameter("pid");   //获取pid
        HttpSession session = request.getSession();   //获取session
        Cart cart = (Cart) session.getAttribute("cart");    //在session中获取购物车
        Map<String, CartItem> cartItems = cart.getCartItems();  //将购物车中的商品取出放入Map  (K-String V-CartItem)
        cartItems.remove(pid);   //在购物车项中删除键为pid的值
        //如果购物车中没有商品，则清空购物车
        if (cartItems.size() == 0) {
            session.removeAttribute("cart");   //清除session中的cart
        }
        response.sendRedirect("cart.jsp");  //页面重定向到购物车页面(相当于刷新)
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
