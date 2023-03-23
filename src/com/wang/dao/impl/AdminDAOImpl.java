package com.wang.dao.impl;

import com.wang.bean.Admin;
import com.wang.dao.AdminDAO;
import com.wang.dao.BaseDAO;

/**
 * 管理员操作实现
 */
public class AdminDAOImpl extends BaseDAO<Admin> implements AdminDAO {
    @Override
    public Admin checkInfo(Admin admin) {
        String sql = "select * from `book_admin` where `username`=? and `password`=?";
        return getBean(sql, admin.getUsername(), admin.getPassword());
    }

    @Override
    public void updateAdmin(Admin newAdmin) {
        String sql = "update `book_admin` set `password`=? where `username`='root'";
        update(sql, newAdmin.getPassword());
    }
}
