package chapter21.src.com.wgy.QQserver.qqcommon;

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
}