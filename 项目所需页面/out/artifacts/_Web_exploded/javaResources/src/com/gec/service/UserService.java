package com.gec.service;

import com.gec.dao.UserDao;
import com.gec.entity.User;

//用户服务层
public class UserService {
    UserDao userDao = new UserDao();

    public User login(String username, String password) {   //用户登录方法
        try {
            //创建一个User对象调用findUserByUsernameAndPassword（通过用户名和密码）
            User user = userDao.findUserByUsernameAndPassword(username, password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updateUserInfo(User user) {
        return userDao.updateUserInfo(user);
    }
}
