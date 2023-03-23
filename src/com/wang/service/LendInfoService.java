package com.wang.service;

import com.wang.bean.LendInfo;

import java.util.List;

/**
 * 借阅信息操作接口
 */
public interface LendInfoService {
    /**
     * 借书事务
     *
     * @param lendInfo 借阅信息
     */
    void lendBook(LendInfo lendInfo);

    /**
     * 还书事务
     *
     * @param lendInfo 借阅信息
     */
    void returnBook(LendInfo lendInfo);

    /**
     * 查询所有借阅归还记录存放在二维数组中
     *
     * @return 存放所有借阅归还记录的二维数组
     */
    Object[][] getAllLendInfo();

    /**
     * 查询某个用户的借阅记录存放在二维数组中
     *
     * @param userName 用户名
     * @return 存放某个用户的借阅记录的二维数组
     */
    Object[][] getOwnLendInfo(String userName);

    /**
     * 查询某个用户的归还记录存放在二维数组中
     *
     * @param userName 用户名
     * @return 存放某个用户的归还记录的二维数组
     */
    Object[][] getOwnReturnInfo(String userName);

    /**
     * 将根据图书名模糊查询到的借阅信息存放在List集合中
     *
     * @param bookName 输入的查找信息
     * @return 存放所有符合条件的LendInfo对象的List集合
     */
    List<LendInfo> getLendInfoListByBookName(String bookName);

    /**
     * 将根据用户名模糊查询到的借阅信息存放在List集合中
     *
     * @param userName 输入的查找信息
     * @return 存放所有符合条件的LendInfo对象的List集合
     */
    List<LendInfo> getLendInfoListByUserName(String userName);
}
