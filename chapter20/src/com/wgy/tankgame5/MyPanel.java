package chapter20.src.com.wgy.tankgame5;

//MyPanel类
//为了监听 键盘事件， 实现KeyListener
//为了让Panel 不停的重绘子弹，需要将 MyPanel 实现Runnable ,当做一个线程使用
//为了监听 键盘事件， 实现KeyListener
//为了让Panel 不停的重绘子弹，需要将 MyPanel 实现Runnable ,当做一个线程使用
//为了监听 键盘事件， 实现KeyListener
//为了让Panel 不停的重绘子弹，需要将 MyPanel 实现Runnable ,当做一个线程使用


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;


//为了监听 键盘事件， 实现KeyListener
//为了让Panel 不停的重绘子弹，需要将 MyPanel 实现Runnable ,当做一个线程使用
public class MyPanel extends JPanel implements KeyListener, Runnable {
    //定义我的坦克
    Hero hero = null;
    //定义敌人的坦克放到Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //定义一个存放Node 对象的Vector, 用于恢复敌人坦克的坐标和方向
    Vector<Node> nodes = new Vector<>();

    int enemyTanksSize = 6;
    //定义一个Vector 存放炸弹
    //说明，当子弹击中坦克时，加入一个Bomb对象到bombs
    Vector<Bomb> bombs = new Vector<>();

