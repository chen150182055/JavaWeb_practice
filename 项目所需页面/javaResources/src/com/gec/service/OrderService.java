package com.gec.service;

import com.gec.dao.OrderDao;
import com.gec.entity.*;

import java.util.*;
import java.util.Map.Entry;

public class OrderService {
    OrderDao orderDao = new OrderDao();

    //编写业务逻辑代码去产生订单
    public Order creatOrder(User user, Cart cart) {   //根据这两个对象去产生一个订单
        //创建一个订单对象
        Order order = new Order();
        

        return order;
    }

}
