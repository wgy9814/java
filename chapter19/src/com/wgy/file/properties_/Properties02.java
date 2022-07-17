package chapter19.src.com.wgy.file.properties_;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/16 14:45
 */
public class Properties02 {
    public static void main(String[] args) throws IOException {
        //使用Properties 类来读取mysql.properties 文件

        //1. 创建Properties 对象
        Properties properties = new Properties();
        //2. 加载指定配置文件
        properties.load(new FileReader("chapter19\\src\\mysql.properties"));
        //3. 把k-v显示控制台
        properties.list(System.out);
        //4. 根据key 获取对应的值
        String user = properties.getProperty("user");
        String pwd = properties.getProperty("pwd");
        System.out.println("用户名=" + user);
        System.out.println("密码是=" + pwd);



    }
}