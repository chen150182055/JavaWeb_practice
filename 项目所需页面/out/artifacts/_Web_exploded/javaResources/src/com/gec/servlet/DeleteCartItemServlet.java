package com.gec.servlet;

import com.gec.entity.Cart;
import com.gec.entity.CartItem;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

public class DeleteCartItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        //
        String pid = request.getParameter("pid");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Map<String, CartItem> cartItems = cart.getCartItems();
        cartItems.remove(pid);
        //如果购物车中没有商品，则清空购物车
        if (cartItems.size() == 0) {
            session.removeAttribute("cart");
        }

        response.sendRedirect("cart.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
