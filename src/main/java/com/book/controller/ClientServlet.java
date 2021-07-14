package com.book.controller;



import com.book.bean.Book;
import com.book.bean.Page;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author initial
 * @CreateTime 2021/7/7:13:46
 */
public class ClientServlet extends BaseServlet {
   
    private final BookService bookService = new BookServiceImpl();
    
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //1. 获取请求参数，也就是当前页码，还有每页显示的数量
        String pageNo = request.getParameter("pageNo");
        int pageNoInt = pageNo == null? 1 : Integer.parseInt(pageNo);
        String pageSize = request.getParameter("pageSize");
        int pageSizeInt = pageSize == null? Page.PAGE_SIZE : Integer.parseInt(pageSize);
    
        //2. 通过调用bookService.page()方法，查询到分页的信息。
        Page<Book> page = bookService.queryForPage(pageNoInt,pageSizeInt);
    
        page.setUrl("client/bookServlet?action=page");
        
        //3. 设置域数据
        request.setAttribute("page",page);
    
        //4. 请求转发，回显分页查询的数据
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }
    
    
    /**
     * 根据价格区间分页查询图书信息
     * @param request 请求对象
     * @param response  相应对象
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //1. 获取请求参数，也就是当前页码，还有每页显示的数量
        String pageNo = request.getParameter("pageNo");
        int pageNoInt = pageNo == null? 1 : Integer.parseInt(pageNo);
        String pageSize = request.getParameter("pageSize");
        int pageSizeInt = pageSize == null? Page.PAGE_SIZE : Integer.parseInt(pageSize);
        
        //获取价格区间
        String minStr = request.getParameter("min");
        int min = minStr == null? 0: Integer.parseInt(minStr);
        String maxStr = request.getParameter("max");
        int max = maxStr == null ? Integer.MAX_VALUE : Integer.parseInt(maxStr);
        
        //防止区间错误导致sql语句出错。
        if (min > max){
            int temp = min;
            min = max;
            max = temp;
        }
        
        //2. 通过调用bookService.pageByPrice()方法，查询到分页的信息。
        Page<Book> page = bookService.queryForPageByPrice(pageNoInt,pageSizeInt,min,max);
        
        page.setUrl("client/bookServlet?action=pageByPrice&min="+min+"&max="+max);
        
        //3. 设置域数据
        request.setAttribute("page",page);
        
        //4. 请求转发，回显分页查询的数据
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }
    
}
