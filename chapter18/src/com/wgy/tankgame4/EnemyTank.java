package chapter18.src.com.wgy.tankgame4;

import java.util.Vector;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/2 15:50
 */
//EnemyTank类
//让敌人的坦克也能够发射子弹(可以有多颗子弹)
//1.在敌人坦克类，使用Vector保存多个Shot
//2.当每创建一个敌人坦克对象，给该敌人坦克对象初始化一个Shot对象，同时启动Shot
//3.在绘制敌人坦克时，需要遍历敌人坦克对象Vector,绘制所有的子弹,当子弹isLive == false时，就从Vector移除



public class EnemyTank extends Tank implements Runnable{
    //在敌人坦克类，使用Vector 保存多个Shot
    Vector<Shot> shots = new Vector<>();
    public EnemyTank(int x, int y) {
        super(x, y);
    }
    //创建线程
    @Override
    public void run() {
        while (true) {
            //这里我们判断如果shots size() =0, 创建一颗子弹，放入到
            //shots集合，并启动
            if (isLive && shots.size() < 1) {
                Shot s = null;
                switch (getDirect()) {
                    case 0: //向上
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1://向由
                        s = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2://向下
                        s = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3://向左
                        s = new Shot(getX(), getY() + 20, 3);
                }
                shots.add(s);
                //启动我们的Shot线程
                new Thread(s).start();
            }
            //根据坦克的方向来继续激动
            switch (getDirect()) {
                case 0://向上
                    //移动随机步数
                    for (int i = 0; i < (int) (Math.random() * 100); i++) {
                        if (getY() > 0) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1: //向右
                    for (int i = 0; i < (int) (Math.random() * 100); i++) {
                        if (getX() <1000-60) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2: //向下
                    for (int i = 0; i < (int) (Math.random() * 100); i++) {
                        if (getY() <750 - 60) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3: //向左
                    for (int i = 0; i < (int) (Math.random() * 100); i++) {
                        if (getX() > 0) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            //然后随机的改变坦克方向 0-3
            setDirect((int) (Math.random() * 4));
            if (!isLive) {
                //听老韩说，写并发程序，一定要考虑清楚，该线程什么时候结束
                break;//退出线程.
            }
        }
    }
}
