package chapter18.src.com.wgy.tankgame3;

import javax.swing.*;

//HspTankGame02类
public class TankGame03 extends JFrame {

    //定义MyPanel
    MyPanel mp = null;
    public static void main(String[] args) {

        TankGame03 hspTankGame01 = new TankGame03();
    }

    public TankGame03() {
        mp = new MyPanel();
        //将mp 放入到Thread ,并启动
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);//把面板(就是游戏的绘图区域)

        this.setSize(1000, 750);
        this.addKeyListener(mp);//让JFrame 监听mp的键盘事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}