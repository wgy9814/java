package chapter21.src.com.wgy.QQclient.service;

import chapter21.src.com.wgy.QQclient.qqcommon.Message;
import chapter21.src.com.wgy.QQclient.qqcommon.MessageType;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/30 14:24
 */
//ClientConnectServerThread类
public class ClientConnectServerThread extends Thread {
    private Socket socket;//

    //构造器可以接受一个Socket对象
    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        //因为在Thread需要在后台和服务器一直保持通信，因此我们while循环
        while (true){
            System.out.println();
            System.out.println("客户端线程，在等待读取从服务器端返回的消息");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //如果服务器没有发送的Message对象，线程会堵塞在这里。
                Message ms = (Message)ois.readObject();
                //判断这个message类型，然后做相应的业务处理
                //如果读取到的是服务端返回的在线用户列表
                if (ms.getMesType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND)){
                    //取出在线列表信息，并显示
                    //规定
                    String[] onlineUsers = ms.getContent().split(" ");
                    System.out.println();
                    System.out.println("========在线用户列表========");
                    for (int i = 0; i < onlineUsers.length; i++) {
                        System.out.println("用户:" + onlineUsers[i]);

                    }
                }else if (ms.getMesType().equals(MessageType.MESSAGE_COMM_MES)){
                    System.out.println("\n" + ms.getSender()
                            +"对" +ms.getGetter() +"说:" +ms.getContent());
                    //显示信息即可
//                    System.out.println(ms.getContent());
                }else if (ms.getMesType().equals(MessageType.MESSAGE_TO_ALL_MES)){
                    //显示在客户端的控制台
                    System. out.println("In" + ms.getSender() +"对大家说:" + ms.getContent());
                }else if (ms.getMesType().equals(MessageType.MESSAGE_FILE_MES)){
                    byte[] fileBytes = ms.getFileBytes();
                    String dest = ms.getDest();
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest));
                    bos.write(fileBytes,0,fileBytes.length);
                    bos.close();
                    System.out.println("\n"+ms.getSender() +" 给 " +ms.getGetter() + " 发文件，保存到 " +ms.getDest());
                }else {
                    System.out.println("是其他类型的message，暂时不处理..");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    //为了更方便的得到Socket
    public Socket getSocket() {
        return socket;
    }
}