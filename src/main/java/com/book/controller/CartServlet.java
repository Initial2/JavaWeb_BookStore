package com.book.controller;
/**
 * @author initial
 * @CreateTime 2021/7/9:15:45
 */

import com.book.bean.Book;
import com.book.bean.Cart;
import com.book.bean.CartItem;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {
    
    private final BookService bookService = new BookServiceImpl();
    
    
    /**
     * 向购物车中添加商品的方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
       //1. 获取请求参数，商品编号
        String id = req.getParameter("bookID");
        
        int bookId = Integer.parseInt(id);
     
        //2. 然后从数据库找到这个编号对应的图书信息
        Book book = bookService.queryBookById(bookId);
        
        //首先判断有没有购物车，没有就建一个
        Cart cart = (Cart)req.getSession().getAttribute("cart");
       if (cart == null){
           cart = new Cart();
           req.getSession().setAttribute("cart",cart);
       }
        
        //3. 把该图书加入到购物车中
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        cart.addItem(cartItem);
        
        //记录一下最后添加到购物车的图书名,用于回显
        HashMap<String, Object> map = new HashMap<>();
        map.put("totalCount",cart.getTotalCount());
        map.put("lastName", cartItem.getName());
    
        Gson gson = new Gson();
        String mapJsonString = gson.toJson(map);
        resp.getWriter().write(mapJsonString);
        
    }
    
    /**
     * 删除购物车中的商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取要删除的图书ID
        String id = req.getParameter("id");
        int bookId = Integer.parseInt(id);
        
        // 获取当前用户的购物车
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        //删除指定的图书
        cart.deleteItem(bookId);
        
        //然后重定向回购物车列表
        resp.sendRedirect("/Book/pages/cart/cart.jsp");
        
    }
    
    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取Session对象,获取购物车
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        
        //2. 清空购物车
        cart.clear();
        
        //3. 重定向回购物车页面
        resp.sendRedirect("/Book/pages/cart/cart.jsp");
    
    }
    
    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求参数
        String bookIDStr = req.getParameter("id");
        String countStr = req.getParameter("count");
        
        int bookID = Integer.parseInt(bookIDStr);
        int count = Integer.parseInt(countStr);
        
        //2. 处理修改请求
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        
        cart.updateCount(bookID,count);
        
        //3. 重定向回购物车页面
        resp.sendRedirect(req.getHeader("Referer"));
        
    }
}
