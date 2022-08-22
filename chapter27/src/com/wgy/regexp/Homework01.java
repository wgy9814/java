package chapter27.src.com.wgy.regexp;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/22 22:16
 */

/**
 * @Classname Homework01
 * @Description 案例1
 * @Date 2021/5/23 16:08
 * @Created by dell
 */

public class Homework01 {
    public static void main(String[] args) {
        //规定电子邮件规则为//只能有一个@
        //@前面是用户名,可以是a-z A-Z 0-9_-字符
        //@后面是域名,并且域名只能是英文字母，比如 sohu.com或者tsinghua.org.cn
        //写出对应的正则表达式,，验证输入的字符串是否为满足规则

        String content = "hsp@tsinghua.org.cn kkk" ;
        String regStr = "^[\\w-]+@([a-zA-Z]+\\.)+[a-zA-Z]+$";

        //老师说明
        //1. String 的 matches是整体匹配
        // 2．看看这个matches 底层

        if (content.matches(regStr)) {
            System.out.println("匹配成功");
        } else {
            System.out.println("匹配失败");
        }




    }
}
