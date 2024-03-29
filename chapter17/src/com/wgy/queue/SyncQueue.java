package chapter17.src.com.wgy.queue;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/28 14:12
 */

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列
 * 和其他的lockQueue 不一样， SynchronousQueue 不存储元素
 */
public class SyncQueue {

    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>(); //同步队列

        new Thread(()->{

            try {
                System.out.println(Thread.currentThread().getName() + "put 1");
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName() + "put 2");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName() + "put 3");
                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "=>" + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "=>" + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "=>" + synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        },"T2").start();
    }
}

