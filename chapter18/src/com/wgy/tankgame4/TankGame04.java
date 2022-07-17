package chapter18.src.com.wgy.tankgame4;

import javax.swing.*;



/**
 * @ClassName HspTankGame01
 * @Author ：BLWY-1124
 * @Date ：2022/3/27 11:21
 * @Description：
 * @Version: 1.0
 */
//为了监听 键盘事件， 实现 KeyListener
public class TankGame04 extends JFrame {
    //定义一个面板
    private MyPanel mp = null;

    public static void main(String[] args) {
        TankGame04 tankGame04 = new TankGame04();
        System.out.println("程序开始");
    }

    public TankGame04() {//构造器
        //初始化面板
        mp = new MyPanel();
        //将mp 放入到Thread ,并启动
        Thread thread = new Thread(mp);
        thread.start();
        //把面板放入到窗口(画框)
        this.add(mp);
        //设置窗口的大小
        this.setSize(1000, 750);
        this.addKeyListener(mp);//让JFrame 监听mp的事件
        //当点击窗口的小×，程序完全退出.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);//可以显示

    }
}
