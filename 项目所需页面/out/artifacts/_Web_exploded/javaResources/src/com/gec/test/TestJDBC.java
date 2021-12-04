package com.gec.test;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJDBC {
    static final String URL = "jdbc:mysql://localhost:3306/shopdb?characterEncoding=utf-8";
    static final String USERNAME = "root";
    static final String PASSWORD = "5201314";
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    @Test
    public void testJDBC() throws Exception{
        Class.forName(DRIVER);
        Connection connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("select * from category");
        while (set.next()){
            System.out.println(set.getInt(1)+"\t\t\t"+set.getString(2));
        }
        set.close();
        statement.close();
        connection.close();
    }



    public static void main(String[] args) throws Exception {

    }
}
