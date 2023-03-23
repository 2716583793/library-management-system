package com.wang.dao;

import com.wang.bean.Admin;

/**
 * 管理员操作接口
 */
public interface AdminDAO {
    /**
     * 搜索数据库得到对应Admin对象
     *
     * @param admin 传入一个Admin对象
     * @return 存在则返回对象，不存在则返回null
     */
    Admin checkInfo(Admin admin);

    /**
     * 修改Admin对象密码信息
     *
     * @param newAdmin 传入新对象替换原对象修改密码信息
     */
    void updateAdmin(Admin newAdmin);
}
