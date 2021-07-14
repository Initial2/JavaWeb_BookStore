package com.book.dao;

import com.book.bean.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author initial
 * @CreateTime 2021/6/30:21:15
 */
public interface UserDao {
    
    /**
     * 根据username去数据库查询，看username是否重复。
     * @param connection 数据库连接
     * @param username 用户注册时，输入的用户名
     * @return 如果返回值为null，则证明不重复，该账号可以使用。 反之则不可使用。
     */
    public User queryUserByUsername(Connection connection, String username) throws SQLException;
    
    
    
    /**
     * 根据用户名与用户密码查询用户信息
     * @param connection 数据库连接
     * @param username 用户登录输入的用户名
     * @param password 用户登录时输入的密码
     * @return 如果返回值为null，则证明用户名或者密码错误，  反之正确。
     */
    public User queryUserByUsernameAndPassword(Connection connection,String username,String password) throws SQLException;
    
    
    
    /**
     * 保存此用户信息到数据库中
     * @param connection 数据库连接
     * @param user 需要保存的新注册的用户
     * @return 保存失败则返回-1
     */
    public int saveUser(Connection connection,User user) throws SQLException;
    
}
