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

//添加到购物车
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
        //创建一个字符串用来存放request请求的pid
        String pid = request.getParameter("pid");
        //创建一个字符串用来存放request请求的buyNum
        String s=request.getParameter("buyNum");
        //将刚刚的字符串转换成十进制整数并传给buyNum
        int buyNum=Integer.parseInt(s);
        //根据商品的编号去获取商品的详细信息
        ProductDao productDao = new ProductDao();   //创建一个Product对象
        Product product = productDao.getProductByPid(pid);  //通过商品编号去查询对应的商品信息 并将返回的product对象保存

        //创建一个CartItem对象
        CartItem cartItem=new CartItem();
        //调用set方法
        cartItem.setProduct(product);
        cartItem.setBuyNum(buyNum);

        /*
         * HttpSession 服务端的技术
         * 服务器会为每一个用户创建一个独立的 session
         *
         * HttpSession 原理
         * 当用户第一次访问 Servlet 时,服务器端对给该用户创建一个独立的 Session, 并且生成一个 SessionID
         * 这个 SessionID 在响应浏览器的时候,会被装进 cookie 中
         * 从而被保存到浏览器中
         * 当用户再一次访问 Servlet 的时候
         * 请求中会携带着 cookie 中的 SessionID 去访问
         * 服务器会根据这个 SessionID 去查看是否有对应的 Session 对象
         * 有,则直接调用
         * 无,则创建一个(相当于用户第一次访问)(看病案例)
         *
         * 域的范围
         * Context 域 > Session 域 > Request 域
         * Session 域只要会话不结束就会存在,但是Session 域有默认存活时间-30分钟
         */
        //会话技术 理解什么是Session 为什么要使用Session Session有什么方法
        HttpSession session = request.getSession();    //创建一个HttpSession对象用来存放请求的Session
        Cart cart = (Cart)session.getAttribute("cart"); //从当前的HttpSession对象中返回名称为cart的对象
        if(cart==null){  //如果cart为空的话
            cart=new Cart();    //创建一个新的Cart对象
        }

        //
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
        session.setAttribute("cart", cart);   //将对象cart与cart关联并存放到对象session中

        response.sendRedirect("cart.jsp");    //实现重定向到cart.jsp
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
