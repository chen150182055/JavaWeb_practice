package com.gec.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

//购物车实体类的实现
public class Cart {
    //创建Map对象 carItems
    private Map<String, CartItem> cartItems = new HashMap<String, CartItem>();  //数据采用哈希表结构

    //定义一个double变量total用来存放总数
    private double total;

    /**
     * 返回Map对象
     * @return
     */
    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }

    /**
     * 设置Map对象
     * @param cartItems
     */
    public void setCartItems(Map<String, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    /**
     * 获取总数
     * @return
     */
    public double getTotal() {
        total = 0;
        //创建一个Set对象set用来存放
        /**
         * Map中采用Entry内部类来表示一个映射项，映射项包含Key和Value (我们总说键值对键值对, 每一个键值对也就是一个Entry)
         * Map.Entry里面包含getKey()和getValue()方法
         */
        Set<Entry<String, CartItem>> set = cartItems.entrySet();
        for (Entry<String, CartItem> c : set) {     //遍历
            total += c.getValue().getSubTotal();   //将
        }
        return total;
    }

    @Override
    public String toString() {
        return "Cart [cartItems=" + cartItems + ", total=" + total + "]";
    }


}
