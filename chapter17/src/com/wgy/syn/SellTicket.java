package chapter17.src.com.wgy.syn;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/3 12:50
 */
public class SellTicket{
    public static void main(String[] args) {


        System.out.println("===使用继承类方式来售票=====");
        SellTicket03 sellTicket1 = new SellTicket03();
        SellTicket03 sellTicket2 = new SellTicket03();
        SellTicket03 sellTicket3 = new SellTicket03();

        sellTicket1.start();//启动售票线程
        sellTicket2.start();//启动售票线程
        sellTicket3.start();//启动售票线程

//
//        System.out.println("===使用实现接口方式来售票=====");
//        SellTicket05 sellTicket02 = new SellTicket05();
//
//        new Thread(sellTicket02).start();//第1个线程-窗口
//        new Thread(sellTicket02).start();//第2个线程-窗口
//        new Thread(sellTicket02).start();//第3个线程-窗口


    }
}


//使用Thread方式

class SellTicket03 extends Thread {

    private static int ticketNum = 100;//让多个线程共享 ticketNum
    private static boolean bool = true;//控制run方法的变量
    public static synchronized void sell(){//同步方法，同一时刻，只能有一个线程执行该方法
        if (ticketNum <= 0) {
            System.out.println("售票结束...");
            bool = false;
            return;
        }

        //休眠50毫秒, 模拟
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票"
                + " 剩余票数=" + (--ticketNum));
    }
    @Override
    public void  run() {
        while (bool) {

            sell();

        }
    }
}

//实现接口方式

class SellTicket05 implements Runnable {
    private int ticketNum = 100;//让多个线程共享 ticketNum
    private boolean bool = true;
    public synchronized void sell(){
        if (ticketNum <= 0) {
            System.out.println("售票结束...");
            bool = false;
            return;
        }

        //休眠50毫秒, 模拟
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票"
                + " 剩余票数=" + (--ticketNum));//1 - 0 - -1
    }
    @Override
    public void run() {
        while (bool) {
            sell();
        }
    }
}
