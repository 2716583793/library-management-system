package com.wang.dao;

import com.wang.utils.JdbcUtils_Druid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库通用操作
 */
public abstract class BaseDAO<T> {

    //实例化QueryRunner类的对象runner
    private final QueryRunner runner = new QueryRunner();

    //实例化Class类对象clazz
    private final Class<T> clazz;

    //获取当前BaseDAO的子类继承的父类中的泛型
    {
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        Type[] types = paramType.getActualTypeArguments(); //获取了父类的泛型参数
        clazz = (Class<T>) types[0];
    }

    /**
     * 增删改通用操作
     *
     * @param sql  待执行的sql语句
     * @param args 若干Object对象
     * @return >0成功 ==0失败
     */
    public int update(String sql, Object... args) {
        Connection conn = JdbcUtils_Druid.getConnection();
        int count = 0;
        try {
            count = runner.update(conn, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils_Druid.release(conn, null, null);
        }
        return count;
    }

    /**
     * 查询单条数据
     *
     * @param sql  待执行的sql语句
     * @param args 若干Object对象
     * @return 指定类的一个对象
     */
    public T getBean(String sql, Object... args) {
        Connection conn = JdbcUtils_Druid.getConnection();
        T t = null;
        try {
            t = runner.query(conn, sql, new BeanHandler<>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils_Druid.release(conn, null, null);
        }
        return t;
    }

    /**
     * 查询多条数据
     *
     * @param sql  待执行的sql语句
     * @param args 若干Object对象
     * @return 指定类对象的集合
     */
    public List<T> getBeanList(String sql, Object... args) {
        Connection conn = JdbcUtils_Druid.getConnection();
        List<T> list = null;
        try {
            list = runner.query(conn, sql, new BeanListHandler<>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils_Druid.release(conn, null, null);
        }
        return list;
    }

    /**
     * @param sql  待执行的sql语句
     * @param args 若干Object对象
     * @return 得到的每条数据用数组存储
     */
    public List<Object[]> getArrayList(String sql, Object... args) {
        Connection conn = JdbcUtils_Druid.getConnection();
        List<Object[]> list = null;
        try {
            list = runner.query(conn, sql, new ArrayListHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils_Druid.release(conn, null, null);
        }
        return list;
    }
}
