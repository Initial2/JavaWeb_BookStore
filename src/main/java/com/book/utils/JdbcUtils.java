package com.book.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接工具类
 * @author initial
 * @CreateTime 2021/6/30:20:32
 */
public class JdbcUtils {
    private static DataSource dataSource;
    private static final ThreadLocal<Connection> THREAD_LOCAL = new ThreadLocal<>();
    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
  
    
    /**
     * 获取数据库连接
     * @return  返回一个数据库连接 该数据库连接是支持事务的
     * @throws SQLException 获取连接失败则抛出异常
     */
    public static Connection getConnection() throws SQLException {
        if (THREAD_LOCAL.get() == null ){
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            THREAD_LOCAL.set(connection);
        }
        return THREAD_LOCAL.get();
    }
    
    
    
    /**
     * 在关闭连接的同时，提交事务
     */
    public static void closeAndCommit(){
        Connection conn = THREAD_LOCAL.get();
        try {
            if (conn!=null){
                conn.commit();
                conn.setAutoCommit(true);
                DbUtils.closeQuietly(conn);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            THREAD_LOCAL.remove();
        }
      
    }
    
    /**
     * 在关闭连接的同时，回滚事务
     */
    public static void closeAndRollback(){
        Connection conn = THREAD_LOCAL.get();
        try {
            if (conn!=null) {
                conn.rollback();
                conn.setAutoCommit(true);
                DbUtils.closeQuietly(conn);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            THREAD_LOCAL.remove();
        }
       
    }
}
