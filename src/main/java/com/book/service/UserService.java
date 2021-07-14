package com.book.service;

import com.book.bean.User;

import java.sql.SQLException;

/**
 * @author initial
 * @CreateTime 2021/6/30:22:06
 */
public interface UserService {
    
    
    /**
     * 注册用户的方法
     * @param user 需要注册的用户
     */
    void registUser(User user) throws SQLException;
    
    
    /**
     * 用户登录的方法
     * @param user  用户输入的登录信息
     * @return  返回null则证明登陆失败。
     */
    User login(User user) throws SQLException;
    
    /**
     * 判断用户名是否重复
     * @param username  用户想要注册的用户名
     * @return 返回false则证明没有重复。
     */
    boolean isExits(String username) throws SQLException;
    
}
