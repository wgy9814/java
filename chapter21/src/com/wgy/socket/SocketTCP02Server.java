package chapter21.src.com.wgy.socket;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/19 21:28
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketTCP02Server {
    public static void main(String[] args) throws IOException {
//1. 在本机 的 9999 端口监听, 等待连接
// 细节: 要求在本机没有其它服务在监听 9999
// 细节：这个 ServerSocket 可以通过 accept() 返回多个 Socket[多个客户端连接服务器的并发]
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务端，在 9999 端口监听，等待连接..");

        //2. 当没有客户端连接 9999 端口时，程序会 阻塞, 等待连接
        // 如果有客户端连接，则会返回 Socket 对象，程序继续
        Socket socket = serverSocket.accept();
        System.out.println("服务端 socket =" + socket.getClass());

        //3. 通过 socket.getInputStream() 读取客户端写入到数据通道的数据, 显示
        InputStream inputStream = socket.getInputStream();
        //4. IO 读取
        byte[] buf = new byte[1024];
        int readLine = 0;
        while ((readLine = inputStream.read(buf)) != -1){
            System.out.println(new String(buf,0,readLine));//根据读取到的实际长度，显示内容
        }
        //5. 获取 socket 相关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello,Cilent".getBytes());
        //设置结束标记
        socket.shutdownInput();
        //6.关闭流和 socket
        inputStream.close();
        socket.close();
        serverSocket.close();//关闭
        outputStream.close();


    }
}

