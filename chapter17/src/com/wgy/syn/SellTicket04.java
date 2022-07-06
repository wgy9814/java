package chapter17.src.com.wgy.syn;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/5 21:52
 */
//实现接口的同步
//第一种，在方法上添加synchronized修饰，默认锁在this对象
class SellTicket04 implements Runnable {
    private  int ticketNum = 100;//让多个线程共享 ticketNum
    private  boolean bool = true;
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


//实现接口的同步
//第二种，在方法上内部设置同步代码块添加修饰，锁可以在this对象，也可以是其他对象，但是必须是同一个对象，若不是同一个对象，那么不同线程操作的对象就不同了，就不存在同步问题了。
class SellTicket04 implements Runnable {
    private  int ticketNum = 100;//让多个线程共享 ticketNum
    private  boolean bool = true;
    public void sell(){
        synchronized(this){//此时锁在this对象
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

    }
    @Override
    public void run() {
        while (bool) {
            sell();
        }
    }
}

//       代码示例 - 同步代码块（非静态）
class SellTicket04 implements Runnable {
    private  int ticketNum = 100;//让多个线程共享 ticketNum
    private  boolean bool = true;
    Object object = new Object();
    public void sell(){
        synchronized(object){//此时锁在object对象
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

    }
    @Override
    public void run() {
        while (bool) {
            sell();
        }
    }
}

//       代码示例 - 同步方法（静态）
//继承类的同步
//第一种，静态方法直接写synchronized修饰，默认所在该类
class SellTicket03 extends Thread {

    private static int ticketNum = 100;//让多个线程共享 ticketNum
    private static boolean bool = true;
    public static synchronized void sell(){
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



//实现接口的同步
//第一种，在静态方法上添加synchronized修饰，默认锁在该类
class SellTicket04 implements Runnable {
    private  int ticketNum = 100;//让多个线程共享 ticketNum
    private  boolean bool = true;
    public static synchronized void sell(){
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

//       代码示例 - 同步代码块（静态）
//继承类的同步
//第二种，静态方法内部写同步代码块，默认的锁在该类
class SellTicket03 extends Thread {

    private static int ticketNum = 100;//让多个线程共享 ticketNum
    private static boolean bool = true;
    public static  void sell(){
        synchronized(SellTicket03.class){
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

    }
    @Override
    public void  run() {
        while (bool) {

            sell();

        }
    }
}

//实现接口的同步
//第二种，在静态方法上内设置添加synchronized修饰的同步代码块，默认锁在该类
class SellTicket04 implements Runnable {
    private static int ticketNum = 100;//让多个线程共享 ticketNum
    private static boolean bool = true;
    public static void sell(){
        synchronized(SellTicket04.class){
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
    }
    @Override
    public void run() {
        while (bool) {
            sell();
        }
    }
}

//2  注意事项和细节
//        （1）同步方法如果没有使用static修饰：默认锁对象为this；
//
//        （2）如果方法使用static修饰，默认锁对象：当前类.class
//
//（3）实现的落地步骤：
//
//        ①  需要先分析上锁的代码；
//
//        ②  选择同步代码块【范围小，效率高，优选】或者同步方法；
//
//        ③  要求多个线程的锁对象为同一个即可。
