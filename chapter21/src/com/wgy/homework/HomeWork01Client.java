package chapter21.src.com.wgy.homework;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/21 21:19
 */

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @ClassName SocketTCP01Client
 * @Author ：BLWY-1124
 * @Date ：2022/4/25 9:51
 * @Description： 客户端
 * 发送"name" 给服务器
 * @Version: 1.0
 */
public class HomeWork01Client {
    public static void main(String[] args) throws IOException {
        //思路
        // 1. 连接服务端 (ip , 端口）
        // 解读: 连接本机的 9999 端口, 如果连接成功，返回 Socket 对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        //2. 连接上后，生成 Socket, 通过 socket.getOutputStream()
        // 得到 和 socket 对象关联的输出流对象
        OutputStream outputStream = socket.getOutputStream();
        //3. 通过输出流，写入数据到 数据通道
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你的问题：");
        String question = scanner.next();
        bufferedWriter.write(question);
        bufferedWriter.newLine(); //插入一个换行符，表示写入的内容结束, 注意，要求对方使用 readLine()!!!!
        bufferedWriter.flush(); // 如果使用的字符流，需要手动刷新，否则数据不会写入数据通道
        //设置结束标记
        socket.shutdownOutput();
        //4.获取和 socket 关联的输入流. 读取数据(字符)，并显示
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s); //输出

        //5. 关闭流对象和 socket, 必须关闭
        bufferedReader.close();  // 关闭外层
        bufferedWriter.close();
        socket.close();
        System.out.println("客户端退出");
    }
}
