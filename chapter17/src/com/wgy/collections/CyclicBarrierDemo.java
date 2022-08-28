package chapter17.src.com.wgy.collections;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/27 23:54
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        /**
         * 集齐7个龙珠召唤神龙
         */
        // 召唤龙珠的线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{
            System.out.println("召唤神龙成功! ");
        });
        for (int i = 0; i < 7; i++) {
            int temp = i;
            //lambda 能拿到i吗
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "收集" + temp + "个龙珠");


                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

