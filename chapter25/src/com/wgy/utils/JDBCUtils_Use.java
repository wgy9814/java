package chapter25.src.com.wgy.utils;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/14 15:01
 */
public class JDBCUtils_Use {

    @Test
    public void testSelect() {
        //1. 得到连接
        Connection connection = null;
        //2. 组织一个sql
        String sql = "select * from actor where id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        //3. 创建PreparedStatement 对象
        try {
            connection = JDBCUtils.getConnection();
            System.out.println(connection.getClass()); //com.mysql.jdbc.JDBC4Connection
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 5);//给?号赋值
            //执行, 得到结果集
            set = preparedStatement.executeQuery();
            //遍历该结果集
            while (set.next()) {
                int id = set.getInt("id");
                String name = set.getString("name");
                String sex = set.getString("sex");
                Date borndate = set.getDate("borndate");
                String phone = set.getString("phone");
                System.out.println(id + "\t" + name + "\t" + sex + "\t" + borndate + "\t" + phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.close(set, preparedStatement, connection);
        }
    }

    @Test
    public void testDML() {//insert , update, delete

        //1. 得到连接
        Connection connection = null;
        //2. 组织一个sql
        String sql = "update actor set name = ? where id = ?";
        // 测试 delete 和 insert ,自己玩.
        PreparedStatement preparedStatement = null;
        //3. 创建PreparedStatement 对象
        try {
            connection = JDBCUtils.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            //给占位符赋值
            preparedStatement.setString(1, "周星驰");
            preparedStatement.setInt(2, 4);
            //执行
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.close(null, preparedStatement, connection);
        }
    }


    @Test
    public void testInsert() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        // 1. 插入语句：向subject表插入数据
        String sqlInsert01 = "INSERT INTO subject(subjectname) VALUES (?),(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert01);
        preparedStatement.setString(1, "编译原理");
        preparedStatement.setString(2, "计算机图形学");
        System.out.println(preparedStatement.executeUpdate() > 0 ? "插入成功！": "插入失败！");

        // 2. 释放资源
        JDBCUtils.close(null, preparedStatement, connection);
    }

    @Test
    public void testDelete() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        // 1. 删除语句：向subject表删除数据
        String sqlDelete01 = "DELETE FROM subject WHERE subjectno BETWEEN 19 AND 22";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete01);
        System.out.println(preparedStatement.executeUpdate() > 0 ? "删除成功！": "删除失败！");

        // 2. 释放资源
        JDBCUtils.close(null, preparedStatement, connection);
    }

    @Test
    public void testUpdate() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        // 1. 修改语句：向subject表修改数据
        String sqlUpdate01 = "UPDATE subject set subjectname=\"计算机组成原理\" WHERE subjectname=\"计算机图形学\"";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate01);
        System.out.println(preparedStatement.executeUpdate() > 0 ? "修改成功！": "修改失败！");

        // 2. 释放资源
        JDBCUtils.close(null, preparedStatement, connection);
    }

//    @Test
//    public void testSelect() throws Exception {
//        Connection connection = JDBCUtils.getConnection();
//        // 1. 查询语句：向subject表查询数据
//        String sqlSelect01 = "SELECT * FROM student";
//        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect01);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        while (resultSet.next()) {
//            System.out.println(resultSet.getString("studentname") + " 住在 " +
//                    resultSet.getString("address"));
//        }
//
//        // 2. 释放资源
//        JDBCUtils.close(null, preparedStatement, connection);
//    }

}
