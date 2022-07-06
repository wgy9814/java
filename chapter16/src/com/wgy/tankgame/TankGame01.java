package chapter16.src.com.wgy.tankgame;

import javax.swing.*;

//TankGame01类
public class TankGame01 extends JFrame {
    //定义一个面板
    MyPanel myPanel = null;
    public static void main(String[] args) {
        new TankGame01();
    }
    public TankGame01(){
        //初始化面板
        myPanel = new MyPanel();
        //把面板加入到画框
        this.add(myPanel);
        //设置窗口大小，和面板一样大即可
        this.setSize(500,350);
        //设置可视化
        this.setVisible(true);
        //设置摁x终止程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}