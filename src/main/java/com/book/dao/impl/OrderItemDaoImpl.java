package com.book.dao.impl;

import com.book.bean.OrderItem;
import com.book.dao.BaseDao;
import com.book.dao.OrderItemDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author initial
 * @CreateTime 2021/7/12:15:36
 */
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {
    
    @Override
    public int saveOrderItem(Connection connection,OrderItem orderItem) throws SQLException {
        String sql = "insert into order_item (`name`,`count`,`price` ,`total_price` ,`order_id`  ) values(?,?,?,?,?)";
        return update(connection,sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
    
    @Override
    public List<OrderItem> queryOrderDetailByOrderId(Connection connection,String orderId) throws SQLException {
        String sql = "select `id`,`name`,`count`,`price` ,`total_price` as totalPrice ,`order_id` as orderId from order_item where order_id = ?";
        return getBeanList(connection,sql,orderId);
    }
}
