package chapter17.src.com.wgy.Thread;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/2 23:50
 */
public class Thread03 {
    public static void main(String[] args) {
        //请编写一个程序,创建两个线程,一个线程每隔1秒输出“hello,world”，输出10次，退出，
        // 一个线程每隔1秒输出hi ”，输出5次退出. Thread03.java

        T1 t1 = new T1();
        T2 t2 = new T2();
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        thread1.start();//启动第1个线程
        thread2.start();//启动第2个线程
        //...

    }
}

class T1 implements Runnable {

    int count = 0;

    @Override
    public void run() {
        while (true) {
            //每隔1秒输出 “hello,world”,输出10次
            System.out.println("hello,world " + (++count));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count == 60) {
                break;
            }
        }
    }
}

class T2 implements Runnable {

    int count = 0;

    @Override
    public void run() {
        //每隔1秒输出 “hi”,输出5次
        while (true) {
            System.out.println("hi " + (++count));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count == 50) {
                break;
            }
        }
    }
}
