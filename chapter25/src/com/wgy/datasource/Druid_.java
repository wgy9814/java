package chapter25.src.com.wgy.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/16 21:53
 * 测试druid的使用
 */
public class Druid_ {
    @Test
    public void testDruid() throws Exception {
        //1. 加入 Druid jar包
        //2. 加入 配置文件 druid.properties , 将该文件拷贝项目的src目录
        //3. 创建Properties对象, 读取配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("chapter25\\src\\druid.properties"));

        //4. 创建一个指定参数的数据库连接池, Druid连接池
        DataSource dataSource =
                DruidDataSourceFactory.createDataSource(properties);

        //5. 获得连接
        long start = System.currentTimeMillis();
        for (int i = 0; i<500000; i++){
            Connection connection = dataSource.getConnection();
            //System.out.println("连接成功!");
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("druid连接池操作5000耗时=" +(end - start));

    }

}
