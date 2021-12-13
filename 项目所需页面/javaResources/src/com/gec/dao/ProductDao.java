package com.gec.dao;

import com.gec.entity.Product;
import com.gec.test.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDao {
    private QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

    /**
     * 获取热门商品
     *
     * @return
     */
    public List<Product> getHotProductList() {
        String sql = "SELECT * FROM product WHERE is_hot = 1 LIMIT 0,12";
        List<Product> list = null;
        try {
            list = qr.query(sql, new BeanListHandler<>(Product.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取最新的商品
     *
     * @return
     */
    public List<Product> getNewProductList() {
        String sql = "SELECT * FROM product ORDER BY pdate DESC LIMIT 0,12";
        List<Product> list = null;
        try {
            list = qr.query(sql, new BeanListHandler<>(Product.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 通过商品编号查询对应的商品信息
     *
     * @param pid
     * @return
     */
    public Product getProductByPid(String pid) {
        String sql = "select * from product where pid = ?";
        Product product = null;

        try {
            product = qr.query(sql, new BeanHandler<>(Product.class), pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    /**
     * 计算商品表中有多少条记录
     *
     * @return
     */
    public long getCount(String cid) {
        String sql = null;
        long count = 0;

        // 查询主页的情况
        if (cid == null) {
            sql = "select count(pid) from product";
            try {
                count = (long) qr.query(sql, new ScalarHandler());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {// 查询有分类选择商品分类页面的信息
            sql = "select count(pid) from product where cid = ?";
            try {
                count = (long) qr.query(sql, new ScalarHandler(), cid);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    /**
     * 分页查询商品记录
     *
     * @param currentPage
     * @param size
     * @return
     */
    public List<Product> getProduct(int currentPage, int size) {
        if (currentPage <= 0) {
            currentPage = 0;
        }
        String sql = "SELECT * FROM product LIMIT " + currentPage + "," + size;
        List<Product> list = null;

        try {
            list = qr.query(sql, new BeanListHandler<>(Product.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 通过商品分类id来查询商品信息
     *
     * @param start
     * @param size
     * @param cid
     * @return
     */
    public List<Product> getProductByCid(int start, int size, String cid) {
        if (start <= 0) {
            start = 0;
        }
        String sql = "SELECT * FROM product WHERE cid = ? LIMIT " + start + " , " + size;
        List<Product> list = null;

        try {
            list = qr.query(sql, new BeanListHandler<>(Product.class), cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 	模糊搜索所有商品信息
     * @param search
     * @return
     */
    public List<Product> searchProduct(String search) {
        QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
        String sql="select * from Product where pname like ? or pdesc like ?";
        List<Product> list=null;
        try {
            list = qr.query(sql, new BeanListHandler<Product>(Product.class),search,search);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
