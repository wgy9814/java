package chapter17.src.com.wgy.syn;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/27 15:32
 * Condition常见例子（生产者消费者模式（完成加一减一各一次操作））：
 */
public class PC {
    public static void main(String[] args) {
        a a = new a();
        new Thread(()->{
            for(int i = 0; i < 10; i++) {
                a.increment();
            }
        }, "A").start();
        new Thread(()->{
            for(int i = 0; i < 10; i++) {
                a.decrease();
            }
        }, "B").start();
    }

}

class a {
    public int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public void increment() {
        lock.lock();
        try {
            while(number != 0) {
                condition.await();
            }
            number ++;
            System.out.println(Thread.currentThread().getName()+">>"+number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public  void decrease(){
        lock.lock();
        try {
            while(number!=1){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+">>"+number);
            condition.signalAll();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}