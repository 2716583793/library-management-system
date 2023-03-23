package com.wang.service;

import com.wang.bean.Admin;

/**
 * 管理员操作接口
 */
public interface AdminService {
    /**
     * 登录事务
     *
     * @param admin 传入管理员信息
     * @return 登录成功返回管理员信息，失败返回null
     */
    boolean login(Admin admin);

    /**
     * 更改密码事务
     *
     * @param newAdmin 传入更改密码后的管理员信息替换原信息
     */
    void changePassword(Admin newAdmin);
}
