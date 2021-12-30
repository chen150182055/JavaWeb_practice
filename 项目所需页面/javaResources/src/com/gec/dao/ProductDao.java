package com.gec.dao;

import com.gec.entity.Product;
import com.gec.test.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
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

    private QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

    /**
     * 获取热门商品
     *
     * @return
     */
    public List<Product> getHotProductList() {
        //创建一个字符串用来存放sql语句 从product表中查询
        String sql = "SELECT * FROM product WHERE is_hot = 1 LIMIT 0,12";
        //创建一个List兑现用来存放Product对象
        List<Product> list = null;
        try {
            //调用query方法执行sql语句,将查询到的结果转换成Product对象传给list
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
     * 更新商品信息
     * @param product
     * @return
     */
    public int updateProduct(Product product) {
        String sql="update product set pname=?,is_hot=?,market_price=?,"
                + "shop_price=?,cid=?,pdesc=? where pid=?";
        Object[] arr={product.getPname(),product.getIs_hot(),product.getMarket_price(),
                product.getShop_price(),product.getCid(),product.getPdesc(),
                product.getPid()};
        int n=0;
        try {
            n = qr.update(sql,arr);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return n;
    }

    /**
     * 分页查询商品记录
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
     * 添加商品信息
     * @param product
     * @return
     */
    public int addProduct(Product product) {
        String sql="insert into product values(?,?,?,?,?,now(),?,?,0,?)";
        Object[] arr={product.getPid(),product.getPname(),product.getMarket_price(),
                product.getShop_price(),product.getPimage(),product.getIs_hot(),
                product.getPdesc(),product.getCid()};
        int n=0;
        try {
            n = qr.update(sql,arr);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return n;
    }

    /**
     * 删除商品信息
     * @param pid
     * @return
     */
    public int deleteProductByPid(String pid) {
        //删除订单记录表中的信息前首先判断他的子表 中的有没有数据
        ProductDao productDao= new ProductDao();
        //先删除子表中的数据
        Product product = productDao.getProductByPid(pid);

        //再删除主表中的数据
        String sql="delete from product where pid= ?";
        int N=0;
        try {
            N = qr.update(sql,pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return N;
    }

    /**
     * 模糊搜索所有商品信息
     * @param search
     * @return
     */
    public List<Product> searchProduct(String search) {
        QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
        String sql="select * from Product where pname like ? or pdesc like ?";
        List<Product> list=null;
        try {
            //query(Connection conn, String sql, Object[] params, ResultSetHandler rsh)：执行选择查询，在查询中，对象阵列的值被用来作为查询的置换参数。
            list = qr.query(sql, new BeanListHandler<Product>(Product.class),search,search);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 通过cid获取产品信息
     * @param cid
     * @return
     */
    public List<Product> getProductListByCid(String cid) {
        String sql="select * from Product where cid=?";
        List<Product> list=null;
        try {
            //query(Connection conn, String sql, Object[] params, ResultSetHandler rsh)：执行选择查询，在查询中，对象阵列的值被用来作为查询的置换参数。
            list = qr.query(sql, new BeanListHandler<Product>(Product.class),cid);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return list;

    }

    /**
     * 获取产品列表
     * @return
     */
    public List<Product> getProductList() {
        String sql="select * from Product";
        List<Product> list=new ArrayList<Product>();
        try {
            list = qr.query(sql,new BeanListHandler<Product>(Product.class));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }
}
