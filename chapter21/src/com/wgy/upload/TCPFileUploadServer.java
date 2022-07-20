package chapter21.src.com.wgy.upload;

/**
 * @ClassName TCPFileUploadServer
 * @Author ：BLWY-1124
 * @Date ：2022/4/25 15:57
 * @Description： 文件上传的服务端
 * @Version: 1.0
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPFileUploadServer {
    public static void main(String[] args) throws Exception {
        //1. 服务端在本机监听8888端口
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务端在8888端口监听....");
        //2. 等待连接
        Socket socket =serverSocket.accept();
        //3. 读取客户端发送的数据    通过Socket得到输入流
        InputStream inputStream = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        byte[] bytes = StreamUtils.streamToByteArray(bis);
        //4. 将得到 bytes 数组，写入到指定的路径，就得到一个文件了
        String destFilePath = "e:\\mg1.png";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFilePath));
        bos.write(bytes);
        bos.close();

        //向客户端回复 "收到图片"
        // 通过socket 获取到输出流(字符)
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write("收到图片");
        bw.flush();//把内容刷新到数据通道
        socket.shutdownOutput();//设置写入结束标记

        //关闭其他资源
        bw.newLine();
        socket.close();
        serverSocket.close();
        bis.close();

    }
}
