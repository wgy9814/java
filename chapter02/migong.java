package chapter02;


/**
 * @author xiaoyu
 * @version 1.0
 */
//老鼠出迷宫问题
public class migong {
    public static void main(String[] args) {
        int[][] map = new int[8][7];  //8行7列
        int i, j;
        // 1代表障碍物，0表示可以走
        for (i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        System.out.println("======当前迷宫为======   ");
        for (i = 0; i < map.length; i++) {
            for (j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
        find laoshu = new find();
        laoshu.findWay(map, 1, 1);
        System.out.println("======老鼠移动轨迹为====== ");
        for (i = 0; i < map.length; i++) {
            for (j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }
}

//       0表示可以走，1表示障碍物，2表示可以走，并走过，3表示走过，但是是死路
//       老鼠初始位置是map[1][1]    map[6][5]表示找到通路，游戏结束
class find {
    public boolean findWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {   //找到出口
            return true;
        } else {
            if (map[i][j] == 0) {
                //当前的路可以走
                map[i][j] = 2;    //假设我们可以走，就需要按以下方法来移动，下右上左
                if (findWay(map, i + 1, j))
                    return true;
                else if (findWay(map, i, j + 1))
                    return true;
                else if (findWay(map, i - 1, j))
                    return true;
                else if (findWay(map, i, j - 1))
                    return true;
                else {
                    map[i][j] = 3;
                    return false;
                }
            } else
                return false;
        }
    }
}
