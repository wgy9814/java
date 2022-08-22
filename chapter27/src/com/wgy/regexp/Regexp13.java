package chapter27.src.com.wgy.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/22 22:07
 */
public class Regexp13 {
    public static void main(String[] args) {
        String content ="我....我要....学学学学....编程java! ";
        //1．去掉所有的.
        Pattern pattern = Pattern.compile("\\.");
        Matcher matcher = pattern.matcher(content);
        content = matcher.replaceAll("");

        System.out.println("content=" +content);

        //2．去掉重复的字我我要学学学学编程java !
        //思路
        //(1)使用（.)\\1+
        //(2)使用反向引用$1 来替换匹配到的内容
        //注意:因为正则表达式变化，所以需要重置matcher
//        pattern = Pattern.compile("(.)\\1+");//分组的捕获内容记录到$1
//
//        matcher = pattern.matcher(content);
//        while (matcher.find()) {
//            System.out.println("找到=" +matcher. group(0));
//        }
//
//        //使用反向引用$1 来替换匹配到的内容
//        content = matcher.replaceAll("$1");
//        System.out.println( "content=" +content);


        //3．使用一条语句 去掉重复的字我我要学学学学编程java!
        content = Pattern.compile("(.)\\1+").matcher(content). replaceAll("$1");
        System.out.println("content=" +content);



    }

}
