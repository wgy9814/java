package chapter26.src.com.wgy.mhl.view;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/21 14:08
 */


import chapter26.src.com.wgy.mhl.domain.DiningTable;
import chapter26.src.com.wgy.mhl.domain.Employee;
import chapter26.src.com.wgy.mhl.domain.Menu;
import chapter26.src.com.wgy.mhl.domain.MultiTableBean;
import chapter26.src.com.wgy.mhl.service.BillService;
import chapter26.src.com.wgy.mhl.service.DiningTableService;
import chapter26.src.com.wgy.mhl.service.EmployeeService;
import chapter26.src.com.wgy.mhl.service.MenuService;
import chapter26.src.com.wgy.mhl.utils.Utility;

import java.util.List;

/**
 * @BelongsProject: mhl
 * @BelongsPackage: com.qinbo.mhl.view
 * @Author: 别来无恙qb
 * @CreateTime: 2022-05-23  21:18
 * @Description: 满汉楼主界面
 */
public class MHLView {
    //控制是否退出菜单
    private boolean loop = true;
    private String key = "";

    //定义EmployeeService 属性
    private EmployeeService employeeService = new EmployeeService();
    //定义 DiningTableService属性
    private DiningTableService diningTableService = new DiningTableService();
    //定义 MenuService
    private MenuService menuService = new MenuService();
    //定义 BillService属性
    private BillService billService = new BillService();
    private Utility Utility;


    public static void main(String[] args) {
        MHLView mhlView = new MHLView();
        mhlView.mainView();
    }

    //完成预定餐桌 订座
    public void orderDiningTable() {
        System.out.println("==============预定餐桌============");
        System.out.print("请选择要预定的餐桌编号(-1退出): ");
        int orderId = Utility.readInt();
        //判断orderId是否为-1 是就退出
        if (orderId == -1) {
            System.out.println( "==============取消预订餐桌============");
            return;
        }
        char key = Utility.readConfirmSelection();
        if (key == 'Y') { // 确认预定

            DiningTable diningTableById = diningTableService.getDiningTableById(orderId);

            //根据orderId 返回 对应DiningTable对象, 如果为null, 说明该对象不存在
            if (diningTableById == null) { //如果为null, 说明该对象不存在, 就是没有查出来
                System.out.println("==============预订餐桌不存在============");
                return;
            }
            //判断该餐桌的状态是否 "空"
            if (!(diningTableById.getState().equals("空"))) {//说明当前这个餐桌不是 "空" 状态
                System.out.println("==============该餐桌已经预定或者就餐中============");
                return;
            }
            System.out.print("预定人的名字: ");
            String orderName = Utility.readString(50);
            System.out.print("预定人的电话: ");
            String orderTel = Utility.readString(50);

            //修改餐桌状态
            if (diningTableService.orderDiningTable(orderId,orderName,orderTel)){
                System.out.println("==============预订餐桌成功============");
            }else {
                System.out.println("==============预订餐桌失败============");
            }

        }else {
            System.out.println("==============取消预订餐桌============");
        }
    }

