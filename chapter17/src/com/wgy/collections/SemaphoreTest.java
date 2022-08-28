package chapter17.src.com.wgy.collections;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/27 23:55
 * Semaphore(信号量)，是JUC包下的一个工具类，我们可以通过其限制执行的线程数量，达到限流的效果。
 * 当一个线程执行时先通过其方法进行获取许可操作，获取到许可的线程继续执行业务逻辑，
 * 当线程执行完成后进行释放许可操作，未获取达到许可的线程进行等待或者直接结束。
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        //semaphore.acquire(); //获取信号量,假设如果已经满了,等待信号量可用时被唤醒
        //
        //semaphore.release(); //释放信号量
        //
        //作用: 多个共享资源互斥的使用!并发限流,控制最大的线程数

        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            int temp = i;
            new Thread(()->{
                try {
                    semaphore.acquire(); //获取
                    System.out.println(temp + "号车抢到车位");
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(); //释放
                    System.out.println(temp + "号车离开车位");
                }
            }).start();
        }
    }
}

