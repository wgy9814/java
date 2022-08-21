package chapter26.src.com.wgy.mhl.service;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/21 22:20
 */

import chapter26.src.com.wgy.mhl.dao.MenuDAO;
import chapter26.src.com.wgy.mhl.domain.Menu;

import java.util.List;

/**
 * @BelongsProject: mhl
 * @BelongsPackage: com.qinbo.mhl.service
 * @Author: 别来无恙qb
 * @CreateTime: 2022-05-26  21:21
 * @Description:
 */
public class MenuService {
    private MenuDAO menuDAO = new MenuDAO();
    //获取菜单数据 返回所有的菜品, 返回给界面使用
    public List<Menu> menuList(){
        return menuDAO.queryMulti("select * from menu",Menu.class);
    }
    //根据id, 返回Menu对象
    public Menu getMenuById(int id){
        return menuDAO.querySingle("select * from menu where id = ?",Menu.class,id);
    }
}
