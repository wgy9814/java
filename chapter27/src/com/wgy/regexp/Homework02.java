package chapter27.src.com.wgy.regexp;

/**
 * @author 某某某
 * @date 2022/8/23 9:36
 */

/**
 * @Classname Homework01
 * @Description 案例2
 * @Date 2021/5/23 16:08
 * @Created by dell
 * 验证是不是整数或小数
 */

public class Homework02 {
    public static void main(String[] args) {
        //要求验证是不是整数或者小数
        //提示:这个题要考虑正数和负数
        //比如:123 -345 34.89 -87.9 -0.01 0.45等
        /*
        老师的思路
            *1．先写出简单的正则表达式
             * 2．在逐步的完善[根据各种情况来完韵]
        */
        String content="-0.58";
        String regStr="^[+-]?([1-9]\\d*|0)(\\.\\d+)?$";

        if (content.matches(regStr)){
            System.out.println("匹配成功 是正数或者小数！！！");
        }else {
            System.out.println("匹配失败！！！");
        }
    }
}