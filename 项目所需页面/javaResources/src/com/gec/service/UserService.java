package com.gec.service;

import com.gec.dao.UserDao;
import com.gec.entity.User;

public class UserService {
    UserDao userDao = new UserDao();

    public User login(String username, String password) {
        try {
            User user=userDao.findUserByUsernameAndPassword(username, password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
