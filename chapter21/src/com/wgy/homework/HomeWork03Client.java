package chapter21.src.com.wgy.homework;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/21 21:50
 */

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @ClassName HomeWork034Client
 * @Author ：BLWY-1124
 * @Date ：2022/4/27 10:28
 * @Description： 文件下载客户端
 * @Version: 1.0
 */
public class HomeWork03Client {
    public static void main(String[] args) throws Exception {
        //1.客户端连接服务端 6666，得到Socket对象
        Socket socket = new Socket(InetAddress.getLocalHost(),6666);
        //2. 连接上后，生成 Socket, 通过 socket.getOutputStream()
        // 得到 和 socket 对象关联的输出流对象
        OutputStream outputStream = socket.getOutputStream();
        //3. 通过输出流，写入数据到 数据通道
        //BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要下载的文件名");
        String fileName = scanner.next();
        outputStream.write(fileName.getBytes());
        //bufferedWriter.write(fileName);
        //bufferedWriter.flush(); // 如果使用的字符流，需要手动刷新，否则数据不会写入数据通道

        //设置结束标记
        socket.shutdownOutput();

        //接受服务器端发送的文件
        //4. 读取服务器发送的数据    通过Socket得到输入流
        InputStream inputStream = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        byte[] bytes = StreamUtils.streamToByteArray(bis);
        //5. 将得到 bytes 数组，写入到指定的路径，就得到一个文件了
        String destFilePath = "e:\\music.mp3";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFilePath));
        bos.write(bytes);
        System.out.println("写入成功~");
        //6.关闭资源
        bos.close();
        inputStream.close();
        outputStream.close();
        bis.close();
    }
}
