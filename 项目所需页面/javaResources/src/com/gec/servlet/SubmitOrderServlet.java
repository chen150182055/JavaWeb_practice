package com.gec.servlet;

import com.gec.entity.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class SubmitOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//用户在登录的情况下 才能提交订单
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        //如果用户没有登陆的话 就登录
        if(user==null) {
            response.sendRedirect("login.jsp");
        }
        //处理订单信息
        else {
            //生成订单信息
            Order order = new Order();
            order.setOid(UUID.randomUUID().toString());
            order.setName(user.getName());
            order.setUid(user.getUid());
            //订单中还有这个购物车的商品
            //获取购物车
            Cart cart=(Cart)session.getAttribute("cart");

            ArrayList<OrderItem> orderItems = new ArrayList<>();  //定义一个集合用来存放购物中的商品

            Set<Map.Entry<String, CartItem>> entrySet = cart.getCartItems().entrySet();  //获取所有购物中的详细商品信息
            for (Map.Entry<String, CartItem> entry : entrySet) {
                //因为一个订单中有多个商品 我们需要将商品写入数据
                OrderItem orderItem = new OrderItem();
                orderItem.setItemId(UUID.randomUUID().toString());
                orderItem.setCount(entry.getValue().getBuyNum());
                orderItem.setProduct(entry.getValue().getProduct());
                orderItem.setOid(order.getOid());   //通过oid 订单编号 来判断这个商品时属于哪个订单下面的
                orderItems.add(orderItem);  //将取出的内容存放到这个集合中
            }

            order.setOrderItems(orderItems);

            //提交订单后进入的是订单确定页面
            request.setAttribute("order", order);
            request.getRequestDispatcher("order_info.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
