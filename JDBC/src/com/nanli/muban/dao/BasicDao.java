package com.nanli.muban.dao;

import com.nanli.muban.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author raoxin
 * @param <T> T为继承的javabean的类型
 * 0.QueryRunner是将result的结果填到到javabean中，javabean中的字段名字必须和数据库中还有就是，他会自动关闭prestatement和resultment
 * 1.int update 更新操作
 * 2.T getBean 获取一个javabean
 * 3.List<T> getBeanList返回javabeanList
 * 4.Object getValue获取单个值
 * 5.除了BsicDao，其它的Dao均为接口，然后在Imp里面实现
 */

public class BasicDao<T> {
    private QueryRunner qr = new QueryRunner();
    //开发通用的 dml 方法, 针对任意的表

    /**
     * 常用更新操作
     * @param sql
     * @param parameters
     * @return 返回受影响行数，一般来说受影响0行就是失败了
     */
    public int update(String sql, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            int rows = qr.update(connection, sql, parameters);
            return rows;
        } catch (SQLException e) {
            throw new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    /**
     * 返回一个javabean对象，就是数据库一行的结果
     * @param sql
     * @param clazz
     * @param parameters
     * @return 返回数据库一行的结果，封装到T中，一般是一个javabean对象
     */
    public T getBean(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection, sql, new BeanHandler<T>(clazz), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    /**
     *返回T对象的List集合
     * @param sql sql 语句，可以有 ?
     * @param clazz 传入一个类的 Class 对象 比如 Actor.class
     * @param parameters 传入 ? 的具体的值，可以是多个
     * @return 根据 Actor.class 返回对应的 ArrayList 集合
     */
    public List<T> getBeanList(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection, sql, new BeanListHandler<T>(clazz), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    /**
     * 得到某一个值
     * @param sql
     * @param parameters
     * @return 得到javabean中的某一个值，一般也是查询语句
     */
    public Object getValue(String sql, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection, sql, new ScalarHandler(), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

}
