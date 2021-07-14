package com.book.dao.impl;

import com.book.bean.User;
import com.book.dao.BaseDao;
import com.book.dao.UserDao;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * @author initial
 * @CreateTime 2021/6/30:21:19
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {
    
    
    /**
     * 通过用户名查询用户。
     * @param connection 数据库连接
     * @param username 用户注册时，输入的用户名
     * @return
     */
    @Override
    public User queryUserByUsername(Connection connection, String username) throws SQLException {
        String sql = "select `id`,`username`,`password` ,`email` from user where username = ?";
        return getBean(connection,sql,username);
    }
    
    
    /**
     * 验证用户名密码是否正确
     * @param connection 数据库连接
     * @param username 用户登录输入的用户名
     * @param password 用户登录时输入的密码
     * @return
     */
    @Override
    public User queryUserByUsernameAndPassword(Connection connection, String username, String password) throws SQLException {
        String sql = "select `id`,`username`,`password` ,`email` from user where username = ? AND password = ?";
        return getBean(connection,sql,username,password);
    }
    
    
    /**
     * 保存用户信息
     * @param connection 数据库连接
     * @param user 需要保存的新注册的用户
     * @return
     */
    @Override
    public int saveUser(Connection connection, User user) throws SQLException {
        String sql = "insert into user(username,password,email) values(?,?,?)";
        return update(connection,sql,user.getUsername(),user.getPassword(),user.getEmail());
        
        
    }
}
