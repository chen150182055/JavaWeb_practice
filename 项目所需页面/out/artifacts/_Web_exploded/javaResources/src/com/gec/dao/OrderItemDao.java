package com.gec.dao;

import com.gec.entity.OrderItem;
import com.gec.entity.Product;
import com.gec.test.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderItemDao {
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
     * 	根据商品订单id查询对应的订单的商品信息
     * @param oid
     * @return
     */
    public List<OrderItem> getOrderItemByOid(String oid){
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        //创建一个List用来存放OrderItem的对象
        List<OrderItem> list=null;
        //创建字符串用来存放sql语句 从OrderItem 中查询 符合oid 的字段
        String sql="select * from OrderItem where oid=?";
        try {
            //执行sql语句,将查询到的结果转换成OrderItem对象存放在list中
            list=qr.query(sql, new BeanListHandler<OrderItem>(OrderItem.class),oid);
            //执行完这个sql语句后 会将这个返回结果的uid复制给我们product数组
            List<Product> list1=qr.query(sql, new BeanListHandler<Product>(Product.class),oid);

            //创建一个ProductDao对象
            ProductDao productDao=new ProductDao();
            for(int i=0;i<list.size();i++){   //遍历刚刚那个list（充满了orderitem对象）
                Product product = productDao.getProductByPid(list1.get(i).getPid());   //调用ProductDao中的方法
                list.get(i).setProduct(product);    //将第i个元素返回
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;  //返回这个list
    }

    //添加订单商品信息
    public int addOrderItem(OrderItem orderItem){
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        //创建一个字符串用来存放sql语句 将信息插入 OrderItem 表中
        String sql="insert into OrderItem values(?,?,?,?,?)";
        //创建一个数组用来存放待会用来执行update方法的替换参数
        Object[] arr={orderItem.getItemId(),orderItem.getCount(),orderItem.getSubtotal(),
                orderItem.getProduct().getPid(),orderItem.getOid()};
        int n=0;
        try {
            //调用update方法执行sql语句 同时把结果返回给 n
            n = qr.update(sql,arr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
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
}
