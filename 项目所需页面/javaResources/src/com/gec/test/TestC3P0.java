package com.gec.test;

import com.gec.dao.UserDao;
import com.gec.entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TestC3P0 {

    public void test() throws Exception{
        //直接从c3p0数据连接工具 中获取数据库链接
        Connection connection =C3P0Utils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("select * from category");
        while (set.next()){
            System.out.println(set.getInt(1)+"\t\t\t"+set.getString(2));
        }
    }

    @Test
    public void testc() throws SQLException{
        //QueryRunner对象是数据库工具类里面的对象 它的作用是可以传输SQL语句 以及处理响应结果
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        //如果我想查询user用户表中的的数据 将数据展示到java程序中
        User user = queryRunner.query("select * from user", new BeanHandler<>(User.class));
        System.out.println(user);
        //如果你想接受一组数据的话 使用
        List<User> users = queryRunner.query("select * from user",new BeanListHandler<>(User.class));
        for (User u:users){
            System.out.println(u);
        }
    }

    //dao层数据库访问层代码 写完以后需要进行测试 确保没有问题
    @Test
    public  void testDao() throws Exception{
        UserDao userDao = new UserDao();
        User user = userDao.findUserByUsernameAndPassword("ccc","ccc");
        System.out.println(user);
        User user2=userDao.findUserByUsernameAndPassword("123456","CCCC213");
        System.out.println(user2);
    }
}
