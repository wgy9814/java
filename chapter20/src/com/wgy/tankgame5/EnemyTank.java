package chapter20.src.com.wgy.tankgame5;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/2 15:50
 */
//EnemyTank类
//让敌人的坦克也能够发射子弹(可以有多颗子弹)
//1.在敌人坦克类，使用Vector保存多个Shot
//2.当每创建一个敌人坦克对象，给该敌人坦克对象初始化一个Shot对象，同时启动Shot
//3.在绘制敌人坦克时，需要遍历敌人坦克对象Vector,绘制所有的子弹,当子弹isLive == false时，就从Vector移除




import java.util.Vector;


public class EnemyTank extends Tank implements Runnable {
    //在敌人坦克类，使用Vector 保存多个Shot
    Vector<Shot> shots = new Vector<>();

    //添加成员，EnemyTank 可以得到敌人坦克的 Vector
    //1.  Vector<EnemyTank> 在
    Vector<EnemyTank> enemyTanks = new Vector<>();

    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    //这里提供一个方法，可以将MyPanel 的 成员 Vector<EnemyTank> enemyTanks = new Vector<>();
    //设置到 EnemyTank 的成员 enemyTanks
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    //编写方法，判断当前这个敌人坦克，是否和 enemyTanks 中的其他坦克发生重叠或者碰撞
    public boolean isTouchEnemyTank() {
        //判断当前敌人坦克（this）方向
        switch (this.getDirect()) {
            case 0: //向上
                //让当前敌人坦克和其它所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector 中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上/下
                        //老韩分析
                        //1. 如果敌人坦克是上/下 x的范围 [enemyTank.getX(), enemyTank.getX() + 40]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2. 当前坦克 左上角的坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && enemyTank.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3. 当前坦克 右上角的坐标 [this.getX() + 40, this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && enemyTank.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }

                        //如果敌人坦克是 右/左
                        //老韩分析
                        //1. 如果敌人坦克是右/左  x的范围 [enemyTank.getX(), enemyTank.getX() + 60]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //2. 当前坦克 左上角的坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //3. 当前坦克 右上角的坐标 [this.getX() + 40, this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1: //向右
                //让当前敌人坦克和其它所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector 中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上/下
                        //老韩分析
                        //1. 如果敌人坦克是上/下 x的范围 [enemyTank.getX(), enemyTank.getX() + 40]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2. 当前坦克 右上角的坐标 [this.getX()+60, this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3. 当前坦克 右上角的坐标 [this.getX()+60 , this.getY() + 40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }

                        //如果敌人坦克是 右/左
                        //老韩分析
                        //1. 如果敌人坦克是右/左  x的范围 [enemyTank.getX(), enemyTank.getX() + 60]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //2. 当前坦克 右上角的坐标 [this.getX() + 60, this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //3. 当前坦克 右下角的坐标 [this.getX() + 60, this.getY() + 40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2: //向下
                //让当前敌人坦克和其它所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector 中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上/下
                        //老韩分析
                        //1. 如果敌人坦克是上/下 x的范围 [enemyTank.getX(), enemyTank.getX() + 40]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2. 当前坦克 左下角的坐标 [this.getX(), this.getY() + 60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3. 当前坦克 右下角的坐标 [this.getX() + 40 , this.getY() + 60]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }

                        //如果敌人坦克是 右/左
                        //老韩分析
                        //1. 如果敌人坦克是右/左  x的范围 [enemyTank.getX(), enemyTank.getX() + 60]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //2. 当前坦克 左下角的坐标 [this.getX(), this.getY() + 60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //3. 当前坦克 右下角的坐标 [this.getX() + 40, this.getY() + 60]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3: //向左
//让当前敌人坦克和其它所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector 中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上/下
                        //老韩分析
                        //1. 如果敌人坦克是上/下 x的范围 [enemyTank.getX(), enemyTank.getX() + 40]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2. 当前坦克 左上角的坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3. 当前坦克 左下角的坐标 [this.getX() , this.getY() + 40]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }

                        //如果敌人坦克是 右/左
                        //老韩分析
                        //1. 如果敌人坦克是右/左  x的范围 [enemyTank.getX(), enemyTank.getX() + 60]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //2. 当前坦克 左上角的坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //3. 当前坦克 左下角的坐标 [this.getX(), this.getY() + 40]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
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
                        // isTouchEnemyTank() 撞到了返回true，没撞到返回false
                        if (getY() > 0 && !isTouchEnemyTank()) {
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
                        if (getX() < 1000 - 60  && !isTouchEnemyTank()) {
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
                        if (getY() < 750 - 60  && !isTouchEnemyTank()) {
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
                        if (getX() > 0  && !isTouchEnemyTank()) {
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
