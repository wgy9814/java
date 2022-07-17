package chapter19.src.com.wgy.file.homework;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/16 15:10
 */
@SuppressWarnings("all")
public class Homework01 {
    public static void main(String[] args) throws IOException {
        String dirPath = "E:\\mytemp";
        File file = new File(dirPath);
        if (file.exists()) {
            System.out.println("目录已存在");
        } else {
            if (file.mkdir()) {
                System.out.println("目录创建成功！");
            } else {
                System.out.println("目录创建失败！");

            }
            System.out.println("目录不存在可以创建");
        }
        File file2 = new File(dirPath, "hello.txt");
        if (!file2.exists()) {
            file2.createNewFile();
            System.out.println("hello.txt创建成功");

        } else {
            System.out.println("文件已存在");
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2));
        bufferedWriter.write("hello,world!");
        bufferedWriter.close();//一定记得关！！！！！
        System.out.println("写入成功");

    }
}