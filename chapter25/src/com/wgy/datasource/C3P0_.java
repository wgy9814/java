package chapter25.src.com.wgy.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/14 22:08
 */
public class C3P0_ {
    //方式1： 相关参数，在程序中指定user, url , password等
    @Test
    public void testC3PO_01 () throws Exception{
        // 1. 创建一个数据源对象Z
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        // 2. 通过配置文件 mysql.properties 获取相关连接的信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("chapter25\\src\\mysql.properties"));
        // 读取配置文件中的相关属性值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        // 3. 给数据连接池 comboPooledDataSource 设置相关参数
        // 因为数据库的连接是由 comboPooledDataSource 来进行管理的
        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);

        // 4. 设置连接数
        // 设置初始化连接数
        comboPooledDataSource.setInitialPoolSize(10);
        // 设置连接池最大连接数
        // 当请求连接数据池的连接数大于50时，就需要进入等队列进行等待
        comboPooledDataSource.setMaxPoolSize(50);

        //测试连接池的效率，测试对mysql 5000次操作
        long start = System.currentTimeMillis();
        for(int i =0; i < 5000;i++) {
            Connection connection = comboPooledDataSource.getConnection();//这个方法就是从DataSource接口实现
            //ystem.out.println("连接OK");
            connection.close();
        }
        long end = System.currentTimeMillis();
        //c3p05000连接mysql耗时=391
        System.out.println("c3p0 5000连接mysql耗时=" +(end - start));






    }

    //第二种方式使用配置文件模板来完成
    //1将c3p0提供的 c3p0.config.xml 拷贝到src目录下
    //2该文件指定了连接数据库和连接池的相关参数

    @Test
    public void testC3PO_02 () throws Exception{
        // 1. 绑定配置文件中的数据源 configName
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("c3p0_test");

        //测试5000次连接mysql
        long start = System.currentTimeMillis();
        System.out.println("开始执行....");
        for (int i = 0; i < 5000;i++) {
            Connection connection = comboPooledDataSource.getConnection();
            //System.out.println("连接OK~");
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("c3p0的第二种方式耗时=" +(end - start));



    }

}
