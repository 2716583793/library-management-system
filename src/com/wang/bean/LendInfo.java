package com.wang.bean;

import java.sql.Date;

/**
 * 图书借阅信息实体类
 */
public class LendInfo {
    private Integer lendId; //图书借阅id
    private String bookName; //借阅图书名称
    private String userName; //借阅者用户名
    private Date lendDate; //借阅时间
    private String returnDate; //归还时间

    public LendInfo() {
    }

    public LendInfo(Integer lendId, String bookName, String userName, Date lendDate, String returnDate) {
        this.lendId = lendId;
        this.bookName = bookName;
        this.userName = userName;
        this.lendDate = lendDate;
        this.returnDate = returnDate;
    }

    public Integer getLendId() {
        return lendId;
    }

    public void setLendId(Integer lendId) {
        this.lendId = lendId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
