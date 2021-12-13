package com.gec.dao;

import com.gec.entity.Order;
import com.gec.test.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderDao {

    QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
    /**
     *	 根据订单编号来获取订单信息
     *
     * @param oid
     * @return
     */
    public Order getOrderByOid(String oid) {
        String sql = "select * from Orders where oid=? order by ordertime desc";
        Order order = null;
        try {
            order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public int addOrder(Order order){
        String sql="insert into orders values(?,now(),?,?,?,?,?,?,null)";
        String[] arr={order.getOid(),String.valueOf(order.getTotal()),String.valueOf(order.getState()),
                order.getAddress(),order.getName(),order.getTelephone(),order.getUid()};
        int n=0;
        try {
            n = qr.update(sql,arr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    /**
     * 修改订单状态
     *
     * @param oid
     * @return
     */
    public int updateOrderState(String oid) {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "update orders set state=state+1 where oid=?";
        int n = 0;
        try {
            n = qr.update(sql, oid);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return n;
    }

}
