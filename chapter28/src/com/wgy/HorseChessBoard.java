package chapter28.src.com.wgy;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author 某某某
 * @date 2022/8/23 17:18
 */
public class HorseChessBoard {
    private static int X;//棋盘的列数
    private static int Y;//棋盘的行数
    //标记棋盘的各个位置是否被访问过
    private static boolean visited[];
    //标记是否棋盘的所有位置都被访问过
    private static boolean finished;//成功true,否则false

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1;//马儿初始位置的行，从1开始编号
        int column = 1;//马儿初始位置的列，从1开始编号

        //创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];//初始值都是false

        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时：" + (end - start) + "毫秒");

        //输出棋盘的最后情况
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }

    }

    /**
     * 完成其实周游问题的算法
     *
     *
     * /编写最核心的算法，遍历棋盘，如果遍历成功，就把 finished 设置为true
     * ,//并且，将马儿走的每一步step，记录到 chessBoard
     * @param chessboard 算盘
     * @param row        马儿当前的位置的行，从0开始
     * @param column     列
     * @param step       第几步，初始是第1步
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        //先把step记录到 chessBoard
        chessboard[row][column] = step;
        visited[row * X + column] = true;//标记该位置已经访问

        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));   //point.x 列  point.y 行
        //对ps进行排序，排序的规则就是对ps的所有的Point对象的下一步的位置的数目，进行非递减排序  1 2 2 3 4 4 4 5..
        sort(ps);
        //遍历ps
        while (!ps.isEmpty()) {
            //取出当前这个ps第一个位置(点)
            //取出下一个可以走的位置
            Point p = ps.remove(0);
            //判断该点是否已经被访问过
            if (!visited[p.y * X + p.x]) {//未访问过
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }

        //当退出while，看看是否遍历成功，如果没有成功，就重置相应的值，然后进行回溯
        //判断马儿是否完成了任务，使用step和应该走的步数比较
        //如果没有达到数量，则表示没有完成任务，将整个棋盘置0
        //  step<X*Y: 棋盘到目前位置，仍然没有走完
        //            或棋盘处于一个回溯过程
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }

    }

    /**
     * 根据当前位置（Point对象)，计算马儿还能走那些位置，并放入到一个集合中，最多有八个
     * 编写方法，可以获取当前位置，可以走的下一步的所有位置(Point表示x,y)
     *
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<>();

        //创建一个Point对象(点/位置)，准备放入到 ps
        Point p1 = new Point();

        //判断在 curPoint是否可以走如下位置，如果可以走，就将该点(Point)放入到ps

        //马儿是否可以走5的位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));//这里一定要new Point
        }
        //马儿是否可以走6的位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //马儿是否可以走7的位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //马儿是否可以走0的位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //马儿是否可以走1的位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //马儿是否可以走2的位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //马儿是否可以走3的位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //马儿是否可以走4的位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }

        return ps;

    }


    //写一个方法，对ps的各个位置，可以走的下一个位置的次数进行排序，把可能走的下一个位置从小到大排序
    //重要

    //根据到当前这一步的所有的下一步的选择位置，进行非递减排序
    //减少回溯的次数
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //获取到o1的下一步的所有位置的个数
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if (count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}