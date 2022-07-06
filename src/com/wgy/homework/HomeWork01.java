package com.wgy.homework;

public class HomeWork01 {
    public static void main(String[] args) {
        try {
            //验证输入的参数个数是否正确两个
            if (args.length != 2) {
                throw new ArrayIndexOutOfBoundsException("参数个数不对");
            }
            //把接收到的参数，转成整数[可能输入的是字符串]
            int n1 = Integer.parseInt(args[0]);
            int n2 = Integer.parseInt(args[1]);
            double result = cal(n1, n2);//该方法可送抛出除零异常
            System.out.println("计算结果为:" + result);
        } catch (ArrayIndexOutOfBoundsException e) {//参数个数是否为两个(下标越界异常)
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {//数据格式异常
            System.out.println("参数格式不正确，需要输入整数");
        } catch (ArithmeticException e) {
            System.out.println("出现了除零的异常");
        }
    }
    public static double cal(int n1,int n2){
        return n1 / n2;
    }
}


