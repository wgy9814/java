package chapter17.src.com.wgy.Thread;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/2 22:15
 */
public class Thread02 {
    public static void main(String[] args) {
//        Dog dog=new Dog();
//        //dog.start();这里不能使用start
//        //创建Thread对象，把dog对象（实现Runnable接口）,放入Thread
//        Thread thread=new Thread(dog);
//        thread.start();
        Tiger tiger = new Tiger();
        ThreadProxy threadProxy = new ThreadProxy(tiger);
        threadProxy.start();
    }
}
class Animal {
}

class Tiger extends Animal implements Runnable{

    @Override
    public void run() {
        System.out.println("老虎嗷嗷叫....");
    }
}

//线程代理类 , 模拟了一个极简的Thread类
class ThreadProxy implements Runnable {//你可以把Proxy类当做 ThreadProxy

    private Runnable target = null;//属性，类型是 Runnable

    @Override
    public void run() {
        if (target != null) {
            target.run();//动态绑定（运行类型Tiger）
        }
    }

    public ThreadProxy(Runnable target) {
        this.target = target;
    }

    public void start() {
        start0();//这个方法时真正实现多线程方法
    }

    public void start0() {
        run();
    }
}


class Dog implements Runnable{//通过实现Runnable接口，开启线程
    int count=0;

    @Override
    public void run() {
        while (true){
            System.out.println("hi"+(++count)+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count==10){
                break;
            }
        }
    }
}