package chapter17.src.com.wgy.collections;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/27 22:53
 */

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 同理可证
 */
public class SetTest {

    public static void main(String[] args) {

//        Set<String> set = new HashSet<>();
        //如何解决hashSet线程安全问题
        //1. Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}


