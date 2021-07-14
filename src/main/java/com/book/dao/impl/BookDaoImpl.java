package com.book.dao.impl;

import com.book.bean.Book;
import com.book.dao.BaseDao;
import com.book.dao.BookDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 * @author initial
 * @CreateTime 2021/7/4:13:53
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    
    
    @Override
    public int addBook(Connection connection,Book book) throws SQLException {
        String sql = "insert into book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
        return update(connection,sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg_path());
    }
    
    
    
    
    @Override
    public int deleteBookById(Connection connection,Integer id) throws SQLException {
        String sql = "delete from book where id = ?";
        return update(connection,sql,id);
    }
    
    @Override
    public int updateBook(Connection connection,Book book) throws SQLException {
        String sql = "update book set `name` = ?,`author` = ?,`price` = ?,`sales` = ?,`stock` =? ,`img_path` = ? where id = ? ";
        return update(connection,sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg_path(),book.getId());
    }
    
    @Override
    public Book queryBookById(Connection connection,Integer id) throws SQLException {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`from book where id = ?";
        return getBean(connection,sql,id);
    }
    
    @Override
    public List<Book> queryBooks(Connection connection) throws SQLException {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`from book ";
        return getBeanList(connection,sql);
    }
    
    @Override
    public List<Book> queryForItems(Connection connection,int begin, int pageSizeInt) throws SQLException {
        
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`from book limit "+begin+","+pageSizeInt;
        return getBeanList(connection,sql);
        
    }
    
    @Override
    public Long queryForPageTotalCount(Connection connection) throws SQLException {
        String sql = "select count(*) from book";
        return getValue(connection, sql);
    }
    
    
    @Override
    public Number queryForPageTotalCountByPrice(Connection connection, int min, int max) throws SQLException {
        String sql = "select count(*) from book where price between ? and ?";
        return getValue(connection, sql,min,max);
    }
    
    @Override
    public List<Book> queryForItemsByPrice(Connection connection, int begin, int pageSizeInt, int min, int max) throws SQLException {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`from book " +
                "where price between ? and ? order by price limit "+begin+","+pageSizeInt;
        return getBeanList(connection,sql,min,max);
    }
}
