package chapter21.src.com.wgy.socket;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/18 22:14
 */

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @ClassName SocketTCP01Client
 * @Author ：BLWY-1124
 * @Date ：2022/4/25 9:51
 * @Description： 客户端
 * 发送"hello,server" 给服务器
 * @Version: 1.0
 */

public class SocketTCP01Client {
    public static void main(String[] args) throws IOException {
        //思路
        // 1. 连接服务端 (ip , 端口）
        // 解读: 连接本机的 9999 端口, 如果连接成功，返回 Socket 对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端 socket 返回=" + socket.getClass());
        //2. 连接上后，生成 Socket, 通过 socket.getOutputStream()
        // 得到 和 socket 对象关联的输出流对象
        OutputStream outputStream = socket.getOutputStream();
        //3. 通过输出流，写入数据到 数据通道
        outputStream.write("hello,server".getBytes());
        //4. 关闭流对象和 socket, 必须关闭
        outputStream.close();
        socket.close();
        System.out.println("客户端退出");
    }

}
