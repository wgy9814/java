package chapter19.src.com.wgy.file.transformation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/16 13:30
 */
public class CodeQuestion {
    public static void main(String[] args) throws IOException {
        //读取e:\\a.txt 文件到程序
        //思路
        //1.  创建字符输入流 BufferedReader [处理流]
        //2. 使用 BufferedReader 对象读取a.txt
        //3. 默认情况下，读取文件是按照 utf-8 编码，若不是，可能出现乱码情况。
        String filePath = "e:\\a.txt";
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String s = br.readLine();
        System.out.println("读取到的内容: " + s);
        br.close();

        //InputStreamReader
        //OutputStreamWriter
    }
}