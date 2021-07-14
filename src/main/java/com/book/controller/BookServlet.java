package com.book.controller;

import com.book.bean.Book;
import com.book.bean.Page;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author initial
 * @CreateTime 2021/7/4:14:56
 */
public class BookServlet extends BaseServlet {
    private final BookService bookService = new BookServiceImpl();
    
    
    public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
       //1. 获取请求参数，也就是当前页码，还有每页显示的数量
        String pageNo = request.getParameter("pageNo");
        int pageNoInt = pageNo == null? 1 : Integer.parseInt(pageNo);
        String pageSize = request.getParameter("pageSize");
        int pageSizeInt = pageSize == null? Page.PAGE_SIZE : Integer.parseInt(pageSize);
        
        //2. 通过调用bookService.page()方法，查询到分页的信息。
        Page<Book> page = bookService.queryForPage(pageNoInt,pageSizeInt);
        page.setUrl("manager/bookServlet?action=page");
        
        //3. 设置域数据
        request.setAttribute("page",page);
        
        //4. 请求转发，回显分页查询的数据
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    
    }
    
    
    
    public void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Map<String, String[]> map = request.getParameterMap();
        Book book = WebUtils.copyParamToBean(map, new Book());
        bookService.addBook(book);
        
        //然后重新跳回到图书列表页面
        int pageNo = Integer.parseInt(request.getParameter("pageNo")) + 1;
        response.sendRedirect("/Book/manager/bookServlet?action=page&pageNo="+pageNo);
    
    }
    
    public void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Map<String, String[]> parameterMap = request.getParameterMap();
    
        Book book = WebUtils.copyParamToBean(parameterMap, new Book());
        
        bookService.updateBook(book);
        
        response.sendRedirect("/Book/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
        
        
    }
    
    
    
    
    public void deleteBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //1. 获取要删除的图书ID
        String id = request.getParameter("id");
        Integer integer = Integer.valueOf(id);
        
        //2. 调用bookService中的delete方法，删除图书
        bookService.deleteBookById(integer);
        
        //3. 请求重定向，回到图书管理页面
        response.sendRedirect("/Book/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
        
    }
    
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //去数据库查询图书列表
        List<Book> books = bookService.queryBooks();
        //设置域数据
        request.setAttribute("books",books);
        //请求转发到book_manager.jsp页面进行图书显示
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }
  
    
    private void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //1. 获取请求信息的图书ID
        String id = request.getParameter("id");
        Integer integer = Integer.valueOf(id);
    
        Book book = bookService.queryBookById(integer);
        
        //2. 然后请求转发到book_edit.jsp页面，回显图书信息
        request.setAttribute("book",book);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
    }
    
}
