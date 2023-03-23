package com.wang.dao;

import com.wang.bean.LendInfo;

import java.util.List;

/**
 * 借阅信息操作接口
 */
public interface LendInfoDAO {
    /**
     * 插入一个LendInfo对象
     *
     * @param lendInfo 待插入的LendInfo对象
     */
    void insertLendInfo(LendInfo lendInfo);

    /**
     * 添加归还日期
     *
     * @param lendInfo 传入新对象替换原对象
     */
    void addReturn(LendInfo lendInfo);

    /**
     * 查询所有借阅归还记录存放在二维数组中
     *
     * @return 存放所有借阅归还记录的二维数组
     */
    Object[][] searchAllLendInfo();

    /**
     * 查询某个用户的借阅记录存放在二维数组中
     *
     * @param userName 用户名
     * @return 存放某个用户的借阅记录的二维数组
     */
    Object[][] searchOwnLendInfo(String userName);

    /**
     * 查询某个用户的归还记录存放在二维数组中
     *
     * @param userName 用户名
     * @return 存放某个用户的归还记录的二维数组
     */
    Object[][] searchOwnReturnInfo(String userName);

    /**
     * 将根据图书名模糊查询到的借阅信息存放在List集合中
     *
     * @param bookName 输入的查找信息
     * @return 存放所有符合条件的LendInfo对象的List集合
     */
    List<LendInfo> fuzzySearchLendInfoByBookName(String bookName);

    /**
     * 将根据用户名模糊查询到的借阅信息存放在List集合中
     *
     * @param userName 输入的查找信息
     * @return 存放所有符合条件的LendInfo对象的List集合
     */
    List<LendInfo> fuzzySearchLendInfoByUserName(String userName);
}
