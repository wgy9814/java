package chapter18.src.com.wgy.tankgame4;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/10 14:17
 */

public class Bomb {
    int x;
    int y;
    int life = 9; //炸弹的生命周期
    boolean isLive = true; //炸弹是否存活

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //减少生命值
    public void lifeDown(){
        if (life > 0){
            life--;
        }else {
            isLive = false;
        }
    }
}
