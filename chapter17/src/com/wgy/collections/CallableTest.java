package chapter17.src.com.wgy.collections;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/27 23:42
 */
public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask,"a").start();
        System.out.println(futureTask.get());

    }
}

class MyThread implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        System.out.println("call()方法被调用了");
        return 1024;
    }
}


