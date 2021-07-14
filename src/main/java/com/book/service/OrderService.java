package com.book.service;

import com.book.bean.Cart;
import com.book.bean.Order;
import com.book.bean.OrderItem;

import java.sql.SQLException;
import java.util.List;

/**
 * @author initial
 * @CreateTime 2021/7/12:15:46
 */
public interface OrderService {
    
    /**
     * 创建订单，返回订单编号
     * @param cart 购物车对象
     * @param userId  用户Id
     * @return 订单编号
     */
    String  createOrder(Cart cart,Integer userId) throws SQLException;
    
    /**
     * 查询我的所有订单
     * @param userId 用户id
     * @return  返回用户所有订单
     */
    List<Order> myOrders(Integer userId) throws SQLException;
    
    
    /**
     * 返回订单详细信息
     * @param orderId  订单号
     * @return 返回该订单的所有明细
     */
    List<OrderItem> orderDetails(String orderId) throws SQLException;
    
    
    /**
     * 查看所有订单
     * @return
     */
    List<Order> allOrders() throws SQLException;
    
    /**
     * 发货，修改订单状态
     * @param orderId  订单号
     * @param status 订单状态
     * @return
     */
    int sendOrder(String orderId, Integer status) throws SQLException;
    
    /**
     * 签收货物  修改订单状态
     * @param orderId 订单号
     * @param status 订单状态
     * @return
     */
    int receiveOrder(String orderId, Integer status) throws SQLException;
    
}
