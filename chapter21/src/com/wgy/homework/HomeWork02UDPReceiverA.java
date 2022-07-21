package chapter21.src.com.wgy.homework;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/21 21:26
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @ClassName HomeWork02UDPReceiverA
 * @Author ：BLWY-1124
 * @Date ：2022/4/26 14:52
 * @Description： UDP接收端A
 * @Version: 1.0
 */
public class HomeWork02UDPReceiverA {
    public static void main(String[] args) throws IOException {
        //1. 创建一个 DatagramSocket 对象，准备在 8888 接收数据
        DatagramSocket socket = new DatagramSocket(8888);
        //2. 构建一个 DatagramPacket 对象，准备接收数据
        // 在前面讲解 UDP 协议时，老师说过一个数据包最大64k
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf,buf.length);
        System.out.println("A端准备接收数据..");
        socket.receive(packet);//准备接收数据
        int length = packet.getLength();
        byte[] data = packet.getData();
        String s = new String(data,0,length);
        System.out.println(s);
        //回复
        String answer = "";
        if (s.equals("四大名著是哪些")){
            answer = "四大名著是：《红楼梦》，《西游记》，《三国演义》，《水浒传》";
        }else {
            answer = "what???";
        }
        data = answer.getBytes();
        packet = new DatagramPacket(data, 0, data.length,
                InetAddress.getByName("192.168.3.3"), 7777);
        socket.send(packet);//发送数据
        //关闭资源
        socket.close();

    }
}
