package chapter20.src.com.wgy.tankgame5;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/17 20:37
 */

import java.io.*;
import java.util.Vector;


public class Recorder {
    //定义变量 记录我方击毁敌方坦克
    private static int allEnemyTankNum = 0;
    //定义IO对象，准备写数据到文件中
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    //把记录文件保存到 src 下
    //private static String recordFile = "e:\\myRecord.txt";
    private static String recordFile = "src\\recordFile.txt";


    //定义Vector ,指向 MyPanel 对象的 敌人坦克Vector
    public static Vector<EnemyTank> enemyTanks = null;

    //定义一个Node的Vector集合，用于保存敌人的信息
    private static Vector<Node> nodes = new Vector<>();

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    //返回记录文件的目录
    public static String getRecordFile() {
        return recordFile;
    }

    //增加一个方法 用于读取recordFile，回复相关信息
    public static Vector<Node> getNodesAndEnemyTankRec() {

        try {
            br = new BufferedReader(new FileReader(recordFile));
            allEnemyTankNum = Integer.parseInt(br.readLine());
            //循环读取文件,生成nodes集合
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] syd = line.split(" ");
                Node node = new Node(Integer.parseInt(syd[0]), Integer.parseInt(syd[1]),Integer.parseInt(syd[2]));
                nodes.add(node); //放入nodes Vector

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br.readLine() != null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return nodes;
    }

    //增加一个方法，当游戏退出时，将 allEnemyTankNum 保存到文件中 recordFile
    //对keepRecord 进行升级, 保存敌人坦克的坐标和方向
    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum + "\r\n");
            //遍历敌人坦克的Vector ,然后根据情况保存即可.
            //OOP, 定义一个属性 ，然后通过setXxx得到 敌人坦克的Vector
            for (int i = 0; i < enemyTanks.size(); i++) {
                //取出敌人对象
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive) { //判断坦克是否存活
                    //保存enemyTank 的信息
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect();
                    //写入文件中
                    bw.write(record + "\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    // 当我方击毁一个敌人坦克，就应当 allEnemyTankNum ++
    public static void addAllEnemyTankNum() {
        Recorder.allEnemyTankNum++;
    }
}
