package chapter17.src.com.wgy.Thread;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/2 16:37
 */
public class Thread01 {
    public static void main(String[] args) throws InterruptedException {
        //创建Cat对象,可以当作线程使用
        Cat cat = new Cat();
        cat.start();//启动线程->

        //1.当我们调用cat.start();时，系统会进入public synchronized void start() {} 这个方法
//        public synchronized void start() {
//            group.add(this);
//            boolean started = false;
//            try {
//                start0();
//                started = true;
//            } finally {
//                try {
//                    if (!started) {
//                        group.threadStartFailed(this);
//                    }
//                } catch (Throwable ignore) {
//                /* do nothing. If start0 threw a Throwable then
//                  it will be passed up the call stack */
//                }
//            }
//        }
//        2.接着public synchronized void start() {} 这个方法会调用其中的核心方法start0();
//
//        start0()是一个本地方法，由JVM调用，底层由c/c++实现
//
//        真正实现多线程效果的是start0()方法,而不是run()方法
//
//        3.最后再在start0()方法中调用run()方法
//        private native void start0();
//        @Override
//        public void run() {
//            if (target != null) {
//                target.run();
//            }
//        }

        //说明：当main线程启动一个子线程Thread-0,主线程不会阻塞，会继续执行后面代码
        System.out.println("主线程继续执行 "+Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            System.out.println("主线程 i="+i);
            //让主线程休眠1秒
            Thread.sleep(1000);
        }
    }
}

class Cat extends Thread{//当一个类继承了Thread类，该类就可以当作线程使用
    @Override
    public void run() {//重写run方法，实现自己的业务逻辑
        int times=0;
        while(true){
            //该线程每隔1秒，在控制台输出"猫咪学习java中"
            System.out.println("猫咪学习Java中"+"线程名"+(++times)+Thread.currentThread().getName());
            try {
                //使该线程休眠1秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (times==8){
                break;//当times到8次时，线程退出
            }
        }
    }
}