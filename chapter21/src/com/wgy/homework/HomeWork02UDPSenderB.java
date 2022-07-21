package chapter21.src.com.wgy.homework;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/21 21:26
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * @ClassName HomeWork02UDPSenderB
 * @Author ：BLWY-1124
 * @Date ：2022/4/26 14:53
 * @Description： UDP发送端B
 * @Version: 1.0
 */
public class HomeWork02UDPSenderB {
    public static void main(String[] args) throws IOException {
        //1. 创建一个 DatagramSocket 对象，准备在 7777 接收数据
        DatagramSocket socket = new DatagramSocket(7777);
        //2. 将需要发送的数据，封装到 DatagramPacket 对象
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入问题:");
        String question = scanner.next();
        byte[] data = question.getBytes();
        DatagramPacket packet = new DatagramPacket(data, 0, data.length,
                InetAddress.getByName("192.168.3.3"), 8888);
        //发送数据
        socket.send(packet);

        //接收答案
        byte[] buf = new byte[1024];
        packet = new DatagramPacket(buf,buf.length);
        System.out.println("B端准备接收数据..");
        socket.receive(packet);//准备接收数据
        int length = packet.getLength();
        data = packet.getData();
        String s = new String(data,0,length);
        System.out.println(s);
        //关闭资源
        socket.close();
    }
}
