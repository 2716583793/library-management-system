package com.wang.dao.impl;

import com.wang.bean.Book;
import com.wang.dao.BaseDAO;
import com.wang.dao.BookDAO;

import java.util.List;

/**
 * 图书操作实现
 */
public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {
    @Override
    public void insertBook(Book book) {
        String sql = "insert into `book_info`(`bookName`,`author`,`gender`,`price`,`bookNum`,`typeName`,`bookDesc`)values(?,?,?,?,?,?,?)";
        update(sql, book.getBookName(), book.getAuthor(), book.getGender(), book.getPrice(), book.getBookNum(), book.getTypeName(), book.getBookDesc());
    }

    @Override
    public void addAmountByBookNum(Book book) {
        String sql = "update `book_info` set `bookNum`=`bookNum`+? where `bookName`=?";
        update(sql, book.getBookNum(), book.getBookName());
    }

    @Override
    public void deleteBookById(int bookId) {
        String sql = "delete from `book_info` where `bookId`=?";
        update(sql, bookId);
    }

    @Override
    public Book searchBookById(int bookId) {
        String sql = "select * from `book_info` where `bookId`=?";
        return getBean(sql, bookId);
    }

    @Override
    public void updateBook(Book newBook) {
        String sql = "update `book_info` set `bookName`=?,`author`=?,`gender`=?,`price`=?,`bookNum`=?,`typeName`=?,`bookDesc`=? where `bookId`=?";
        update(sql, newBook.getBookName(), newBook.getAuthor(), newBook.getGender(), newBook.getPrice(), newBook.getBookNum(), newBook.getTypeName(), newBook.getBookDesc(), newBook.getBookId());
    }


    @Override
    public Object[][] searchAllBookToArray() {
        String sql = "select * from `book_info`";
        List<Object[]> l = getArrayList(sql);
        Object[][] list = new Object[l.size()][8];
        int i = 0;
        for (Object[] o : l) {
            list[i++] = o;
        }
        return list;
    }

    @Override
    public List<Book> fuzzySearchBookByBookName(String bookName) {
        String sql = "select * from `book_info` where `bookName` like ?";
        return getBeanList(sql, "%" + bookName + "%");
    }

    @Override
    public List<Book> fuzzySearchBookByAuthor(String author) {
        String sql = "select * from `book_info` where `author` like ?";
        return getBeanList(sql, "%" + author + "%");
    }

    @Override
    public List<Book> searchBookByTypeName(String typeName) {
        String sql = "select * from `book_info` where `typeName`=?";
        return getBeanList(sql, typeName);
    }

    @Override
    public void delAmount(String bookName) {
        String sql = "update `book_info` set `bookNum`=`bookNum`-1 where `bookName`=?";
        update(sql, bookName);
    }

    @Override
    public void addAmount(String bookName) {
        String sql = "update `book_info` set `bookNum`=`bookNum`+1 where `bookName`=?";
        update(sql, bookName);
    }

    @Override
    public boolean isExists(String bookName) {
        String sql = "select * from `book_info` where `bookName`=?";
        return getBean(sql, bookName) != null;
    }
}
