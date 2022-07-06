package com.wgy.homework.chapter15;

import org.junit.Test;

import java.util.List;

public class Homework01 {
    public static void main(String[] args) {
        //    1.编程题Homework01.java 10min
//    定义个泛型类DAO<T>，在其中定义一个Map成员变量，Map的键为String类型，值为Ⅰ类型。
//    分别创建以下方法:
//            (1) public void save(String id,T entity):保存T类型的对象到Map成员变量中() public T get(String id): 从map中获取id对应的对象
//            (3) public void update(String id,T entity):替换map 中key为id的内容,改为entity对象(4) public List<T> list():返回map中存放的所有T对象
//            (5)public void delete(String id):删除指定id对象
//    定义一个User类:
//    该类包含:private成员变量(int类型)id,age;(String类型) name。
//    创建 DAO类的对象，分别调用其save、get、update、list、delete方法来操作User对象，使用Junit 单元测试类进行测试。
    }


    @Test
    public void testList() {
        //说明
        //这里我们给T 指定类型是User
        DAO<User> dao = new DAO<>();
        dao.save("001", new User(1,10,"jack"));
        dao.save("002", new User(2,18,"king"));
        dao.save("003", new User(3,38,"smith"));

        List<User> list = dao.list();

        System.out.println("list=" + list);

        dao.update("003", new User(3, 58, "milan"));
        dao.delete("001");//删除
        System.out.println("===修改后====");
        list = dao.list();
        System.out.println("list=" + list);

        System.out.println("id=003 " + dao.get("003"));//milan

    }


}
