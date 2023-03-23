package com.wang.service;

import com.wang.bean.User;

/**
 * 用户操作接口
 */
public interface UserService {
    /**
     * 登录事务
     *
     * @param user 传入用户信息
     * @return 登录成功返回用户信息，失败返回null
     */
    boolean login(User user);

    /**
     * 更改密码事务
     *
     * @param newUser 传入更改密码后的用户信息替换原信息
     */
    void changePassword(User newUser);

    /**
     * 注销用户事务
     *
     * @param user 待注销用户信息
     */
    void cancelUser(User user);

    /**
     * 注册用户事务
     *
     * @param user 待注册用户信息
     * @return 是否注册成功
     */
    boolean registerUser(User user);

    /**
     * 得到所有用户信息存放在二维数组中
     *
     * @return 存放所有用户信息的二维数组
     */
    Object[][] getUserInfos();
}
