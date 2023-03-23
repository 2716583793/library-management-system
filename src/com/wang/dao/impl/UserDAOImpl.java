package com.wang.dao.impl;

import com.wang.bean.User;
import com.wang.dao.BaseDAO;
import com.wang.dao.UserDAO;

import java.util.List;

/**
 * 用户操作实现
 */
public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    @Override
    public User checkInfo(User user) {
        String sql = "select * from `book_user` where `username`=? and `password`=?";
        return getBean(sql, user.getUsername(), user.getPassword());
    }

    @Override
    public boolean insertUser(User User) {
        String sql = "insert into `book_user`(`username`,`password`)values(?,?)";
        return update(sql, User.getUsername(), User.getPassword()) > 0;
    }

    @Override
    public void updateUser(User newUser) {
        String sql = "update `book_user` set `password`=? where `username`=?";
        update(sql, newUser.getPassword(), newUser.getUsername());
    }

    @Override
    public void deleteUser(User user) {
        String sql = "delete from `book_user` where `username`=? and `password`=?";
        update(sql, user.getUsername(), user.getPassword());
    }

    @Override
    public Object[][] getUserArrayList() {
        String sql = "select * from `book_user`";
        List<Object[]> l = getArrayList(sql);
        Object[][] list = new Object[l.size()][2];
        int i = 0;
        for (Object[] o : l) {
            list[i++] = o;
        }
        return list;
    }

    @Override
    public boolean isExists(String username) {
        String sql = "select * from `book_user` where `username`=?";
        return getBean(sql, username) != null;
    }
}