    //显示所有餐桌状态
    public void listDiningTable() {
        List<DiningTable> diningTables = diningTableService.tableList();
        System.out.println("\n餐桌编号\t\t餐桌状态");
        for (DiningTable diningTable : diningTables) {
            System.out.println(diningTable);
        }
        System.out.println("==============显示完毕============");
    }
    //listMenu 显示菜单
    public void listMenu(){
        List<Menu> menuList = menuService.menuList();
        System.out.println("\n菜品编号\t\t菜品名\t\t类别\t\t价格");
        for (Menu menu : menuList) {
            System.out.println(menu);
        }
        System.out.println("==============显示完毕============");
    }
    //完成点餐
    public void orderMenu(){
        System.out.println("==============点餐服务============");
        System.out.print("请输入点餐的桌号(-1退出): ");
        int orderDiningTableId = Utility.readInt();
        if(orderDiningTableId ==-1){
            System.out.println("==============取消点餐============");
            return;
        }
        System.out.print("请输入点餐的菜品号(-1退出): ");
        int orderMenuId = Utility.readInt();
        if (orderMenuId == -1) {
            System.out.println("==============取消点餐============");
            return;
        }
        System.out.print("请输入点餐的菜品量(-1退出): ");
        int orderNums = Utility.readInt();
        if (orderNums == -1) {
            System.out.println("==============取消点餐============");
            return;
        }
        //验证餐桌是否存在
        DiningTable diningTableById = diningTableService.getDiningTableById(orderDiningTableId);
        if (diningTableById == null){
            System.out.println("==============餐桌号不存在============");
            return;
        }
        //验证菜品是否存在
        Menu menuById = menuService.getMenuById(orderMenuId);
        if (menuById == null){
            System.out.println("==============菜品不存在============");
            return;
        }
        //更新餐桌状态
        if (billService.updateBill(orderMenuId,orderNums,orderDiningTableId)) {
            System.out.println("==============点餐成功============");
        }else {
            System.out.println("==============点餐失败============");
        }
    }
    //显示账单
    public void showBillList2(){
        /*List<Bill> showBillList = billService.showBillList();
        System.out.println("\n编号\t\t菜品号\t\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t\t\t\t状态");
        for (Bill bill : showBillList) {
            System.out.println(bill);
        }
        System.out.println("==============显示完毕============");*/
        List<MultiTableBean> multiTableBeans = billService.showBillList2();
        System.out.println("\n编号\t\t菜品号\t\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t\t\t\t状态\t\t菜品名\t\t价格");
        for (MultiTableBean bill : multiTableBeans) {
            System.out.println(bill);
        }
        System.out.println("==============显示完毕============");
    }
    //结账
    public void payBill(){
        System.out.println("==============结账服务============");
        System.out.print("请选择要结账的餐桌编号(-1退出): ");
        int diningTableId = Utility.readInt();
        if (diningTableId == -1) {
            System.out.println("=============取消结账============");
            return;
        }
        //对餐桌进行校验
        DiningTable diningTableById = diningTableService.getDiningTableById(diningTableId);
        if (diningTableById == null){
            System.out.println("=============结账的餐桌不存在============");
            return;
        }
        //验证餐桌是否有需要结账的账单
        if (!billService.hasPayBillByDiningTableId(diningTableId)) {
            System.out.println("=============该餐位没有未结账账单============");
            return;
        }
        System.out.print("结账方式(现金/支付宝/微信)回车表示退出: ");
        String payMode = Utility.readString(20, "");//说明如果回车，就是返回 ""
        if ("".equals(payMode)) {
            System.out.println("=============取消结账============");
            return;
        }
        char key = Utility.readConfirmSelection();
        if (key == 'Y' || key == 'y') { //结账
            //调用我们写的方法
            if (billService.payBill(diningTableId, payMode)) {
                System.out.println("=============完成结账============");
            }else {
                System.out.println("=============结账失败============");
            }
        }else {
            System.out.println("=============取消结账============");
        }
    }

    //显示主菜单
    public void mainView() {
        while (loop) {
            System.out.println("\n===============满汉楼================");
            System.out.println("\t\t 1 登录满汉楼");
            System.out.println("\t\t 2 退出满汉楼");
            System.out.print("请输入你的选择: ");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    System.out.println("请输入员工号");
                    String enpId = Utility.readString(50);
                    System.out.println("请输入密码");
                    String pwd = Utility.readString(50);
                    Employee employee = employeeService.getEmployeeByIdAndPwd(enpId, pwd);
                    if (employee != null) {
                        System.out.println("===============登录成功[" + employee.getName() + "]================\n");
                        //显示二级菜单，这里二级菜单是循环操作，所以做成while
                        while (loop) {
                            System.out.println("\n===============满汉楼(二级菜单)================");
                            System.out.println("\t\t 1 显示餐桌状态");
                            System.out.println("\t\t 2 预定餐桌");
                            System.out.println("\t\t 3 显示所有菜品");
                            System.out.println("\t\t 4 点餐服务");
                            System.out.println("\t\t 5 查看账单");
                            System.out.println("\t\t 6 结账");
                            System.out.println("\t\t 9 退出满汉楼");
                            System.out.print("请输入你的选择: ");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    listDiningTable();//显示餐桌状态
                                    break;
                                case "2":
                                    orderDiningTable();//预定餐桌
                                    System.out.println("预定餐桌");
                                    break;
                                case "3":
                                    listMenu(); //显示菜单
                                    System.out.println("显示所有菜品");
                                    break;
                                case "4":
                                    orderMenu();
                                    System.out.println("点餐服务");
                                    break;
                                case "5":
                                    showBillList2();//显示账单
                                    System.out.println("查看账单");
                                    break;
                                case "6":
                                    payBill();
                                    System.out.println("结账");
                                    break;
                                case "9":
                                    System.out.println("退出满汉楼");
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("输入有误，重新输入");
                            }
                        }
                    } else {
                        System.out.println("==============登陆失败===============");
                    }
                    break;
                case "2":
                    System.out.println("退出满汉楼");
                    loop = false;
                    break;
                default:
                    System.out.println("输入有误，重新输入");
            }
        }
        System.out.println("已退出满汉楼");
    }
}
