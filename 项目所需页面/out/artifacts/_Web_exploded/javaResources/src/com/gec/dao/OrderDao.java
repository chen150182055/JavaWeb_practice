package com.gec.dao;

import com.gec.entity.Order;
import com.gec.entity.OrderItem;
import com.gec.test.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderDao {
    //QueryRunner类是Dbutils的核心类之一，它显著的简化了SQL的查询 并与ResultSetHandel协同工作将使代码量大为减少
    /**
     * QueryRunner包含下面几个方法
     * 1.query(Connection conn, String sql, Object[] params, ResultSetHandler rsh)：执行选择查询，在查询中，对象阵列的值被用来作为查询的置换参数。
     * 2.query(String sql, Object[] params, ResultSetHandler rsh)：方法本身不提供数据库连接，执行选择查询，在查询中，对象阵列的值被用来作为查询的置换参数。
     * 3.query(Connection conn, String sql, ResultSetHandler rsh)：执行无需参数的选择查询。
     * 4.update(Connection conn, String sql, Object[] params)：被用来执行插入、更新或删除（DML）操作
     *
     * 其中，ResultSetHandler接口执行处理一个结果集对象，将数据转变并处理为任何一种形式，供其他应用使用
     * 1.ArrayHandler：把结果集中的第一行数据转成对象数组。
     * 2.ArrayListHandler：把结果集中的每一行数据都转成一个对象数组，再存放到List中。
     * 3.BeanHandler：将结果集中的第一行数据封装到一个对应的JavaBean实例中。
     * 4.BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
     * 5.MapHandler：将结果集中的第一行数据封装到一个Map里，key是列名，value就是对应的值
     * 6.MapListHandler：将结果集中的每一行数据都封装到一个Map里，然后再存放到List
     * 7.ColumnListHandler：将结果集中某一列的数据存放到List中
     * 8.KeyedHandler(name)：将结果集中的每一行数据都封装到一个Map里(List<Map>)，再把这些map再存到一个map里，其key为指定的列。
     * 9.ScalarHandler:将结果集第一行的某一列放到某个对象中
     */


    /**
     *	 根据订单编号来获取订单信息
     *
     * @param oid
     * @return
     */

    public Order getOrderByOid(String oid) {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        //创建一个字符串用来存放sql语句 从Order表中根据 oid 和 ordertime 查询
        String sql = "select * from Orders where oid=? order by ordertime desc";
        //创建一个Order对象
        Order order = null;
        try {
            //执行sql语句,将查询到的结果转换成order对象
            order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;  //返回该order对象
    }

    public int addOrder(Order order){
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        //创建一个字符串用来存放sql语句 插入Orders表中
        String sql="insert into orders values(?,now(),?,?,?,?,?,?,null)";
        //创建一个字符串数组 用来存放等下即将用来替换的参数
        String[] arr={order.getOid(),String.valueOf(order.getTotal()),String.valueOf(order.getState()),
                order.getAddress(),order.getName(),order.getTelephone(),order.getUid()};
        int n=0;
        try {
            //执行更新语句 arr字符串为需要替换的参数
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

    /**
     * 删除
     * @param oid
     * @return n
     */
    public int deleteOrderItemByOid(String oid) {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        //删除订单记录表中的信息前首先判断他的子表 中的有没有数据
        OrderItemDao orderItemDao = new OrderItemDao();
        //先删除子表中的数据
        List<OrderItem> orderItemByOid = orderItemDao.getOrderItemByOid(oid);
        if(orderItemByOid.size()>0) {
            orderItemDao.deleteOrderItemByOid(oid);
        }

        //再删除主表中的数据
        String sql="delete from orders where oid= ?";
        int n=0;
        try {
            n = qr.update(sql,oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public List<Order> getOrderListByUid(String uid) {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        List<Order> list=null;
        String sql="select * from Orders where uid=?";
        try {
            list=qr.query(sql, new BeanListHandler<Order>(Order.class),uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
