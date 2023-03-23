package com.wang.service.impl;

import com.wang.bean.Book;
import com.wang.dao.BookDAO;
import com.wang.dao.impl.BookDAOImpl;
import com.wang.service.BookService;

import java.util.List;

/**
 * 图书操作实现
 */
public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO = new BookDAOImpl();

    @Override
    public void addBook(Book book) {
        if(bookDAO.isExists(book.getBookName())){
            addBookNum(book);
        }
        bookDAO.insertBook(book);
    }

    @Override
    public void addBookNum(Book book) {
        bookDAO.addAmountByBookNum(book);
    }

    @Override
    public void dropBookById(int bookId) {
        bookDAO.deleteBookById(bookId);
    }

    @Override
    public void changeBook(Book newBook) {
        bookDAO.updateBook(newBook);
    }

    @Override
    public Book getBookById(int bookId) {
        return bookDAO.searchBookById(bookId);
    }

    @Override
    public Object[][] getAllBookToArray() {
        return bookDAO.searchAllBookToArray();
    }

    @Override
    public List<Book> getBookListByBookName(String bookName) {
        return bookDAO.fuzzySearchBookByBookName(bookName);
    }

    @Override
    public List<Book> getBookListByAuthor(String author) {
        return bookDAO.fuzzySearchBookByAuthor(author);
    }

    @Override
    public List<Book> getBookListByTypeName(String typeName) {
        return bookDAO.searchBookByTypeName(typeName);
    }

    @Override
    public void lendBook(String bookName) {
        bookDAO.delAmount(bookName);
    }

    @Override
    public void returnBook(String bookName) {
        bookDAO.addAmount(bookName);
    }

    @Override
    public boolean isExistsBook(String bookName) {
        return bookDAO.isExists(bookName);
    }
}
