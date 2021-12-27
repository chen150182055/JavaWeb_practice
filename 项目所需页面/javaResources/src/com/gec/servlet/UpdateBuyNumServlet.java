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
        String pid = request.getParameter("pid");
        String s = request.getParameter("buyNum");
        int buyNum = Integer.parseInt(s);
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Map<String, CartItem> cartItems = cart.getCartItems();
        cartItems.get(pid).setBuyNum(buyNum);
        response.sendRedirect("cart.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
