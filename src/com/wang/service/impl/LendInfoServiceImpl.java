package com.wang.service.impl;

import com.wang.bean.LendInfo;
import com.wang.dao.LendInfoDAO;
import com.wang.dao.impl.LendInfoDAOImpl;
import com.wang.service.LendInfoService;

import java.util.List;

/**
 * 借阅信息操作实现
 */
public class LendInfoServiceImpl implements LendInfoService {

    private final LendInfoDAO lendInfoDAO = new LendInfoDAOImpl();

    @Override
    public void lendBook(LendInfo lendInfo) {
        lendInfoDAO.insertLendInfo(lendInfo);
    }

    @Override
    public void returnBook(LendInfo lendInfo) {
        lendInfoDAO.addReturn(lendInfo);
    }

    @Override
    public Object[][] getAllLendInfo() {
        return lendInfoDAO.searchAllLendInfo();
    }

    @Override
    public Object[][] getOwnLendInfo(String userName) {
        return lendInfoDAO.searchOwnLendInfo(userName);
    }

    @Override
    public Object[][] getOwnReturnInfo(String userName) {
        return lendInfoDAO.searchOwnReturnInfo(userName);
    }

    @Override
    public List<LendInfo> getLendInfoListByBookName(String bookName) {
        return lendInfoDAO.fuzzySearchLendInfoByBookName(bookName);
    }

    @Override
    public List<LendInfo> getLendInfoListByUserName(String userName) {
        return lendInfoDAO.fuzzySearchLendInfoByUserName(userName);
    }
}
