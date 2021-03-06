package chapter21.src.com.wgy.homework;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/21 21:20
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName SocketTCP01Server
 * @Author ：BLWY-1124
 * @Date ：2022/4/25 9:50
 * @Description： 服务端 使用字符流方式读写
 * @Version: 1.0
 */
public class HomeWork01Server {
    public static void main(String[] args) throws IOException {
//1. 在本机 的 9999 端口监听, 等待连接
// 细节: 要求在本机没有其它服务在监听 9999
// 细节：这个 ServerSocket 可以通过 accept() 返回多个 Socket[多个客户端连接服务器的并发]
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务端，在 9999 端口监听，等待连接..");

        //2. 当没有客户端连接 9999 端口时，程序会 阻塞, 等待连接
        // 如果有客户端连接，则会返回 Socket 对象，程序继续
        Socket socket = serverSocket.accept();

        //3. 通过 socket.getInputStream() 读取客户端写入到数据通道的数据, 显示
        InputStream inputStream = socket.getInputStream();
        //4. IO 读取 使用字符流, 老师使用 InputStreamReader 将 inputStream 转成字符流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s); //输出

        //5. 获取 socket 相关联的输出流

        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        String answer = "";
        if (s.equals("name")) {
            answer = "我是覃波";
        } else if (s.equals("hobby")) {
            answer = "我爱编程";
        } else {
            answer = "你说啥???";
        }
        bufferedWriter.write(answer);
        bufferedWriter.newLine();
//        插入一个换行符，表示写入的内容结束, 注意，要求对方使用 readLine()!!!!
        bufferedWriter.flush(); // 如果使用的字符流，需要手动刷新，否则数据不会写入数据通道
        // 相当于设置结束标记//socket.shutdownInput();
        //6.关闭流和 socket
        System.out.println("服务器退出");
        bufferedReader.close(); //关闭外层
        bufferedWriter.close();
        socket.close();
        serverSocket.close();//关闭
    }
}
