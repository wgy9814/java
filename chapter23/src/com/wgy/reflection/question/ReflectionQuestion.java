package chapter23.src.com.wgy.reflection.question;

import chapter23.src.com.wgy.Cat;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/1 22:05
 * 反射问题的引入*/
public class ReflectionQuestion {
    public static void main(String[] args) throws IOException {
        //根据配置文件 re.properties指定信息，创建Cat对象并调用方法hi
        //老韩回忆
        //传统的方式new对象 -》调用方法
        Cat cat = new Cat();
        cat.hi();

        //我们尝试做一做->明自反射
        //1。使用Properties 类，可以读写配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\re.properties"));
        String classfullpath = properties.get("classfullpath").toString();// " com.wgy.Cat"
        String method = properties.get( "method" ).toString();//""hi"
        System.out.println("classfullpath=" + classfullpath);

        System.out.println("method=" + method);
        //2．创建对象，传统的方法，行不通
        //new classfullpath);

    }
}

