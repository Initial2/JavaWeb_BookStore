package com.book.dao;

import com.book.bean.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author initial
 * @CreateTime 2021/7/4:13:48
 */
public interface BookDao {
    
    /**
     * 向book表中添加图书
     * @param book 要添加的图书bean
     * @param connection 数据库连接
     * @return 返回-1则添加失败
     */
    int addBook(Connection connection,Book book) throws SQLException;
    
    /**
     * 根据图书ID删除图书
     * @param id  要删除的图书ID
     * @param connection 数据库连接
     * @return 返回-1则删除失败
     */
    int deleteBookById(Connection connection,Integer id) throws SQLException;
    
    /**
     * 修改图书信息
     * @param book  传入新的图书bean
     * @param connection 数据库连接
     * @return 返回-1则修改失败
     */
    int updateBook(Connection connection,Book book) throws SQLException;
    
    /**
     * 查询单个图书的信息
     * @param id 要查询的图书ID
     *  @param connection 数据库连接
     * @return  返回该图书，如果返回值为null，则查询失败
     */
    Book queryBookById(Connection connection,Integer id) throws SQLException;
    
    /**
     * 查询book表中所有的图书
     *  @param connection 数据库连接
     * @return 以List的形式返回
     */
    List<Book> queryBooks(Connection connection) throws SQLException;
    
    /**
     * 查询分页Page对象中的items数据。
     * @param begin 指定分页的开始位置
     * @param pageSizeInt  分页的大小
     * @param connection 数据库连接
     * @return  返回查询到的items
     */
    List<Book> queryForItems(Connection connection,int begin, int pageSizeInt) throws SQLException;
    
    /**
     * 查询Book表中总共有多少条记录
     * @param connection 数据库连接
     * @return  返回图书个数
     */
    Long queryForPageTotalCount(Connection connection) throws SQLException;
    
    /**
     * 根据价格区间，查找满足条件的图书的数量
     * @param connection  数据库连接
     * @param min  最小价格
     * @param max  最大价格
     * @return
     */
    Number queryForPageTotalCountByPrice(Connection connection, int min, int max) throws SQLException;
    
    
    /**
     * 根据价格区间，分页查找满足条件的图书
     * @param connection  数据库连接
     * @param begin  分页页号
     * @param pageSizeInt  每页显示大小
     * @param min  最小价格
     * @param max  最大价格
     * @return
     */
    List<Book> queryForItemsByPrice(Connection connection, int begin, int pageSizeInt, int min, int max) throws SQLException;
}
