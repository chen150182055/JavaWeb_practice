package com.gec.service;

import com.gec.dao.OrderDao;
import com.gec.dao.OrderItemDao;
import com.gec.entity.*;

import java.util.*;
import java.util.Map.Entry;

public class OrderService {
    OrderDao orderDao = new OrderDao();
    private OrderItemDao orderItemDao=new OrderItemDao();

    /**
     *
     * @param user
     * @param cart
     * @return
     */
    //编写业务逻辑代码去产生订单
    public Order creatOrder(User user, Cart cart) {   //根据这两个对象去产生一个订单
        //创建一个订单对象
        Order order = new Order();
        order.setOid(UUID.randomUUID().toString());
        order.setAddress(user.getAddress());
        order.setName(user.getName());
        order.setOrderTime(String.valueOf(new Date()));
        order.setState(0);
        order.setTelephone(user.getTelephone());
        order.setUid(user.getUid());

        orderDao.addOrder(order);

        List<OrderItem> orderItems = new ArrayList<>();

        Map<String, CartItem> cartItems = cart.getCartItems();

        Set<Entry<String, CartItem>> entrySet = cartItems.entrySet();
        for (Entry<String, CartItem> entry : entrySet) {
            CartItem cartItem = entry.getValue();

            OrderItem orderItem = new OrderItem();
            orderItem.setCount(cartItem.getBuyNum());
            orderItem.setItemId(UUID.randomUUID().toString());
            orderItem.setOid(order.getOid());

            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);

        return order;
    }

    /**
     *
     * @return
     */
    public List<Order> getAllOrderList() {
        List<Order> list = orderDao.getAllOrderList();
        for(Order o:list){
            List<OrderItem> itemList = orderItemDao.getOrderItemByOid(o.getOid());
            o.setOrderItems(itemList);
        }
        return list;
    }

    /**
     *
     * @param oid
     * @return
     */
    public Order getOrderByOid(String oid) {
        Order order=orderDao.getOrderByOid(oid);
        order.setOrderItems(orderItemDao.getOrderItemByOid(oid));
        return order;
    }

    /**
     *
     * @param oid
     * @param assess
     * @return
     */
    public int assessOrder(String oid,String assess) {
        return orderDao.assessOrder(oid,assess);
    }

    /**
     *
     * @param oid
     * @return
     */
    public int updateOrderState(String oid) {
        return orderDao.updateOrderState(oid);
    }

}
