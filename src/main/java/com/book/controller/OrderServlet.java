package com.book.controller;



import com.book.bean.Cart;
import com.book.bean.Order;
import com.book.bean.OrderItem;
import com.book.service.OrderService;
import com.book.service.impl.OrderServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


/**
 * @author initial
 * @CreateTime 2021/7/12:16:11
 */
public class OrderServlet extends BaseServlet {
    private final OrderService orderService = new OrderServiceImpl();
    
    /**
     * 创建订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //1. 获取购物车对象
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        
        //2. 获取用户id
        Integer userId = (Integer) request.getSession().getAttribute("userId");
    
        if (userId == null){
            //如果没有用户id，证明用户还没有登陆，跳转回登录界面.
            response.sendRedirect("/Book/pages/user/login.jsp");
            return;
        }
        
        //创建订单
        String orderId = orderService.createOrder(cart, userId);
    
        //然后清空购物车
        cart.clear();
        
        //重定向到订单创建成功页面，并显示订单号
        request.getSession().setAttribute("orderId",orderId);
        response.sendRedirect("/Book/pages/cart/checkout.jsp");
        
        
    }
    
    /**
     * 查看我的订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    
        //1. 获取用户id
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        List<Order> orders = orderService.myOrders(userId);
        
        request.setAttribute("orders",orders);
        
        request.getRequestDispatcher("/pages/order/order.jsp").forward(request,response);
    
    }
    
    /**
     * 查看我的订单详情
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void orderDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    
        String orderId = request.getParameter("orderId");
        List<OrderItem> orderItems = orderService.orderDetails(orderId);
        
        request.getSession().setAttribute("orderItems",orderItems);
        response.sendRedirect("/Book/pages/cart/orderDetail.jsp");
        
    
    }
    
    /**
     * 确认收货
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void receiveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        
        String orderId = request.getParameter("orderId");
        orderService.receiveOrder(orderId,2);
        
        response.sendRedirect(request.getHeader("Referer"));
    }
    
    /**
     * 确认发货
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        
        String orderId = request.getParameter("orderId");
        orderService.sendOrder(orderId,1);
        response.sendRedirect(request.getHeader("Referer"));
    }
    
    
    /**
     * 查看所有订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void allOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Order> allOrders = orderService.allOrders();
        request.setAttribute("allOrders",allOrders);
        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request,response);
    }
}
