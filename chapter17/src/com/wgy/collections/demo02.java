package chapter17.src.com.wgy.collections;

import java.util.concurrent.CountDownLatch;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/27 23:50
 */
//计数器
public class demo02 {
    public static void main(String[] args) throws InterruptedException {
        //相当于计数器
        CountDownLatch countDownLatch = new CountDownLatch(5);
        //计数器总数是5,当减少为0,任务才继续向下执行
        for (int i = 1; i <6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"==>start");
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("main线程继续向下执行");
    }
}

