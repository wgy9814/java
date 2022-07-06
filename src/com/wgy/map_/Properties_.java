package com.wgy.map_;

import java.util.Properties;

public class Properties_ {
    public static void main(String[] args) {


//        总结-开发中如何选择集合实现类(记住)
//        在开发中，选择什么集合实现类，主要取决于业务操作特点，然后根据集合实现类特性进行选择，分析如下:
//        1)先判断存储的类型(一组对象[单列]或一组键值对[双列])
//        2)一组对象[单列]:Collection接口
    //        允许重复:List
        //        增删多:LinkedList [底层维护了一个双向链表]
        //        改查多: ArrayList [底层维护Object类型的可变数组]
    //        不允许重复:Set
    //          无序:HashSet [底层是HashMap，维护了一个哈希表即(数组+链表+红黑树)
        //      排序:TreeSet
    //          插入和取出顺序一致:LinkedHashSet,维护数组+双向链表
//        3)一组键值对[双列]:Map
    //        键无序:HashMap [底层是:哈希表 jdk7: 数组+链表，jdk8:数组+链表+红黑树]
    //        键排序:TreeMap
    //        键插入和取出顺序一致:LinkedHashMap
    //        读取文件 Properties



        //老韩解读
        //1. Properties 继承Hashtable
        //2．可以通过k-v存放数据，当然key和 value 不能为null//增加
        Properties properties = new Properties();
//        properties.put(null, "abc");//抛出空指针异常
//        properties.put("abc", null);//抛出空指针异常

        properties.put("john", 100); //k-v
        properties.put("lucy", 100);
        properties.put("lic", 100);
        properties.put("lic", 88);;//如果有相同的key , value被替换
        System.out.println("properties=" + properties);

        //通过k获取对应值
        System.out.println(properties.get("lic"));//88
        //删除
        properties.remove("lic");
        System.out.println( "properties=" +properties);

        //修改
        properties.put( "john","约翰");
        System.out.println( "properties=" + properties);



    }
}
