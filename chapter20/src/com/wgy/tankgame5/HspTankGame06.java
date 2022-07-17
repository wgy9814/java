package chapter20.src.com.wgy.tankgame5;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * @ClassName HspTankGame01
 * @Author ：BLWY-1124
 * @Date ：2022/3/27 11:21
 * @Description：
 * @Version: 1.0
 */
//为了监听 键盘事件， 实现 KeyListener

/**
 * @ClassName HspTankGame01
 * @Author ：BLWY-1124
 * @Date ：2022/3/27 11:21
 * @Description：
 * @Version: 1.0
 */
//为了监听 键盘事件， 实现 KeyListener
public class HspTankGame06 extends JFrame {
    //定义一个面板
    private MyPanel mp = null;

    public static void main(String[] args) {
        HspTankGame06 hspTankGame06 = new HspTankGame06();
        System.out.println("程序开始");
    }

    public HspTankGame06() {//构造器

        System.out.println("请输入选择 1: 新游戏 2: 继续上局");
        Scanner scanner = new Scanner(System.in);
        String key =  scanner.next();

        //初始化面板
        mp = new MyPanel(key);
        //将mp 放入到Thread ,并启动
        Thread thread = new Thread(mp);
        thread.start();
        //把面板放入到窗口(画框)
        this.add(mp);
        //设置窗口的大小
        this.setSize(1300, 750);
        this.addKeyListener(mp);//让JFrame 监听mp的事件
        //当点击窗口的小×，程序完全退出.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);//可以显示

        //在JFrame 中增加相应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.out.println("窗口关闭");
                System.exit(0);
            }
        });


    }
}
