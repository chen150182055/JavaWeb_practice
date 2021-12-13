package com.gec.dao;

import com.gec.entity.OrderItem;
import com.gec.entity.Product;
import com.gec.test.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderItemDao {
    QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

    /**
     * 	根据商品订单id查询对应的订单的商品信息
     * @param oid
     * @return
     */
    public List<OrderItem> getOrderItemByOid(String oid){
        List<OrderItem> list=null;
        String sql="select * from OrderItem where oid=?";
        try {
            list=qr.query(sql, new BeanListHandler<OrderItem>(OrderItem.class),oid);
            //执行完这个sql语句后 会见这个返回结果的uid复制给我们product数组
            List<Product> list1=qr.query(sql, new BeanListHandler<Product>(Product.class),oid);

            ProductDao productDao=new ProductDao();
            for(int i=0;i<list.size();i++){
                Product product = productDao.getProductByPid(list1.get(i).getPid());
                list.get(i).setProduct(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
