package com.wgy.set_;

import java.util.HashSet;

public class HashSetIncrement {
    public static void main(String[] args) {
        /*
        HashSet底层是HashNap，第一次添加时，table数组扩容到16，
        临界值(threshold)是16*加载因子(LoadFactor)是0.75 =12
        如果table数组使用到了临界值12,就会扩容到16 * 2 = 32,
        新的临界值就是 32*0.75 = 24，依次类推
         */
        HashSet hashSet = new HashSet();
//        for(int i = 1; i <=100; i++) {
//            hashSet.add(i);//1,2,3,4,5...100
//        }

        /*
        在Java8中，如果一条链表的元素个数到达 TREEIFY_THRESHOLD(默认是8 )，
        并且table的大小 >= MIN_TREEIFY_CAPACITY(默认64),就会进行树化(红黑树)，
        否则仍然采用数组扩容机制
        */

//        for(int i = 1; i <= 12; i++) {
//            hashSet.add(new A(i));//
//        }
//        System.out.println( "hashset=" + hashSet);
        /*
        当我们向hashset增加一个元素，->Node ->加入table ，就算是增加了一个
        */
        for(int i = 1; i <= 7; i++) { //在table的某一条链表上添加了7个A对象
            hashSet.add(new A(i));
        }
        for(int i = 1; i <= 7; i++) {//在table的另一条链表上添加了7个B对象
            hashSet.add(new B(i));
        }




    }
}


class B {
    private int n;
    public B(int n) {
        this.n = n;
    }
    @Override
    public int hashCode() {
        return 200;
    }
}



class A {
    private int n;
    public A(int n) {
        this.n = n;
    }
    @Override
    public int hashCode() {
        return 100;
    }
}

