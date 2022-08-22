package chapter26.src.com.wgy.mhl.service;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/21 22:27
 */

import chapter26.src.com.wgy.mhl.dao.BillDAO;
import chapter26.src.com.wgy.mhl.dao.MultiTableDAO;
import chapter26.src.com.wgy.mhl.domain.Bill;
import chapter26.src.com.wgy.mhl.domain.MultiTableBean;

import java.util.List;
import java.util.UUID;

/**
 * @BelongsProject: mhl
 * @BelongsPackage: com.qinbo.mhl.service
 * @Author: 别来无恙qb
 * @CreateTime: 2022-05-28  21:23
 * @Description: 处理和账单相关的业务逻辑
 */
public class BillService {
    //定义一个BillDAO账单属性
    private BillDAO billDAO = new BillDAO();
    //定义一个MenuDAO菜单属性
    private MenuService menuService = new MenuService();
    //定义一个DiningTableService 餐桌属性
    private DiningTableService diningTableService = new DiningTableService();
    //定义一个 MultiTableDAO 联合表属性
    private MultiTableDAO multiTableDAO = new MultiTableDAO();
    //编写点餐的方法
    //1. 生成账单
    //2. 需要更新对应餐桌的状态
    //3. 如果成功返回true, 否则返回false
    public boolean updateBill(int menuId, int nums, int diningTableId) {
        String billId = UUID.randomUUID().toString();
        int update = billDAO.update("insert into bill values (null, ?,?,?,?,?,now(),'未结账' )",
                billId, menuId, nums, menuService.getMenuById(menuId).getPrice() * nums, diningTableId);

        if (update <= 0) {
            return false;
        }
        //订餐成功，修改餐桌状态
        return diningTableService.updateDiningTableState(diningTableId, "就餐中");
    }

    //返回所有的账单， 提供给View调用
    public List<Bill> showBillList() {
        return billDAO.queryMulti("select * from bill", Bill.class);
    }

    //返回所有的账单和菜单， 提供给View调用
    public List<MultiTableBean> showBillList2() {
        return multiTableDAO.queryMulti("SELECT bill.*, NAME,price " +
                "FROM bill, menu "+
                "WHERE bill.menuId = menu.id", MultiTableBean.class);
    }

    //查看某个餐桌是否有未结账的账单
    public boolean hasPayBillByDiningTableId(int diningTableId) {
        //LIMIT 0, 1 表示有一条信息就证明有未结账的账单
        Bill bill = billDAO.querySingle("SELECT * FROM bill WHERE diningTableId=? AND state = '未结账' LIMIT 0, 1", Bill.class, diningTableId);
        return bill != null;
    }

    //完成结账[如果餐桌存在，并且该餐桌有未结账的账单]
    //如果成功，返回true, 失败返回 false
    public boolean payBill(int diningTableId, String payMode) {
        //如果这里使用事务的话，需要用ThreadLocal来解决 , 框架中比如mybatis 提供了事务支持
        //1. 修改bill表
        int update = billDAO.update("update bill set state = ? where diningTableId = ? and state = '未结账'", payMode, diningTableId);
        if (update <= 0) {
            return false;//如果更新没有成功，则表示失败...
        }
        //2. 修改diningTable表
        //注意：不要直接在这里操作，而应该调用DiningTableService 方法,完成更新，体现各司其职
        if (!diningTableService.updateDiningTableToFree(diningTableId)) {
            return false;
        }
        return true;
    }
}
