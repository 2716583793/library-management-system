package com.wang.service.impl;

import com.wang.bean.User;
import com.wang.dao.UserDAO;
import com.wang.dao.impl.UserDAOImpl;
import com.wang.service.UserService;

/**
 * 用户操作实现
 */
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean login(User user) {
        return userDAO.checkInfo(user) != null;
    }

    @Override
    public void changePassword(User newUser) {
        userDAO.updateUser(newUser);
    }

    @Override
    public void cancelUser(User user) {
        userDAO.deleteUser(user);
    }

    @Override
    public boolean registerUser(User user) {
        if (userDAO.isExists(user.getUsername())) {
            return false;
        }
        return userDAO.insertUser(user);
    }

    @Override
    public Object[][] getUserInfos() {
        return userDAO.getUserArrayList();
    }
}
