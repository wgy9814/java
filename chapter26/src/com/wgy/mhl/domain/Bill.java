package chapter26.src.com.wgy.mhl.domain;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/21 22:26
 */

import java.util.Date;

/**
 * @BelongsProject: mhl
 * @BelongsPackage: com.qinbo.mhl.domain
 * @Author: 别来无恙qb
 * @CreateTime: 2022-05-28  20:53
 * @Description: #增加表 bill 账单表(id, billId, menuId, nums, billDate, money, state, diningTableId )
 * #账单流水, 考虑可以分开结账, 并考虑将来分别统计各个不同菜品的销售情况
 * CREATE TABLE bill (
 * id INT PRIMARY KEY AUTO_INCREMENT, #自增主键
 * billId VARCHAR(50) NOT NULL DEFAULT '',#账单号可以按照自己规则生成 UUID
 * menuId INT NOT NULL DEFAULT 0,#菜品的编号, 也可以使用外键
 * nums INT NOT NULL DEFAULT 0,#份数
 * money DOUBLE NOT NULL DEFAULT 0, #金额
 * diningTableId INT NOT NULL DEFAULT 0, #餐桌
 * billDate DATETIME NOT NULL ,#订单日期
 * state VARCHAR(50) NOT NULL DEFAULT '' # 状态 '未结账' , '已经结账', '挂单','现金','支付宝','坏账'
 * )CHARSET=utf8;
 */
public class Bill {
    private Integer id;
    private String billId;
    private Integer menuId;
    private Integer nums;
    private Double money;
    private Integer diningTableId;
    private Date billDate;
    private String state;

    public Bill() {
    }

    public Bill(Integer id, String billId, Integer menuId, Integer nums, Double money, Integer diningTableId, Date billDate, String state) {
        this.id = id;
        this.billId = billId;
        this.menuId = menuId;
        this.nums = nums;
        this.money = money;
        this.diningTableId = diningTableId;
        this.billDate = billDate;
        this.state = state;
    }

    @Override
    public String toString() {
        return id +
                "\t\t" + menuId +
                "\t\t\t" + nums +
                "\t\t\t" + money +
                "\t" + diningTableId +
                "\t\t" + billDate +
                "\t\t" + state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(Integer diningTableId) {
        this.diningTableId = diningTableId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
