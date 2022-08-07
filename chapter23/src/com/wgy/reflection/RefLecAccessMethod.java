package chapter23.src.com.wgy.reflection;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/6 23:11
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *@author韩顺平*@version 1.0
 *演示通过反射调用方法*/
public class RefLecAccessMethod {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        //1。得到Boss类对应的Class对象
        Class<?> bossCls = Class.forName("chapter23.src.com.wgy.reflection.Boss");
        //2．创建对象
        Object o = bossCls.newInstance();
        //3．调用public的hi方法
        //Method hi = bossCls.getMethod("hi",String.class);//OK
        //3.1得到hi方法对象
        Method hi = bossCls.getDeclaredMethod("hi",String.class); //OK
        // 3.2 调用
        hi.invoke(o, "韩顺平教育~");


        //4。调用private static方法
        // 4.1 得到say方法对象
        Method say = bossCls.getDeclaredMethod("say", int.class, String.class, char.class);
        //4.2因为say方法是private，所以需要暴破，原理和前面讲的构造器和属性一样
        say.setAccessible(true);
        System.out.println(say.invoke(o,100,"张三",'男'));
        //4.3因为say方法是static的，还可以这样调用，可以传入null
        System.out.println(say.invoke(null,200, "李四", '女'));

        //5．在反射中，如果方法有返回值，统一返回0bject，但是他运行类型和方法定义的返回类型一致
        Object reVal = say .invoke(null,300,"王五",'男');
        System.out.println("reVal的运行类型=" +reVal.getClass());//string




    }
}

class Boss {//类
    public int age;
    private static String name;
    public Boss() {//构造器
    }
    private static String say(int n,String s, char c){//静态方法
        return n +""+ s +""+c;
    }
    public void hi(String s) {//普通public方法
        System.out.println("hi " + s);
    }
}
