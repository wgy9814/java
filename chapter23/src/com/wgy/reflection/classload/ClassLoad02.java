package chapter23.src.com.wgy.reflection.classload;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/6 15:28

*我们说明一个类加载的链接阶段-准备
 * */
public class ClassLoad02 {
    public static void main(String[] args) {
    }

}

class A {
    //属性-成员变量-字段
    //老韩分析类加载的链接阶段-准备属性是如何处理
    //1. n1是实例属性，不是静态变量，因此在准备阶段，是不会分配内存
    //2. n2是静牵变量，分配内存n2是默认初始化0,而不是20
    //3. n3是static final是常量，他和静态变量不一样，因为一旦赋值就不变n3 = 30
    public int n1 = 10;
    public static int n2 = 20;
    public static final int n3 = 30;
}