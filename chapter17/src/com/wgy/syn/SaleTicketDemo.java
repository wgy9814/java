package chapter17.src.com.wgy.syn;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/27 12:57
 * 公平锁: 十分公平: 可以先来后到,一定要排队
 */
public class SaleTicketDemo {
    public static void main(String[] args){
        Ticket ticket = new Ticket();

        new Thread(()->{for(int i = 0; i < 40; i++) ticket.sale();}, "a").start();
        new Thread(()->{for(int i = 0; i < 40; i++) ticket.sale();}, "b").start();
        new Thread(()->{for(int i = 0; i < 40; i++) ticket.sale();}, "c").start();


    }
}


class Ticket {
    private int ticketNum = 30;
    private Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (this.ticketNum > 0) {
                System.out.println(Thread.currentThread().getName() + "够得第" + ticketNum-- + "张票,剩余" + ticketNum + "张票");
            }
            //增加错误的发生几率
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}