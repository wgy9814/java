package com.wgy.try_;

import java.util.Scanner;
//课后练习题: TryCatchExercise04.java
//如果用户输入的不是一个整数，就提示他反复输入，直到输入一个整数为止

public class TryCatchExercise04 {
    public static void main(String[] args) {
        //创建Scanner对象
        //使用无限循环，去接收一个输入
        //将该输入的值，转成一个Int
        //如果在转换时，抛出异常，则说明输入的内容不是一个可以转成Int的内容
        //如果没有抛出异常， 则break该循环

        Scanner sc = new Scanner(System.in);
        int num = 0;
        while(true){
            System.out.println("请输入一个整数");
            try {
                num = Integer.parseInt(sc.next());
                break;//没有异常，退出

            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println("你输入的不是一个整数");
            }//转换成为Int(有可能异常，输入的是hello)
        }

        System.out.println("你输入的值是：" + num);
        sc.close();
    }
}
