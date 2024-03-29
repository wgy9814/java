package chapter23.src.com.wgy.reflection;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/2 22:05
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author 紫英
 * @version 1.0
 * @discription 反射入门案例
 */
public class Reflection01 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        //1。使用Properties 类，可以读写配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("chapter23\\src\\ref.properties"));
        String classfullpath = properties.get("classfullpath").toString();// " com.wgy.Cat"
        String methodName = properties.get( "method" ).toString();//""hi"
        System.out.println("classfullpath=" + classfullpath);

        System.out.println("method=" + methodName);
        //2．创建对象，传统的方法，行不通
        //new classfullpath();

        //3．使用反射机制解决
        //(1)加载类，返回Class类型的对象cls
        Class cls = Class.forName(classfullpath);
        //(2）通过cls 得到你加载的类 com.hspedu.Cat的对象实例
        Object o = cls.newInstance();
        System.out.println("o的运行类型=" + o.getClass());//运行类型
        //(3)通过 cls 得到你加载的类com. hspedu.cat的 methodName"hi"的方法对象
        // 即:在反射中，可以把方法视为对象（万物皆对象）
        Method method1 = cls.getMethod(methodName);
        //(4)通过method1调用方法:即通过方法对象来实现调用方法.
        System.out.println("==============");
        method1.invoke(o);//传统方法对象.方法()〕，反射机制方法.invoke(对象(



        //java.lang.reflect.Field:代表类的成员变量，Field对象表示某个类的成员变量
        // 得到name字段
        //getField不能得到私有的属性
        Field nameField = cls.getField( "age");//
        System.out.println(nameField.get(o));//传统写法对象.成员变量，反射︰成员变量对象. get(对象)

        //ljava. lang.reflect.Constructor:代表类的构造方法，Constructor对象表示构造器
        Constructor constructor = cls.getConstructor();//()中可以指定构造器参数类型，返回无参构造器
        System.out.println(constructor);//cat()

        Constructor constructor2 = cls.getConstructor(String.class);//这里老师传入的 String.class 就是
        System.out.println(constructor2);//Cat(String name)



    }
}