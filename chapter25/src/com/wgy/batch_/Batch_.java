package chapter25.src.com.wgy.batch_;

import chapter25.src.com.wgy.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/14 16:25
 * 演示java的批处理
 */
public class Batch_ {
    @Test
    public void noBatch() throws Exception{ // 不使用批处理，一条SQL一条连接
        Connection connection = JDBCUtils.getConnection();
        String sql = "INSERT INTO admin2 VALUES (null, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        long begin = System.currentTimeMillis();
        for (int i = 0; i <  5000; i++) {
            preparedStatement.setString(1, "Jack" + i);
            preparedStatement.setString(2, "666");
            preparedStatement.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println("不使用批处理执行时间：" + (end - begin)); // 47342
        // 关闭连接
        JDBCUtils.close(null, preparedStatement, connection);
    }

    @Test
    //使用批量方式添加数据
    public void useBatch() throws Exception{ // 使用批处理，多条SQL才连接一次进行处理
        Connection connection = JDBCUtils.getConnection();
        String sql = "INSERT INTO admin2 VALUES (null, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        long begin = System.currentTimeMillis();
        for (int i = 0; i <  5000; i++) {
            preparedStatement.setString(1, "Jack" + i);
            preparedStatement.setString(2, "666");

            //将sql 语句加入到批处理包中 -> 看源码
            /*
            //1. //第一就创建 ArrayList - elementData => Object[]
            //2. elementData => Object[] 就会存放我们预处理的sql语句
            //3. 当elementData满后,就按照1.5扩容（因为底层是用ArrayList临时存放SQL语句）
            //4. 当添加到指定的值后，就executeBatch
            //5. 批量处理会减少我们发送sql语句的网络开销，而且减少编译次数，因此效率提高
            public void addBatch() throws SQLException {
                synchronized(this.checkClosed().getConnectionMutex()) {
                    if (this.batchedArgs == null) {
                        this.batchedArgs = new ArrayList();
                    }

                    for(int i = 0; i < this.parameterValues.length; ++i) {
                        this.checkAllParametersSet(this.parameterValues[i], this.parameterStreams[i], i);
                    }

                    this.batchedArgs.add(new PreparedStatement.BatchParams(this.parameterValues, this.parameterStreams, this.isStream, this.streamLengths, this.isNull));
                }
            }
             */

            preparedStatement.addBatch(); // 添加到批处理
            if ((i + 1) % 1000 == 0 ) {
                preparedStatement.executeBatch(); // 每一百个SQL，执行一次批处理
                preparedStatement.clearBatch(); // 清空
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("使用批处理执行时间：" + (end - begin)); // 328
        // 关闭连接
        JDBCUtils.close(null, preparedStatement, connection);
    }

}
