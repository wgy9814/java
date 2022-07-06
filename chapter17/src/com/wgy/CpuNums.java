package chapter17.src.com.wgy;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/2 16:32
 */
public class CpuNums {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        //获取当前电脑cpu的数量（核心数）
        int nums = runtime.availableProcessors();
        System.out.println(nums );
    }
}