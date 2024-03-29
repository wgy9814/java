package chapter25.src.com.wgy.dao_.test;

import chapter25.src.com.wgy.dao_.dao.ActorDAO;
import chapter25.src.com.wgy.dao_.domain.Actor;

import java.util.List;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/19 23:39
 */
public class TestDAO {
    public static void main(String[] args) {
        ActorDAO actorDAO = new ActorDAO();

        //1. 查询
        List<Actor> actors = actorDAO.queryMulti("select * from actor where id >= ?", Actor.class, 1);
        System.out.println("===查询结果===");
        for (Actor actor : actors) {
            System.out.println(actor);
        }

        //2. 查询单行记录
        Actor actor = actorDAO.querySingle("select * from actor where id = ?", Actor.class, 6);
        System.out.println("====查询单行结果====");
        System.out.println(actor);

        //3. 查询单行单列
        Object o = actorDAO.queryScalar("select name from actor where id = ?", 6);
        System.out.println("====查询单行单列值===");
        System.out.println(o);

        //4. dml操作  insert ,update, delete
        int update = actorDAO.update("insert into actor values(null, ?, ?, ?, ?)", "张无忌", "男", "2000-11-11", "999");

        System.out.println(update > 0 ? "执行成功" : "执行没有影响表");
    }

}
