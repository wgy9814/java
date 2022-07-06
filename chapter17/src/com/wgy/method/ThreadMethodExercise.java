package chapter17.src.com.wgy.method;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/3 15:59
 */
public class ThreadMethodExercise {
    public static void main(String[] args) throws InterruptedException {
        TT tt = new TT();
        Thread thread = new Thread(tt);
        for (int i = 1; i <= 10; i++) {
            Thread.sleep(1000);
            System.out.println("Hi" + i);
            if (i == 5){
                thread.start();
                thread.join();
            }
        }
        System.out.println("主线程结束..");

    }
}
class TT implements Runnable{
    private int count;
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello" + (++count));

            if (count == 10){
                System.out.println("子线程结束..");
                break;
            }
        }
    }
}