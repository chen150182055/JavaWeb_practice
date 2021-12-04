package com.gec.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class C3P0Demo01 {

    public static void main(String[] args) {
        Connection conn = null;
        try {
//        1、创建数据库连接池对象
            DataSource ds = new ComboPooledDataSource();
//        2、获取连接对象
            conn= ds.getConnection();
            System.out.println(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
