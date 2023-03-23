package com.wang.service.impl;

import com.wang.bean.Admin;
import com.wang.dao.AdminDAO;
import com.wang.dao.impl.AdminDAOImpl;
import com.wang.service.AdminService;

/**
 * 管理员操作实现
 */
public class AdminServiceImpl implements AdminService {

    private final AdminDAO adminDAO = new AdminDAOImpl();

    @Override
    public boolean login(Admin admin) {
        return adminDAO.checkInfo(admin) != null;
    }

    @Override
    public void changePassword(Admin newAdmin) {
        adminDAO.updateAdmin(newAdmin);
    }
}
