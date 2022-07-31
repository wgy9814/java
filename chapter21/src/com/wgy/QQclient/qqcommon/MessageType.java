package chapter21.src.com.wgy.QQclient.qqcommon;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/30 14:02
 */
//MessageType接口类
/**
 * 表示消息类型
 */
public interface MessageType {
    //接口种定义了不同常量，不同常量代表不同的消息类型。
    String MESSAGE_LOGIN_SUCCEED = "1";//表示登陆成功
    String MESSAGE_LOGIN_FAIL = "2";//表示登陆成功
    String MESSAGE_COMM_MES = "3"; //普通信息包
    String MESSAGE_GET_ONLINE_FRIEND = "4"; //请求返回在线用户列表
    String MESSAGE_RET_ONLINE_FRIEND = "5"; //返回在线用户列表
    String MESSAGE_CLIENT_EXIT = "6"; //客户端请求退出
    String MESSAGE_TO_ALL_MES = "7"; //群发消息报
    String MESSAGE_FILE_MES = "8"; //文件消息(发送文件)
}