    //定义三张炸弹图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel( String key) {
        //先判断记录的文件是否存在
        //如果存在，就正常执行，如果文件不存在，提示，只能开启新游戏，key = "1"
        File file = new File(Recorder.getRecordFile());
        if (file.exists()){
            nodes = Recorder.getNodesAndEnemyTankRec();
        }else {
            System.out.println("文件不存在，只能开启新的游戏");
            key = "1";
        }
        //将MyPanel对象的 enemyTanks 设置给 Recorder 的 enemyTanks
        Recorder.setEnemyTanks(enemyTanks);
        hero = new Hero(100, 100);//初始化自己坦克
        hero.setSpeed(6);
        switch (key) {
            case "1":
                //初始敌人的坦克
                for (int i = 0; i < enemyTanksSize; i++) {
                    //创建一个敌人的坦克
                    EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
                    //将enemyTanks 设置给 enemyTank !
                    enemyTank.setEnemyTanks(enemyTanks);
                    //启动坦克线程，让他动起来
                    new Thread(enemyTank).start();
                    //设置方向
                    enemyTank.setDirect(1);
                    //给该enemyTank 添加一颗子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    //加入enemyTank的Vector 成员
                    enemyTank.shots.add(shot);
                    //启动shot对象
                    new Thread(shot).start();
                    //加入
                    enemyTanks.add(enemyTank);
                }
                break;
            case "2": //继续上局游戏
                //初始敌人的坦克
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    //创建一个敌人的坦克
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    //将enemyTanks 设置给 enemyTank !
                    enemyTank.setEnemyTanks(enemyTanks);
                    //启动坦克线程，让他动起来
                    new Thread(enemyTank).start();
                    //设置方向
                    enemyTank.setDirect(node.getDirect());
                    //给该enemyTank 添加一颗子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    //加入enemyTank的Vector 成员
                    enemyTank.shots.add(shot);
                    //启动shot对象
                    new Thread(shot).start();
                    //加入
                    enemyTanks.add(enemyTank);
                }
                break;
            default:
                System.out.println("你的输入有误");
        }

        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));

        //这里，播放指定的音乐
        new AePlayWave("src\\111.wav").start();
    }
    /**
     * 编写方法，显示我方击毁敌方坦克的信息
     */
    public void showInfo(Graphics g) {
        // 画出玩家的成绩
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("您累计击毁敌方坦克", 1020, 30);
        drawTank(1020, 60, g, 0, 0); // 画出一个敌方坦克
        g.setColor(Color.BLACK); //重新设置为黑色
        g.drawString(Recorder.getAllEnemyTankNum() + "", 1080, 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //填充矩形，默认黑色
        g.fillRect(0, 0, 1000, 750);
        //画出成绩面板
        showInfo(g);
        //画出自己坦克-封装方法
        if (hero != null && hero.isLive) { //当我的坦克存活时
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
        }

        //画出hero射出得子弹
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive) {
                System.out.println("绘制子弹。。。");
                g.draw3DRect(shot.x, shot.y, 1, 1, false);
            } else { //hero.shots  shot对象已经无效 ,就从shots集合中拿掉
                hero.shots.remove(shot);
            }
        }

        //如果bombs中有对象， 就绘画
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            //根据当之前这个bomb对象的life值去画出对应的图片’
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            //让这个炸弹的生命值减少
            bomb.lifeDown();
            //如果life=0，就把他从集合中删除
            if (bomb.life == 0) {
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
    //如果我们的坦克可以发射多个子弹
    //在判断我方子弹是否击中敌人坦克时，就需要把我们的子弹集合中
    //所有的子弹，都取出和敌人的所有坦克，进行判断

    /**
     * hitEnemyTank  遍历子弹攻击子弹坦克
     */
    /**
     * 不一样
     */
    public void hitEnemyTank() {
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            //判断是否击中了敌人坦克
            if (shot != null && hero.shot.isLive) {//当我的子弹还活着
                //遍历敌人坦克
                for (int j = 0; j < enemyTanks.size(); j++) {
                    EnemyTank enemyTank = enemyTanks.get(j);
                    /**
                     * 已改
                     */
                    hitTank(hero.shot, enemyTank); //调用hitTank 判断我方子弹是否击中敌方坦克。
                }
            }
        }
    }

    /**
     * 编写方法，判断敌人坦克是否击中我的坦克
     */
    public void hitHero() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出敌人坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //遍历enemyTank 对象的所有子弹
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                //取出子弹
                Shot shot = enemyTank.shots.get(j);
                //判断 shot 是否击中我的坦克
                if (shot != null && shot.isLive) {//当敌人的子弹还活着
                    hitTank(shot, hero); //调用hitTank 判断我方子弹是否击中敌方坦克。
                }

            }
        }
    }

    //编写方法，判断我方的子弹是否击中敌人坦克.
    //什么时候判断 我方的子弹是否击中敌人坦克 ? run方法
    //后面我们将 enemyTank 改成 tank名称
    public void hitTank(Shot s, Tank tank) {
        //判断击中敌方坦克
        switch (tank.getDirect()) {
            case 0://坦克向上
            case 2://坦克向下
                if (s.x > tank.getX() && s.x < tank.getX() + 40 &&
                        s.y > tank.getY() && s.y < tank.getY() + 60) {
                    //坦克消失，子弹消失，线程终止
                    tank.isLive = false;
                    s.isLive = false;
                    //当我的子弹击中敌人坦克后，将enemyTank 从Vector 拿掉
                    enemyTanks.remove(tank);
                    //当我方击毁一个敌人坦克时，就对数据allEnemyTankNum++
                    //解读, 因为 tank 可以是 Hero 也可以是 EnemyTank
                    if (tank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                    hero.shots.remove(s);
                    //坦克被击中爆炸 创建Bomb对象，加入到bombs集合
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1://坦克向左
            case 3://坦克向右
                if (s.x > tank.getX() && s.x < tank.getX() + 60 &&
                        s.y > tank.getY() && s.y < tank.getY() + 40) {
                    //坦克消失，子弹消失，线程终止
                    tank.isLive = false;
                    s.isLive = false;
                    //坦克被击中爆炸 创建Bomb对象，加入到bombs集合
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                    enemyTanks.remove(tank);
                    //当我方击毁一个敌人坦克时，就对数据allEnemyTankNum++
                    //解读, 因为 tank 可以是 Hero 也可以是 EnemyTank
                    if (tank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                }
                break;
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
            if (hero.getX() + 60 < 1000) {
                hero.moveRight();//向右
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {//S键
            hero.setDirect(2);
            if (hero.getY() + 60 < 750) {
                hero.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {//A键
            hero.setDirect(3);
            if (hero.getX() > 0) {
                hero.moveLeft();
            }
        }
        //如果用户按下的时J，就发射
        if (e.getKeyCode() == KeyEvent.VK_J) { //J键 射击
            //判断hero子弹是否销毁 一颗子弹
//                if (hero.shot == null || hero.shot.isLive) {
//                    System.out.println("用户按下了J, 开始射击.");
//                    hero.shotEnemyTank();
            // }
            //发射多颗子弹
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
            // 调用方法  判断是否击中了敌人
            hitEnemyTank();
            //调用方法  判断是否击中了自己
            hitHero();
            //重绘
            this.repaint();
        }
    }
}
