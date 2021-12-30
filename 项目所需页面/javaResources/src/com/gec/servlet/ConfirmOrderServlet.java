package com.gec.servlet;

import com.gec.dao.OrderDao;
import com.gec.dao.OrderItemDao;
import com.gec.entity.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

//确定订单
public class ConfirmOrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getParameter()用于单个值的读取 参数多为表单中控件的name属性
        HttpSession session = request.getSession();
        String oid = request.getParameter("oid");      //获取oid
        String address = request.getParameter("address");  //获取address
        String name = request.getParameter("name");     //获取name
        String telephone = request.getParameter("telephone");  //获取telephone

        System.out.println(address);   //打印一下address

        OrderDao orderDao = new OrderDao();   //调用dao层操作数据库
        OrderItemDao orderItemDao = new OrderItemDao();    //调用dao层操作数据库

        User user = (User) session.getAttribute("user");   //将session中名为user的值保存为User对象

        Order order = new Order();
        order.setOid(oid);
        order.setUid(user.getUid());  //设置订单中购买人信息
        order.setAddress(address);    //设置订单中的地址
        order.setName(name);          //设置订单中的名字
        order.setTelephone(telephone);//设置订单中的电话号码
        order.setState(0);            //设置订单中的状态


        //将购物车中的商品都取出来 然后放到订单类中
        List<OrderItem> orderItems = new ArrayList<OrderItem>();

        //从hashmap中取出购物车的商品信息
        Cart cart = (Cart) session.getAttribute("cart");
        Set<Map.Entry<String, CartItem>> entry = cart.getCartItems().entrySet();

        for (Map.Entry<String, CartItem> cartItem : entry) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItemId(UUID.randomUUID().toString());
            orderItem.setCount(cartItem.getValue().getBuyNum());
            orderItem.setOid(order.getOid());
            orderItem.setProduct(cartItem.getValue().getProduct());
            orderItems.add(orderItem);

        }
        order.setOrderItems(orderItems);
        orderDao.addOrder(order); //将订单信息写入数据表中  一定要写在后面不然计算不了总价
        //将订单信息填入订单信息表中
        for (OrderItem o : orderItems) {
            orderItemDao.addOrderItem(o);
        }
        //清除购物车信息
        session.removeAttribute("cart");
        //获取订单中详细的商品信息
        request.setAttribute("order", order);
        //实现页面转发 转发到.jsp页面 forward是把request请求和response请求都转发过去
        request.getRequestDispatcher("account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
