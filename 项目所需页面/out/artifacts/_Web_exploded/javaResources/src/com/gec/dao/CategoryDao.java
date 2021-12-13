package com.gec.dao;

import com.gec.entity.Category;
import com.gec.test.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {

    private QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

    /**
     * 查询商品分类
     *
     * @return
     */
    public List<Category> getCategoryList() {
        String sql = "SELECT *  FROM category";
        List<Category> list = null;
        try {
            list = qr.query(sql, new BeanListHandler<>(Category.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}