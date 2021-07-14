package com.book.service.impl;

import com.book.bean.*;
import com.book.dao.BookDao;
import com.book.dao.OrderDao;
import com.book.dao.OrderItemDao;
import com.book.dao.impl.BookDaoImpl;
import com.book.dao.impl.OrderDaoImpl;
import com.book.dao.impl.OrderItemDaoImpl;
import com.book.service.OrderService;
import com.book.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author initial
 * @CreateTime 2021/7/12:15:51
 */
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = new OrderDaoImpl();
    private final OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private final BookDao bookDao = new BookDaoImpl();
    
    
    /**
     * 这是一个事务操作。 首先去order表中提交订单信息
     * 然后把每个orderItem保存起来。 最后修改图书的销量和库存
     *
     * @param cart   购物车对象
     * @param userId 用户Id
     * @return
     */
    @Override
    public String createOrder(Cart cart, Integer userId) throws SQLException {
        String orderId = null;
        
        Connection connection = JdbcUtils.getConnection();
        
        //1. 首先保存订单信息
        orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(userId, new Date(), cart.getTotalPrice(), 0, orderId);
        orderDao.saveOrder(connection, order);
        
        
        //获取购物车中每一个图书的信息。然后保存到订单项
        Map<Integer, CartItem> items = cart.getItems();
        Collection<CartItem> values = items.values();
        for (CartItem value : values) {
            OrderItem orderItem = new OrderItem(null, value.getName(), value.getCount(), value.getPrice(), value.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(connection, orderItem);
            
            //每添加一个图书，就修改该图书的销量与库存
            Book book = bookDao.queryBookById(connection, value.getId());
            book.setSales(book.getSales() + value.getCount());
            book.setStock(book.getStock() - value.getCount());
            bookDao.updateBook(connection, book);
        }
        
        return orderId;
    }
    
    
    @Override
    public List<Order> myOrders(Integer userId) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        return orderDao.queryMyOrders(connection, userId);
        
    }
    
    
    @Override
    public List<OrderItem> orderDetails(String orderId) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        return orderItemDao.queryOrderDetailByOrderId(connection, orderId);
        
    }
    
    
    @Override
    public List<Order> allOrders() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        return orderDao.queryAllOrders(connection);
    }
    
    
    @Override
    public int sendOrder(String orderId, Integer status) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        return orderDao.changeOrderStatus(connection, orderId, status);
    }
    
    
    @Override
    public int receiveOrder(String orderId, Integer status) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        return orderDao.changeOrderStatus(connection, orderId, status);
        
    }
}
