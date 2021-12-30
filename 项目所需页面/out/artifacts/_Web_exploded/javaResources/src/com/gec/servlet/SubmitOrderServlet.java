package com.gec.servlet;

import com.gec.dao.OrderDao;
import com.gec.entity.*;
import com.gec.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

//提交订单
public class SubmitOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //用户在登录的情况下 才能提交订单
        HttpSession session = request.getSession(); //从session中获取用户登录状态
        User user = (User) session.getAttribute("user");  //将session中的user对象保存
        Cart cart = (Cart) session.getAttribute("cart");    //将session中的Cart对象保存

        //如果用户没有登陆的话 就登录
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        /*OrderService orderService = new OrderService();
        Order order = orderService.creatOrder(user,cart);*/

        //处理订单信息
        //生成订单信息
        Order order = new Order();   //创建一个订单对象用以返回
        order.setOid(UUID.randomUUID().toString());   //设置订单id
        order.setName(user.getName());  //设置订单名
        order.setUid(user.getUid());    //设置订单中的用户id
        //订单中还有这个购物车的商品
        //获取购物车

        ArrayList<OrderItem> orderItems = new ArrayList<>();  //定义一个集合用来存放订单中的字段

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
