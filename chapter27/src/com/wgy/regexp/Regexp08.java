package chapter27.src.com.wgy.regexp;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/22 21:20
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname Regex08
 * @Description 应用实例
 * @Date 2021/5/23 14:39
 * @Created by dell
 */

public class Regexp08 {
    public static void main(String[] args) {
        String content = "hello韩顺平教育 jack韩顺平老师 韩顺平同学hello";

        //找到 韩顺平教育、韩顺平老师、韩顺平同学子字符串
//        String regStr = "韩顺平教育|韩顺平老师|韩顺平同学";
        //上面的写法可以等价非捕获分组,注意:不能matcher.group(1)
//        String regStr = "韩顺平(?:教育|老师|同学)";

        //找到韩顺平这个关键字,但是要求只是查找韩顺平教育和韩顺平老师中  包含有的韩顺平

        //下面也是非捕获分组，不能使用 matcher.group(1)
//        String regStr = "韩顺平(?=教育|老师)";


        //找到韩顺平这个关键字,但是要求只是查找不是（韩顺平教育 和韩顺平老师)中包含有的韩顺平
        // 下面也是非捕获分组，不能使用matcher .group(1)
        String regStr = "韩顺平(?!教育I老师)";



        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            System.out.println("找到 " + matcher.group(0));
        }
    }
}