package chapter21.src.com.wgy.udp;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/21 21:09
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @ClassName UDPSenderB
 * @Author ：BLWY-1124
 * @Date ：2022/4/26 9:06
 * @Description： UDP发送端B
 * @Version: 1.0
 */
@SuppressWarnings({"all"})
public class UDPSenderB {
    public static void main(String[] args) throws IOException {
        //1.创建 DatagramSocket 对象，准备在 9998 端口 接收数据
        DatagramSocket socket = new DatagramSocket(9998);
        //2. 将需要发送的数据，封装到 DatagramPacket 对象
        byte[] data = "hello,明天吃火锅".getBytes();
        //说明: 封装的 DatagramPacket 对象 data 内容字节数组 , data.length , 主机(IP) , 端口
        DatagramPacket packet = new DatagramPacket(data, 0, data.length,
                InetAddress.getByName("192.168.3.3"), 9999);
        socket.send(packet);

        //3.=== 接收从 A 端回复的信息
        //(1). 构建一个 DatagramPacket 对象，准备接收数据
        // 在前面讲解 UDP 协议时，老师说过一个数据包最大64k
        byte[] buf = new byte[1024];
        packet = new DatagramPacket(buf, buf.length);
        //(2). 调用 接收方法, 将通过网络传输的 DatagramPacket 对象
        // 填充到 packet 对象
        // 老师提示: 当有数据包发送到 本机的 9998 端口时，就会接收到数据
        // 如果没有数据包发送到 本机的 9998 端口, 就会阻塞等待.
        System.out.println("接收端A等待接收数据..");
        socket.receive(packet);
        //(3).可以把packet 进行拆包，并取出数据，并显示。
        int length = packet.getLength();//实际接收到的数据字节长度
        data = packet.getData();//接收到数据
        String s = new String(data,0,length);
        System.out.println(s);
    }
}

