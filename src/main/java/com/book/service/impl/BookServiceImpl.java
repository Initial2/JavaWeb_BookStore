package com.book.service.impl;

import com.book.bean.Book;
import com.book.bean.Page;
import com.book.dao.BookDao;
import com.book.dao.impl.BookDaoImpl;
import com.book.service.BookService;
import com.book.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author initial
 * @CreateTime 2021/7/4:14:27
 */
public class BookServiceImpl implements BookService {
    
    private final BookDao bookDao = new BookDaoImpl();
    
    
    @Override
    public int addBook(Book book) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        return bookDao.addBook(connection, book);
    }
    
    
    @Override
    public int deleteBookById(Integer id) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        return bookDao.deleteBookById(connection, id);
        
    }
    
    @Override
    public int updateBook(Book book) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        return bookDao.updateBook(connection, book);
        
    }
    
    @Override
    public Book queryBookById(Integer id) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        return bookDao.queryBookById(connection, id);
    }
    
    @Override
    public List<Book> queryBooks() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        return bookDao.queryBooks(connection);
    }
    
    
    @Override
    public Page<Book> queryForPage(int pageNoInt, int pageSizeInt) throws SQLException {
        Page<Book> page = new Page<>();
        
        
        Connection connection = JdbcUtils.getConnection();
        
        //设置page的总记录数
        int pageTotalCount = bookDao.queryForPageTotalCount(connection).intValue();
        page.setPageTotalCount(pageTotalCount);
        
        //设置page的总页码数
        Integer pageTotal = pageTotalCount % pageSizeInt == 0 ? pageTotalCount / pageSizeInt : pageTotalCount / pageSizeInt + 1;
        page.setPageTotal(pageTotal);
        
        //设置page对象的页码
        page.setPageNo(pageNoInt);
        
        //设置page对象的每页显示数量
        page.setPageSize(pageSizeInt);
        
        //设置page对的items
        int begin = ((page.getPageNo() - 1) * pageSizeInt);
        List<Book> items = bookDao.queryForItems(connection, begin, pageSizeInt);
        page.setItems(items);
        
        return page;
    }
    
    
    @Override
    public Page<Book> queryForPageByPrice(int pageNoInt, int pageSizeInt, int min, int max) throws SQLException {
        Page<Book> page = new Page<>();
        
        Connection connection = JdbcUtils.getConnection();
        
        //设置page的总记录数
        int pageTotalCount = bookDao.queryForPageTotalCountByPrice(connection, min, max).intValue();
        page.setPageTotalCount(pageTotalCount);
        
        //设置page的总页码数
        Integer pageTotal = pageTotalCount % pageSizeInt == 0 ? pageTotalCount / pageSizeInt : pageTotalCount / pageSizeInt + 1;
        page.setPageTotal(pageTotal);
        
        //设置page对象的页码
        page.setPageNo(pageNoInt);
        
        //设置page对象的每页显示数量
        page.setPageSize(pageSizeInt);
        
        //设置page对的items
        int begin = ((page.getPageNo() - 1) * pageSizeInt);
        List<Book> items = bookDao.queryForItemsByPrice(connection, begin, pageSizeInt, min, max);
        page.setItems(items);
        
        return page;
        
    }
}
