package com.gec.dao;

import com.gec.entity.Category;
import com.gec.test.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {
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
     * 查询商品分类
     *
     * @return
     */
    public List<Category> getCategoryList() {
        //创建一个字符串用于存储sql语句
        String sql = "SELECT *  FROM category";
        //创建一个List对象用于存放Category类的对象
        List<Category> list = null;
        try {
            //执行sql语句,并将查询到的结果转换成category对象存放进List
            list = qr.query(sql, new BeanListHandler<>(Category.class));   //BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}