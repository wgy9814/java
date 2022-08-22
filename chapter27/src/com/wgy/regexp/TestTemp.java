package chapter27.src.com.wgy.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/22 21:57
 */
public class TestTemp {
    public static void main(String[]args) {
        String content = "hel?lo abc 11.1";
        //String regStr = ".";//匹配除了\n的所有字符
        //String regStr = "[.]";// 匹配.本身
        //String regStr = “\\. ";
        String regStr = "[?]";

        // 匹配除了\n的所有字符
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到" + matcher.group(0));
        }
    }

}
