package com.book.service.impl;

import com.book.bean.User;
import com.book.dao.UserDao;
import com.book.dao.impl.UserDaoImpl;
import com.book.service.UserService;
import com.book.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * User表的业务实现
 *
 * @author initial
 * @CreateTime 2021/6/30:22:11
 */
public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();
    
    
    @Override
    public void registUser(User user) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        userDao.saveUser(connection, user);
        
    }
    
    @Override
    public User login(User user) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        return userDao.queryUserByUsernameAndPassword(connection, user.getUsername(), user.getPassword());
        
    }
    
    @Override
    public boolean isExits(String username) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        //如果结果为null，证明没有重复，所以取反返回false。
        return !(userDao.queryUserByUsername(connection, username) == null);
        
    }
}
