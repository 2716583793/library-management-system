package com.wang.service;

import com.wang.bean.Book;

import java.util.List;

/**
 * 图书操作接口
 */
public interface BookService {
    /**
     * 添加图书事务
     *
     * @param book 待添加图书信息
     */
    void addBook(Book book);

    /**
     * 添加已存在图书事务
     *
     * @param book 待添加图书信息
     */
    void addBookNum(Book book);

    /**
     * 删除图书事务
     *
     * @param bookId 待删除图书id
     */
    void dropBookById(int bookId);

    /**
     * 更改图书信息事务
     *
     * @param newBook 传入新的图书信息替换原信息
     */
    void changeBook(Book newBook);

    /**
     * 得到指定id图书信息
     *
     * @param bookId 指定id
     * @return 指定id图书信息
     */
    Book getBookById(int bookId);

    /**
     * 查询所有图书信息存放在二维数组中
     *
     * @return 存放所有图书信息的二维数组
     */
    Object[][] getAllBookToArray();

    /**
     * 将根据图书名模糊查询到的图书存放在List集合中
     *
     * @param bookName 输入的查找信息
     * @return 存放所有符合条件的Book对象的List集合
     */
    List<Book> getBookListByBookName(String bookName);

    /**
     * 将根据作者名模糊查询到的图书存放在List集合中
     *
     * @param author 输入的查找信息
     * @return 存放所有符合条件的Book对象的List集合
     */
    List<Book> getBookListByAuthor(String author);

    /**
     * 将根据类别名查询到的图书存放在List集合中
     *
     * @param typeName 输入的查找信息
     * @return 存放所有符合条件的Book对象的List集合
     */
    List<Book> getBookListByTypeName(String typeName);

    /**
     * 借书事务
     *
     * @param bookName 待借阅图书名称
     */
    void lendBook(String bookName);

    /**
     * 还书事务
     *
     * @param bookName 待归还图书名称
     */
    void returnBook(String bookName);

    /**
     * 是否存在与参数同名图书
     *
     * @param bookName 图书名
     * @return 是否存在同名图书
     */
    boolean isExistsBook(String bookName);
}
