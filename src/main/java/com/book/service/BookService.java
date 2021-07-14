package com.book.service;

import com.book.bean.Book;
import com.book.bean.Page;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author initial
 * @CreateTime 2021/7/4:14:26
 */
public interface BookService {
    
    
    /**
     * 向book表中添加图书
     * @param book 要添加的图书bean
     * @return 返回-1则添加失败
     */
    int addBook(Book book) throws SQLException;
    
    /**
     * 根据图书ID删除图书
     * @param id  要删除的图书ID
     * @return 返回-1则删除失败
     */
    int deleteBookById(Integer id) throws SQLException;
    
    /**
     * 修改图书信息
     * @param book  传入新的图书bean
     * @return 返回-1则修改失败
     */
    int updateBook(Book book) throws SQLException;
    
    /**
     * 查询单个图书的信息
     * @param id 要查询的图书ID
     * @return  返回该图书，如果返回值为null，则查询失败
     */
    Book queryBookById(Integer id) throws SQLException;
    
    /**
     * 查询book表中所有的图书
     * @return 以List的形式返回
     */
    List<Book> queryBooks() throws SQLException;
    
    /**
     * 查询分页对象
     * @param pageNoInt 当前页码
     * @param pageSizeInt  每页显示数量
     * @return  返回查询后的page对象。
     */
    Page<Book> queryForPage(int pageNoInt, int pageSizeInt) throws SQLException;
    
    
    /**
     * 根据价格区间查询分页图书信息
     * @param pageNoInt  请求的页码
     * @param pageSizeInt  每页显示大小
     * @param min   价格最小值
     * @param max  价格最大值
     * @return  返回分页查询结果
     */
    Page<Book> queryForPageByPrice(int pageNoInt, int pageSizeInt, int min, int max) throws SQLException;
}
