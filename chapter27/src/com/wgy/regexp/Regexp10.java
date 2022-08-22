package chapter27.src.com.wgy.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/22 21:43
 */
public class Regexp10 {
    public static void main(String[] args) {
        String content = "13588889999";
        // 汉字
        //String regStr = "^[\u0391-\uffe5]+$";
        // 邮政编码
        // 要求：是 1-9 开头的一个六位数. 比如：123890
        //  String regStr = "^[1-9]\\d{5}$";
        // QQ 号码
        // 要求: 是 1-9 开头的一个(5 位数-10 位数) 比如: 12389 , 1345687 , 187698765
        //String regStr = "^[1-9]\\d{4,9}$";
        // 手机号码
        // 要求: 必须以 13,14,15,18 开头的 11 位数 , 比如 13588889999
        String regStr = "^1[3|4|5|8]\\d{9}$";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        if(matcher.find()) {
            System.out.println("满足格式");
        } else {
            System.out.println("不满足格式");
        }
    }
}
