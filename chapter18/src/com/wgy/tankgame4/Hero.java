package chapter18.src.com.wgy.tankgame4;

//Hero类

public class Hero extends Tank {
    //定义一个Shot对象, 表示一个射击(线程)
    Shot shot = null;

    public Hero(int x, int y) {
        super(x, y);
    }
    //射击子弹
    public void shotEnemyTank(){
        //创建 shot 对象，根据当前hero对象的位置和方向来创建Shot
        switch (getDirect()){
            case 0: //向上
                shot = new Shot(getX()+20, getY(),0);
                break;
            case 1://向由
                shot = new Shot(getX()+60,getY()+20,1);
                break;
            case 2://向下
                shot = new Shot(getX() +20,getY()+60,2);
                break;
            case 3://向左
                shot = new Shot(getX(),getY() + 20,3);
        }
        //启动我们的Shot线程
        new Thread(shot).start();
    }
}
