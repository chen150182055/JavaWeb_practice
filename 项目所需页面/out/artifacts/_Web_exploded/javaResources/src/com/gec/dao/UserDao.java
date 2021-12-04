package com.gec.dao;

import com.gec.entity.User;
import com.gec.test.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class UserDao {
    public User findUserByUsernameAndPassword(String username, String password) throws Exception {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String[] args = {username, password};
        User user = queryRunner.query("select * from user where username=? and password=?", args, new BeanHandler<>(User.class));
        return user;
    }
}
