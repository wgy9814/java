package chapter16.src.com.wgy.tankgame;

import javax.swing.*;
import java.awt.*;

//MyPenal类
public class MyPanel extends JPanel {
    //定义一个我的坦克
    Hero hero = null;
    //构造器
    public MyPanel() {
        hero = new Hero(100,100);//初始化我的坦克
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,500,350);//填充矩形，默认黑色
        drawTank(hero.getX(),hero.getY(),g,0,0);
        drawTank(hero.getX() + 60,hero.getY(),g,0,1);

    }

    /**
     *
     * @param x 坦克的左上角x的坐标
     * @param y 坦克的左上角y的坐标
     * @param g 画笔
     * @param direct 坦克方向（上下左右）
     * @param type 坦克类型
     */
    public void drawTank(int x,int y,Graphics g,int direct,int type){
        switch (type){//根据坦克的类型设置颜色
            case 0://我的tank
                g.setColor(Color.cyan);
                break;
            case 1://敌人的tank
                g.setColor(Color.yellow);
                break;
        }

        switch (direct){//根据坦克的方向绘制坦克
            case 0://0表示向上
                //两个轮子 （左，右）
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
//                //一个中心矩形块
                g.fill3DRect(x+10,y+10,20,40,false);
//                //一个中心圆
                g.fillOval(x+10,y+20,20,20);
//                //一个枪管
                g.drawLine(x+20,y,x+20,y+30);
                break;
            default:
                System.out.println("其他情况暂不做处理");
        }
    }

}