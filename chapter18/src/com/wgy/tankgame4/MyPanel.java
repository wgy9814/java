package chapter18.src.com.wgy.tankgame4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//MyPanel类
//为了监听 键盘事件， 实现KeyListener
//为了让Panel 不停的重绘子弹，需要将 MyPanel 实现Runnable ,当做一个线程使用
//为了监听 键盘事件， 实现KeyListener
//为了让Panel 不停的重绘子弹，需要将 MyPanel 实现Runnable ,当做一个线程使用
//为了监听 键盘事件， 实现KeyListener
//为了让Panel 不停的重绘子弹，需要将 MyPanel 实现Runnable ,当做一个线程使用

public class MyPanel extends JPanel implements KeyListener ,Runnable{
    //定义我的坦克
    Hero hero = null;
    //定义敌人的坦克放到Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTanksSize = 3;
    //定义一个Vector 存放炸弹
    //说明，当子弹击中坦克时，加入一个Bomb对象到bombs
    Vector<Bomb> bombs = new Vector<>();

    //定义三张炸弹图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel() {
        hero = new Hero(100, 100);//初始化自己坦克
        hero.setSpeed(6);
        //初始敌人的坦克
        for (int i = 0; i < enemyTanksSize; i++) {
            //创建一个敌人的坦克
            EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
            //启动坦克
            new Thread(enemyTank).start();
            //设置方向
            enemyTank.setDirect(2);
            //给该enemyTank 添加一颗子弹
            Shot shot = new Shot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirect());
            //加入enemyTank的Vector 成员
            enemyTank.shots.add(shot);
            //启动shot对象
            new Thread(shot).start();
            //加入
            enemyTanks.add(enemyTank);
        }
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //填充矩形，默认黑色
        g.fillRect(0, 0, 1000, 750);
        //画出自己坦克-封装方法
        if(hero != null && hero.isLive){
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
        }

        //画出hero射出得子弹
        if (hero.shot != null && hero.shot.isLive){
            System.out.println("绘制子弹。。。");
            g.draw3DRect(hero.shot.x,hero.shot.y,1,1,false);
        }
        //如果bombs中有对象， 就绘画
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            //根据当之前这个bomb对象的life值去画出对应的图片’
            if (bomb.life >6){
                g.drawImage(image1,bomb.x, bomb.y,60,60,this);
            }else if (bomb.life >3){
                g.drawImage(image2,bomb.x, bomb.y,60,60,this);
            }else {
                g.drawImage(image3,bomb.x, bomb.y,60,60,this);
            }
            bomb.lifeDown();
            //如果life=0，就把他从集合中删除
            if (bomb.life == 0){
                bombs.remove(bomb);
            }
        }
        //画出敌人的坦克, 遍历Vector

        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive) {//当敌人坦克是存活的，才画出该坦克
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
                //画出 enemyTank 所有子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //取出子弹
                    Shot shot = enemyTank.shots.get(j);
                    if (shot.isLive) { //isLive == true
                        g.draw3DRect(shot.x, shot.y, 1, 1, false);
                    } else {
                        //从Vector 移除
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }
    }
    //编写方法，画出坦克

    /**
     * @param x      坦克的左上角 x 坐标
     * @param y      坦克的左上角 y 坐标
     * @param g      画笔
     * @param direct 坦克方向（上下左右）
     * @param type   坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {

        //根据不同类型坦克，设置不同颜色
        switch (type) {
            case 0: //敌人的坦克
                g.setColor(Color.cyan);
                break;
            case 1: //我的坦克
                g.setColor(Color.yellow);
                break;
        }

        //根据坦克方向，来绘制对应形状坦克
        //direct 表示方向(0: 向上 1 向右 2 向下 3 向左 )
        //
        switch (direct) {
            case 0: //表示向上
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y);//画出炮筒
                break;
            case 1: //表示向右
                g.fill3DRect(x, y, 60, 10, false);//画出坦克上边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克下边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克盖子
                g.fillOval(x + 20, y + 10, 20, 20);//画出圆形盖子
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//画出炮筒
                break;
            case 2: //表示向下
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//画出炮筒
                break;
            case 3: //表示向左
                g.fill3DRect(x, y, 60, 10, false);//画出坦克上边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克下边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克盖子
                g.fillOval(x + 20, y + 10, 20, 20);//画出圆形盖子
                g.drawLine(x + 30, y + 20, x, y + 20);//画出炮筒
                break;
            default:
                System.out.println("暂时没有处理");
        }

    }
    //编写方法，判断我方子弹是否击中敌方坦克。
    public  void hitTank( Shot s, Tank enemyTank){
        //判断击中敌方坦克
        switch (enemyTank.getDirect()){
            case 0://坦克向上
            case 2://坦克向下
                if (s.x > enemyTank.getX() && s.x < enemyTank.getX()+40 &&
                        s.y >enemyTank.getY() && s.y < enemyTank.getY()+60){
                    //坦克消失，子弹消失，线程终止
                    enemyTank.isLive = false;
                    s.isLive = false;
                    //当我的子弹击中敌人坦克后，将enemyTank 从Vector 拿掉
                    enemyTanks.remove(enemyTank);
                    //坦克被击中爆炸 创建Bomb对象，加入到bombs集合
                    Bomb bomb =  new Bomb(enemyTank.getX(),enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1://坦克向左
            case 3://坦克向右
                if (s.x > enemyTank.getX() && s.x < enemyTank.getX()+60 &&
                        s.y >enemyTank.getY() && s.y < enemyTank.getY()+40){
                    //坦克消失，子弹消失，线程终止
                    enemyTank.isLive = false;
                    s.isLive = false;
                    //坦克被击中爆炸 创建Bomb对象，加入到bombs集合
                    Bomb bomb =  new Bomb(enemyTank.getX(),enemyTank.getY());
                    bombs.add(bomb);
                    enemyTanks.remove(enemyTank);
                }
                break;
        }
    }

    //编写方法，判断敌方子弹是否击中我方坦克。
    public void hitEnemyTank() {
        //判断是否击中了敌人
        if ( hero.shot != null && hero.shot.isLive) {//当我的子弹还活着
            //遍历敌人坦克
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                hitTank(hero.shot, enemyTank);
            }
        }
    }

    //编写方法，判断敌方子弹是否击中我方坦克。
    public void hitHero() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive) {//当敌人坦克是存活的，才画出该坦克
                //画出 enemyTank 所有子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //取出子弹
                    Shot shot = enemyTank.shots.get(j);
                    if (hero.isLive && shot.isLive) {
                        hitTank(shot, hero);
                    } else {

                    }
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理 wdsa 键按下的情况
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_W) {//按下W键
            //改变坦克的方向
            hero.setDirect(0);//
            //修改坦克的坐标 y -= 1
            if (hero.getY() > 0) {
                hero.moveUp(); //向上
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {//D键, 向右
            hero.setDirect(1);
            if (hero.getX() +60 <1000) {
                hero.moveRight();//向右
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {//S键
            hero.setDirect(2);
            if (hero.getY() +60< 750) {
                hero.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {//A键
            hero.setDirect(3);
            if (hero.getX() > 0) {
                hero.moveLeft();
            }
        }
        //如果用户按下的时J，就发射
        if (e.getKeyCode() == KeyEvent.VK_J){ //J键 射击
            System.out.println("用户按下了J, 开始射击.");
            hero.shotEnemyTank();
        }
        //让面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() { //每隔 100ms  重绘区域, 刷新绘图区域, 子弹就移动
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hitEnemyTank();

            //判断敌人是否击中了我方
            hitHero();
            this.repaint();
        }
    }
}
