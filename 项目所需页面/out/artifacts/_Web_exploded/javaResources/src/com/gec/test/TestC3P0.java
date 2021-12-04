package com.gec.test;

import com.gec.dao.UserDao;
import com.gec.entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.jupiter.api.Test;

import javax.jws.soap.SOAPBinding;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TestC3P0 {

    public void test() throws Exception{
        Connection connection =C3P0Utils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("select * from category");
        while (set.next()){
            System.out.println(set.getInt(1)+"\t\t\t"+set.getString(2));
        }
    }

    @Test
    public void testc() throws SQLException{
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        User user = queryRunner.query("select * from user", new BeanHandler<>(User.class));
        System.out.println(user);
        List<User> users = queryRunner.query("select * from user",new BeanListHandler<>(User.class));
        for (User u:users){
            System.out.println(u);
        }
    }

    @Test
    public  void testDao() throws Exception{
        UserDao userDao = new UserDao();
        User user = userDao.findUserByUsernameAndPassword("ccc","ccc");
        System.out.println(user);
        User user2=userDao.findUserByUsernameAndPassword("123456","CCCC213");
        System.out.println(user2);
    }
}
