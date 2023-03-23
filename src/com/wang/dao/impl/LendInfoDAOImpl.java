package com.wang.dao.impl;

import com.wang.bean.LendInfo;
import com.wang.dao.BaseDAO;
import com.wang.dao.LendInfoDAO;

import java.util.List;

/**
 * 借阅信息操作实现
 */
public class LendInfoDAOImpl extends BaseDAO<LendInfo> implements LendInfoDAO {
    @Override
    public void insertLendInfo(LendInfo lendInfo) {
        String sql = "insert into `book_lend`(`bookName`,`userName`,`lendDate`,`returnDate`)values(?,?,?,?)";
        update(sql, lendInfo.getBookName(), lendInfo.getUserName(), lendInfo.getLendDate(), lendInfo.getReturnDate());
    }

    @Override
    public void addReturn(LendInfo lendInfo) {
        String sql = "update `book_lend` set `returnDate`=? where `lendId`=?";
        update(sql, lendInfo.getReturnDate(), lendInfo.getLendId());
    }

    @Override
    public Object[][] searchAllLendInfo() {
        String sql = "select * from `book_lend`";
        List<Object[]> l = getArrayList(sql);
        Object[][] list = new Object[l.size()][5];
        int i = 0;
        for (Object[] o : l) {
            list[i++] = o;
        }
        return list;
    }

    private Object[][] getObjects(String userName, String sql) {
        List<Object[]> l = getArrayList(sql, userName);
        Object[][] list = new Object[l.size()][4];
        int i = 0;
        for (Object[] o : l) {
            list[i++] = o;
        }
        return list;
    }

    @Override
    public Object[][] searchOwnLendInfo(String userName) {
        String sql = "select `lendId`,`bookName`,`lendDate`,`returnDate` from `book_lend` where `userName`=? and `returnDate`='未归还'";
        return getObjects(userName, sql);
    }

    @Override
    public Object[][] searchOwnReturnInfo(String userName) {
        String sql = "select `lendId`,`bookName`,`lendDate`,`returnDate` from `book_lend` where `userName`=? and `returnDate`!='未归还'";
        return getObjects(userName, sql);
    }

    @Override
    public List<LendInfo> fuzzySearchLendInfoByBookName(String bookName) {
        String sql = "select * from `book_lend` where `bookName` like ?";
        return getBeanList(sql, "%" + bookName + "%");
    }

    @Override
    public List<LendInfo> fuzzySearchLendInfoByUserName(String userName) {
        String sql = "select * from `book_lend` where `userName` like ?";
        return getBeanList(sql, "%" + userName + "%");
    }
}
