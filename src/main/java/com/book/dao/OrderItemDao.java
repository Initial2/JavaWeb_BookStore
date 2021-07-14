package com.book.dao;

import com.book.bean.Order;
import com.book.bean.OrderItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 订单项数据库操作
 * @author initial
 * @CreateTime 2021/7/12:15:33
 */
public interface OrderItemDao {
    
    /**
     * 保存订单项
     * @param orderItem 订单项
     * @param connection 数据库连接
     * @return
     */
    int saveOrderItem(Connection connection,OrderItem orderItem) throws SQLException;
    
    /**
     * 查询某个订单的详细信息
     * @param orderId  订单号
     * @param connection 数据库连接
     * @return  返回多个OrderItem
     */
    List<OrderItem> queryOrderDetailByOrderId(Connection connection,String orderId) throws SQLException;
    
}
