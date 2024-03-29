package chapter17.src.com.wgy.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/28 12:51
 */
public class ArrayBlockingQueues {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    /**
     * 抛出异常
     */

    public static void test1() {
        //队列的大小
        ArrayBlockingQueue queue = new ArrayBlockingQueue<>(3);
        System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));
        //java.lang.IllegalStateException: Queue full
        //System.out.println(queue.add("d"));
        System.out.println("----------------------");
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        //java.util.NoSuchElementException
        System.out.println(queue.remove());
        //抛出异常

    }

    /**
     * 有返回值没有异常
     */
    public static void test2() {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(3);

        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
//       System.out.println(queue.offer("d"));//offer 不抛出异常
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
//        System.out.println(queue.poll());

        //null 不抛出异常
    }
    /**
     *等待阻塞*/
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(3);
        queue.put ( "a" );
        queue.put("b" );
        queue.put ( "c" ) ;
//queue.put( "c" ) ;队列没有位置就会阻塞
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
    }


}
