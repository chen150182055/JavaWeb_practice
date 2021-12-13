package com.gec.dao;

import com.gec.entity.User;
import com.gec.test.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {

    QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

    public User findUserByUsernameAndPassword(String username, String password) throws Exception {

        String[] args = {username, password};
        User user = queryRunner.query("select * from user where username=? and password=?", args, new BeanHandler<>(User.class));
        return user;
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM USER WHERE username = ?";
        User user = null;
        try {
            user = queryRunner.query(sql, new BeanHandler<User>(User.class), username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public int addUser(User user) {
        String sql = "INSERT INTO USER VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        String [] arr = {user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail()
                ,user.getTelephone(),user.getBirthday(),user.getSex(),String.valueOf(user.getState()),user.getCode()
                ,user.getAddress()};
        int row = 0;
        try {
            row = queryRunner.update(sql, arr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
}
