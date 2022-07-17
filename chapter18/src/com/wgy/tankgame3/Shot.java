package chapter18.src.com.wgy.tankgame3;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/9 13:42
 */
//1.当发射一颗子弹后，就相当于启动一个线程
//2. Hero有子弹的对象，当按下J时，我们就启动一个发射行为(线程)，让子弹不停的移动，形成一个射击的效果
//3.我们MyPanel需要不停的重绘子弹，才能出现该效果.4.当子弹移动到面板的边界时，就应该销毁
public class Shot implements Runnable {
    int x; //子弹x坐标
    int y; //子弹y坐标
    int direct = 0; //子弹方向
    int speed = 2; //子弹的速度
    boolean isLive = true; //子弹是否还存活

    //构造器
    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {//射击
        while (true) {

            //休眠 50毫秒
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //根据方向来改变x,y坐标
            switch (direct) {
                case 0://上
                    y -= speed;
                    break;
                case 1://右
                    x += speed;
                    break;
                case 2://下
                    y += speed;
                    break;
                case 3://左
                    x -= speed;
                    break;
            }
            //测试，这里我们输出x,y的坐标
            System.out.println("子弹 x=" + x + " y=" + y);
            //当子弹移动到面板的边界时，就应该销毁（把启动的子弹的线程销毁)
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750)) {
                System.out.println("子弹线程退出");
                isLive = false;
                break;
            }

        }
    }
}
