package chapter23.src.com.wgy;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/1 21:59
 */

public class Cat {
    private String name = "招财猫";
    public int age = 18;

    public Cat(){
        //无参构造器
    }
    public Cat(String name){
        this.name = name;
    }
    public void hi() {//常用方法
//        System.out.println("hi " + name);
    }

    public void cry(){
        System.out.println(name + "喵喵叫~");
    }
}
