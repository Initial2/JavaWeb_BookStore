package com.book.dao;

import com.book.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * 操作数据库的BaseDao
 * @author initial
 * @CreateTime 2021/6/30:21:05
 */
public abstract class BaseDao<T> {
    
    private final QueryRunner queryRunner = new QueryRunner();
    private final Class<T> clazz;
    
    
    /**
     * 获取当前类的父类的泛型。 这样可以省去我们每次指定要操作的数据表的类型。
     */
    public BaseDao() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] arguments = parameterizedType.getActualTypeArguments();
        clazz = (Class<T>) arguments[0];
    }
    
    
    
    
    
    /**
     * 查询结果为一条数据
     * @param conn 数据库连接
     * @param sql  需要执行的SQL语句
     * @param args 占位符的填充
     * @return 返回一条查询记录.
     */
    public T getBean(Connection conn, String sql, Object... args) throws SQLException {
        return queryRunner.query(conn, sql, new BeanHandler<>(clazz), args);
    }
    
    
    /**
     * 查询结果为多条数据
     *
     * @param conn 数据库连接
     * @param sql  需要执行的SQL语句
     * @param args 占位符的填充
     * @return 返回多条查询记录.
     */
    public List<T> getBeanList(Connection conn, String sql, Object... args) throws SQLException {
        return queryRunner.query(conn, sql, new BeanListHandler<>(clazz), args);
    }
    
    
    /**
     * 一个通用的增删改操作
     *
     * @param conn 数据库连接
     * @param sql  需要执行的SQL语句.
     * @param args SQL语句中有几个占位符,就传进来几个参数.  长度必须保持一致.
     * @return 返回受影响的数据行， 如果执行失败则返回-1.
     */
    public int update(Connection conn, String sql, Object... args) throws SQLException {
        return  queryRunner.update(conn, sql, args);
    }
    
    
    
    /**
     * 查询特殊值，例如分组函数， 结果为一行一列
     * @param conn 数据库连接
     * @param sql  查询语句
     * @param args 占位符填充
     * @return 返回带有分组函数查询的结果。
     */
    public <E> E getValue(Connection conn, String sql, Object... args) throws SQLException {
        return queryRunner.query(conn, sql, new ScalarHandler<>(), args);
    }
    
}
