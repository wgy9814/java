package chapter21.src.com.wgy.api;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/18 22:13
 */

import java.net.InetAddress;
import java.net.UnknownHostException;


public class API {
    public static void main(String[] args) throws UnknownHostException {
        //1.获取本机 InetAddress 对象 getLocalHost
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost); //DESKTOP-KCMN6AE/192.168.125.3

        //2.根据指定主机名/域名获取 ip 地址对象 getByName
        InetAddress localHost1 = InetAddress.getByName("DESKTOP-KCMN6AE");
        System.out.println("localHost1:" + localHost1); //DESKTOP-KCMN6AE/192.168.125.3

        //3.根据域名返回 InetAddress 对象,比如www.baidu.com 对应
        InetAddress localHost2 = InetAddress.getByName("www.baidu.com");
        System.out.println("localHost2:" + localHost2); //localHost2:www.baidu.com/14.215.177.38

        //4.通过InetAddress对象，获取对应的地址
        String hostAddress = localHost2.getHostAddress();
        System.out.println("hostAddress:" + hostAddress); //hostAddress:14.215.177.38

        //5.通过InetAddress对象，获取对应的主机名/或者的域名
        String hostName = localHost2.getHostName();
        System.out.println("hostName:" + hostName); //hostName:www.baidu.com

    }
}
