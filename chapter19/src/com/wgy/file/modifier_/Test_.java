package chapter19.src.com.wgy.file.modifier_;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/13 22:32
 */
//Test_类
public class Test_ {
    public static void main(String[] args) {
    //（1）节点流是底层流/低级流，直接跟数据源相接；
    //
    //（2）处理流包装节点流，既可以消除不同节点流的实现差异，也可以提供更方便的方法来实现输入输出；
    //
    //（3）处理流（包装流）对节点流进行包装，使用了 修饰器设计模式，不会直接于数据源相连。

        BufferedReader_ bufferedReader_ = new BufferedReader_(new FileReader_());
        bufferedReader_.readFiles(10);
        //bufferedReader_.readFile();
        //Serializable
        //Externalizable
        //ObjectInputStream
        //ObjectOutputStream
        //这次希望通过 BufferedReader_ 多次读取字符串
        BufferedReader_ bufferedReader_2 = new BufferedReader_(new StringReader_());
        bufferedReader_2.readStrings(5);
    }
}