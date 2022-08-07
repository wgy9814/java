package chapter23.src.com.wgy.reflection.classload;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/6 15:33
 * 演示类加载-初始化阶段
 */
public class ClassLoad03 {
    public static void main(String[] args) {

        //老韩分析
        //1．加载B类，并生成B的class对象
        //2．链接 num = 0
        //3．初始化阶段
        //依次自动收集类中的所有静态变量的赋值动作和静态代码块中的语句
        /*
        clinit(){
            System.out.println("B 静态代码块被执行");
            //num = 300;
            num = 100;
        }
        合并:num = 100
        */
        new B();//类加载
        System.out.println(B.num);//100，如果直接使用类的静态属性，也会导致类的加载

        //看看加载类的时候，是有同步机制控制
        /*
        protected Class<?> loadClass(String name，boolean resolve)
         ClassNotFoundException
        {
        //正因为有这个机制，才能保证某个类在内存中，只有一份ClaFs对象synchronized (getclassLoadingLock(name)) {
        l l. ...
        }
        }*/

        B b = new B();



    }

}

class B {
    static {
        System.out.println("B静态代码块被执行");
        num = 300;
    }

    static int num = 100;

    public B() {
        System.out.println("B(构造器被执行");
    }
}
