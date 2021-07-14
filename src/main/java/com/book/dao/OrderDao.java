package com.book.dao;

import com.book.bean.Cart;
import com.book.bean.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author initial
 * @CreateTime 2021/7/12:14:31
 */
public interface OrderDao {
    
    
    /**
     * 保存订单
     * @param order 订单
     * @param connection 数据库连接
     * @return
     */
    int saveOrder(Connection connection, Order order) throws SQLException;
    
    /**
     * 查询我的订单
     * @param connection 数据库连接
     * @param userId 用户ID
     * @return 根据用户ID查询用户所有订单
     */
    List<Order> queryMyOrders(Connection connection,Integer userId) throws SQLException;
    
    /**
     * 管理员功能，查询所有订单
     * @param connection 数据库连接
     * @return 返回所有订单信息
     */
    List<Order> queryAllOrders(Connection connection) throws SQLException;
    
    /**
     * 修改订单状态，
     * 未发货  发货 已签收
     * @param connection 数据库连接
     * @param orderId  订单号
     * @param status 订单状态
     * @return
     */
    int changeOrderStatus(Connection connection,String  orderId,Integer status) throws SQLException;
    
    
    
}
