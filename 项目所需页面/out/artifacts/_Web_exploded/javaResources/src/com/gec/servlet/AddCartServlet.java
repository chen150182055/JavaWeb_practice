package com.gec.servlet;

import com.gec.dao.ProductDao;
import com.gec.entity.Cart;
import com.gec.entity.CartItem;
import com.gec.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class AddCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        String s=request.getParameter("buyNum");
        int buyNum=Integer.parseInt(s);
        //根据商品的编号去获取商品的详细信息
        ProductDao productDao = new ProductDao();
        Product product = productDao.getProductByPid(pid);


        CartItem cartItem=new CartItem();
        cartItem.setProduct(product);
        cartItem.setBuyNum(buyNum);

        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        if(cart==null){
            cart=new Cart();
        }
        Map<String, CartItem> cartItems = cart.getCartItems();

        //判断和重复添加商品的情况
        Set<String> keySet = cartItems.keySet();
        for (String string : keySet) {
            //以及存在相同的商品信息
            if(string.equals(pid)) {
                cartItem = cartItems.get(string);
                //进行相加购买数量
                cartItem.setBuyNum(cartItem.getBuyNum()+buyNum);
            }
        }
        cartItems.put(pid, cartItem);
        session.setAttribute("cart", cart);

        response.sendRedirect("cart.jsp");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
