package chapter26.src.com.wgy.mhl.dao;

import chapter26.src.com.wgy.mhl.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/19 23:31
 * 开发BasicDA0，是其他DA0的父类
 */
public class BasicDAO<T> {  // 泛型指定具体类型
    private QueryRunner qr = new QueryRunner();
    private JDBCUtilsByDruid JDBCUtilsDruid;

    // 开发通用的DML方法，针对任意表

    public int update (String sql, Object... parameters) {
        Connection connection = null;

        try {
            connection = JDBCUtilsDruid.getConnection();
            int update = qr.update(connection, sql, parameters);
            return update;
        } catch (SQLException e) {
            throw new RuntimeException(e); // 将编译异常->运行异常，方便使用者处理(既可以抛出，也可以打印)
        } finally {
            JDBCUtilsDruid.close(null, null, connection); // 关闭连接
        }
    }

    //返回多个对象(即查询的结果是多行), 针对任意表
    /**
     *
     * @param sql sql 语句，可以有 ?
     * @param clazz 传入一个类的Class对象 比如 Actor.class
     * @param parameters 传入 ? 的具体的值，可以是多个
     * @return 根据Actor.class 返回对应的 ArrayList 集合
     */
    public List<T> queryMulti(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsDruid.getConnection();
            // 返回某类型的对象List数组列表
            return qr.query(connection, sql, new BeanListHandler<T>(clazz), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e); // 将编译异常->运行异常，方便使用者处理(既可以抛出，也可以打印)
        } finally {
            JDBCUtilsDruid.close(null, null, connection); // 关闭连接
        }
    }

    // 查询单行结果的通用方法
    // 以Actor为例，查询结果肯定是返回一个Actor类型的对象
    public T querySingle(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsDruid.getConnection();
            return qr.query(connection, sql, new BeanHandler<T>(clazz), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e); // 将编译异常->运行异常，方便使用者处理(既可以抛出，也可以打印)
        } finally {
            JDBCUtilsDruid.close(null, null, connection); // 关闭连接
        }
    }

    // 查询单行单列的方法,即返回单值的方法
    // 这里的Object是单行单列的某元素类型(int、string ....)，如查询某行的姓名项，此时就是String类型，这里用Object类
    public Object queryScalar(String sql, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsDruid.getConnection();
            return qr.query(connection, sql, new ScalarHandler(), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e); // 将编译异常->运行异常，方便使用者处理(既可以抛出，也可以打印)
        } finally {
            JDBCUtilsDruid.close(null, null, connection); // 关闭连接
        }
    }
}
