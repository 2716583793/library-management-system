package com.wang.dao;

import com.wang.bean.User;

/**
 * 用户操作接口
 */
public interface UserDAO {
    /**
     * 搜索数据库得到对应User对象
     *
     * @param user 传入一个User对象
     * @return 存在则返回对象，不存在则返回null
     */
    User checkInfo(User user);

    /**
     * 插入一个User对象
     *
     * @param User 待插入的User对象
     * @return 是否成功插入
     */
    boolean insertUser(User User);

    /**
     * 修改User对象密码信息
     *
     * @param newUser 传入新对象替换原对象修改密码信息
     */
    void updateUser(User newUser);

    /**
     * 删除指定User对象
     *
     * @param user 待删除的User对象
     */
    void deleteUser(User user);

    /**
     * 得到所有用户信息存放在二维数组中
     *
     * @return 得到存放所有用户信息的二维数组
     */
    Object[][] getUserArrayList();

    /**
     * 是否存在与参数同名对象
     *
     * @param username 用户名
     * @return 是否存在同名
     */
    boolean isExists(String username);
}
