package com.wang.dao;

import com.wang.bean.Book;

import java.util.List;

/**
 * 图书操作接口
 */
public interface BookDAO {
    /**
     * 插入一个Book对象
     *
     * @param book 待插入的Book对象
     */
    void insertBook(Book book);

    /**
     * 插入已存在Book对象时增加数量
     *
     * @param book 待增加的Book对象
     */
    void addAmountByBookNum(Book book);

    /**
     * 查询所有图书信息存放在二维数组中
     *
     * @return 存放所有图书信息的二维数组
     */
    Object[][] searchAllBookToArray();

    /**
     * 删除一个Book对象
     *
     * @param bookId 待删除Book对象的id
     */
    void deleteBookById(int bookId);

    /**
     * 查询指定id的Book对象
     *
     * @param bookId 指定的id
     * @return 存在则返回对象，不存在则返回null
     */
    Book searchBookById(int bookId);

    /**
     * 更改Book对象的信息
     *
     * @param newBook 传入新对象替换原对象
     */
    void updateBook(Book newBook);

    /**
     * 将根据图书名模糊查询到的图书存放在List集合中
     *
     * @param bookName 输入的查找信息
     * @return 存放所有符合条件的Book对象的List集合
     */
    List<Book> fuzzySearchBookByBookName(String bookName);

    /**
     * 将根据作者名模糊查询到的图书存放在List集合中
     *
     * @param author 输入的查找信息
     * @return 存放所有符合条件的Book对象的List集合
     */
    List<Book> fuzzySearchBookByAuthor(String author);

    /**
     * 将根据类别名查询到的图书存放在List集合中
     *
     * @param typeName 输入的查找信息
     * @return 存放所有符合条件的Book对象的List集合
     */
    List<Book> searchBookByTypeName(String typeName);

    /**
     * 减少指定图书数量
     *
     * @param bookName 指定图书名
     */
    void delAmount(String bookName);

    /**
     * 增加指定图书数量
     *
     * @param bookName 指定图书名
     */
    void addAmount(String bookName);

    /**
     * 是否存在与参数同名对象
     *
     * @param bookName 图书名
     * @return 是否存在同名
     */
    boolean isExists(String bookName);
}
