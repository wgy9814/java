package chapter21.src.com.wgy.homework;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/21 21:50
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName HomeWork03Server
 * @Author ：BLWY-1124
 * @Date ：2022/4/27 10:28
 * @Description： 文件上传服务端
 * @Version: 1.0
 */
public class HomeWork03Server {
    public static void main(String[] args) throws Exception {
        //1. 服务端在本机监听6666端口
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务端在本机监听6666端口...");
        //2. 等待连接
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        //3.读取 客户端发送要下载的文件名
        //  这里老师使用了while读取文件名，时考虑将来客户端发送的数据较大的情况
       /* 字节转字符流
       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();*/
        //字节流
        byte[] b = new byte[1024];
        int len = 0;
        String downLoadFileName= "";
        while ((len = inputStream.read()) != -1){
            downLoadFileName = new String(b,0,len);
        }
        System.out.println("客户端希望下载文件名=" + downLoadFileName);
        //如果客户下载的是 高山流水 我们就返回该文件，否则一律返回 无名.mp3
        String filePath = "";
        if (downLoadFileName.equals("高山流水")){
            filePath = "g:\\java\\高山流水.mp3";
        }else {
            filePath = "g:\\java\\无名.mp3";
        }
        // 4.创建一个输入流，读取文件
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
        //5.使用工具类StreamUtils ，读取文件到一个字节数组
        //bytes 就是filePath对应的字节数组
        byte[] bytes = StreamUtils.streamToByteArray(bis);
        //6.通过socket获取到输出流, 将bytes数据发送给服务端
        OutputStream outputStream = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(outputStream); //获得服务器端输出流
        //7. 写入到数据通道，返回给客户端
        bos.write(bytes);//将文件对应的字节数组的内容，写入到数据通道
        System.out.println("发送成功!");
        //8.关闭资源
        bis.close();
        socket.shutdownOutput();//设置写入数据的结束标记
        inputStream.close();
        socket.close();
    }
}
