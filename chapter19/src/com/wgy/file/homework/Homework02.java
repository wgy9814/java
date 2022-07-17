package chapter19.src.com.wgy.file.homework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/16 15:14
 */
public class Homework02 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("e:\\a.txt"));
        String s = null;
        int i =0;
        while ((s=bufferedReader.readLine())!=null){
            System.out.println(++i +" "+ s);
        }
        bufferedReader.close();


    }
}