package com.book.dao.impl;

import com.book.bean.Cart;
import com.book.bean.Order;
import com.book.dao.BaseDao;
import com.book.dao.OrderDao;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author initial
 * @CreateTime 2021/7/12:14:34
 */
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
    
    
    @Override
    public int saveOrder(Connection connection, Order order) throws SQLException {
        String sql = "insert into `Order`(`order_id`,`create_time`,`price`,`status`  , `user_id`) values(?,?,?,?,?)";
       return  update(connection, sql, order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
    
    @Override
    public List<Order> queryMyOrders(Connection connection,Integer userId) throws SQLException {
        String sql = "select `order_id` as orderId , `create_time`  as createTime , `price`, `status`  , `user_id`  as userId from `order` where user_id =  ?";
        return getBeanList(connection,sql,userId);
        
    }
    
    @Override
    public List<Order> queryAllOrders(Connection connection) throws SQLException {
        String sql = "select `order_id` as orderId , `create_time`  as createTime , `price`, `status`  , `user_id`  as userId from `order`";
        return getBeanList(connection,sql);
    }
    
    @Override
    public int changeOrderStatus(Connection connection,String orderId,Integer status) throws SQLException {
       String sql = "update `order` set status = ? where order_id = ?";
       return update(connection,sql,status,orderId);
    }
}
