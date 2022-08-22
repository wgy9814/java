package chapter27.src.com.wgy.regexp;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/22 21:20
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dell
 * @Classname Regex09
 * @Description 判断URL的表达式
 * @Date 2021/5/23 14:42
 * @Created by dell
 */

public class Regexp09 {
    public static void main(String[] args) {
        String content = "hello111111 ok" ;
        // String regStr = "\ld+";// 默认是贪婪匹配
        String regStr = "\\d+?";//非贪婪匹配

        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()){
            System.out.println("找到:" +matcher. group(0));
        }

    }
}