package chapter21.src.com.wgy.QQserver.service;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/30 15:02
 */
//ServerConnectClientThread类

import chapter21.src.com.wgy.QQclient.qqcommon.Message;
import chapter21.src.com.wgy.QQclient.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

//ServerConnectClientThread类
/**
 * 该类的一个对象和某个客户端保持通信
 */
public class ServerConnectClientThread extends Thread{
    private Socket socket;//该线程需要持有Socket保持通信
    private String userId;//连接到服务器端的用户Id

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    @Override
    public void run() {//这里线程处于run状态，可以发送或接收到客户端消息
        while (true){
            try {
                System.out.println("服务器端和客户端"+ userId +"保持通信，读取数据");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message ms = (Message)ois.readObject();
                //判断这个message类型，然后做相应的业务处理
                if (ms.getMesType().equals(MessageType.MESSAGE_GET_ONLINE_FRIEND)){
                    //客户端要在线用户列表
                    //在线用户列表格式 100 200 紫霞仙子
                    System.out.println(ms.getSender() + " 要在线用户列表");
                    String onlineUser = ManageServerConnectClientThread.getOnlineUser();
                    //返回message
                    Message message2 = new Message();
                    message2.setMesType(MessageType.MESSAGE_RET_ONLINE_FRIEND);
                    message2.setContent(onlineUser);
                    message2.setGetter(ms.getGetter());
                    //返回给客户端
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message2);

                }else if (ms.getMesType().equals(MessageType.MESSAGE_COMM_MES)){
                    System.out.println(ms.getSender() + " 要和" + ms.getGetter() + " 私聊");
                    //获取接收消息的用户线程
                    ServerConnectClientThread scct = ManageServerConnectClientThread.getServerConnectClientThread(ms.getGetter());
                    //判断是否在线,存在线程就不在线
                    if (scct == null){//不在线，就告知发送消息者
                        System.out.println(ms.getGetter() + " 不在线");
                        Message message3 = new Message();
                        message3.setContent("\n" + ms.getGetter() + " 不在线,无法私聊");
                        message3.setMesType(MessageType.MESSAGE_COMM_MES);
                        message3.setGetter(ms.getSender());
                        //返回给客户端
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        oos.writeObject(message3);
                    }else {//在线，就给接收者发送消息，并告知发送者对方已收到消息
                        Message message4 = new Message();
                        message4.setMesType(MessageType.MESSAGE_COMM_MES);
                        message4.setSender(ms.getSender());
                        message4.setGetter(ms.getGetter());
                        message4.setContent("\n" + ms.getSender() + " 对 "+ ms.getGetter() + " 说： " + ms.getContent());
                        //获取接收者和发送者的线程
                        ServerConnectClientThread scct1 = ManageServerConnectClientThread.getServerConnectClientThread(ms.getGetter());
                        ServerConnectClientThread scct2 = ManageServerConnectClientThread.getServerConnectClientThread(ms.getSender());
                        //获取接收者线程的socket关联的对象输出流，并发送数据
                        ObjectOutputStream oos1 = new ObjectOutputStream(scct1.socket.getOutputStream());
                        ObjectOutputStream oos2 = new ObjectOutputStream(scct2.socket.getOutputStream());
                        oos1.writeObject(message4);
                        oos2.writeObject(message4);
                        System.out.println(ms.getSender() + " 在和 " + ms.getGetter() + " 私聊");
                    }
                }else if(ms.getMesType().equals(MessageType.MESSAGE_TO_ALL_MES)){
                    //需要遍历管理线程的集合，把所有的线程的socket得到，然后把message进行转发即可
                    HashMap<String, ServerConnectClientThread> hm = ManageServerConnectClientThread.getHm();
                    Iterator<String> iterator = hm.keySet().iterator();

                    while (iterator.hasNext()){
                        //取出在线用户id
//                        String onLineUserId = iterator. next().toString();
//                        if(!onLineUserId.equals(ms.getSender())){//排除群发消息的这个用户
////进行转发message
//                            ObjectOutputStream oos =
//                                    new ObjectOutputStream(hm.get(onLineUserId).getSocket().getOutputStream());
//                            oos.writeObject(ms);
//                        }

                        String s = iterator.next();
                        if(!s.equals(ms.getSender())) {//排除群发消息的这个用户
                            Message message = new Message();
                            message.setMesType(MessageType.MESSAGE_TO_ALL_MES);
                            message.setSendTime(new Date().toString());
                            message.setContent(ms.getSender() + " 对大家伙说 " + ms.getContent());

                            message.setSender(ms.getSender());
                            message.setGetter(s);
                            ObjectOutputStream oos = new ObjectOutputStream(ManageServerConnectClientThread.getServerConnectClientThread(s).getSocket().getOutputStream());
                            oos.writeObject(message);
                        }
                    }

                }else if(ms.getMesType().equals(MessageType.MESSAGE_FILE_MES)){
                    String fileGetter = ms.getGetter();
                    ServerConnectClientThread scct = ManageServerConnectClientThread.getServerConnectClientThread(fileGetter);
                    //判断是否在线
                    Message message = new Message();
                    if (scct == null){

                        message.setGetter(ms.getSender());
                        message.setMesType(MessageType.MESSAGE_COMM_MES);
                        message.setContent(ms.getGetter() + " 不在线，无法发送");
                        ObjectOutputStream OOS = new ObjectOutputStream(ManageServerConnectClientThread.getServerConnectClientThread(ms.getSender()).getSocket().getOutputStream());
                        OOS.writeObject(message);
                    }else {
                        ObjectOutputStream oos = new ObjectOutputStream(scct.getSocket().getOutputStream());
                        oos.writeObject(ms);
                    }
                } else if (ms.getMesType().equals(MessageType.MESSAGE_CLIENT_EXIT)){
                    //ServerConnectClientThread类
                    System.out.println(ms.getSender() + " 退出系统");
                    //将对应的线程从线程集合移除

                    ManageServerConnectClientThread.removeServerConnectClientThread(ms.getSender());
                    socket.close();//关闭连接
                    break;//退出线程
                } else {
                    System.out.println("其他的处理");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    //为了更方便的得到Socket
    public Socket getSocket() {
        return socket;
    }
}