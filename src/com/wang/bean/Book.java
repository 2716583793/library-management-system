package com.wang.bean;

/**
 * 图书信息实体类
 */
public class Book {
    private Integer bookId; //图书id
    private String bookName; //图书名称
    private String author; //图书作者
    private String gender; //作者性别
    private Double price; //图书价格
    private Integer bookNum; //库存数量
    private String typeName; //类别名称
    private String bookDesc; //图书描述

    public Book() {
    }

    public Book(Integer bookId, String bookName, String author, String gender, Double price, Integer bookNum, String typeName, String bookDesc) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.gender = gender;
        this.price = price;
        this.bookNum = bookNum;
        this.typeName = typeName;
        this.bookDesc = bookDesc;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getBookNum() {
        return bookNum;
    }

    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }
}
